package net.nextabc.validator;

import java.util.*;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Validator {

    private static final Comparator<Scheme> SORT = Comparator.comparingInt(lhs -> lhs.priority);

    private final List<Field> fields = new ArrayList<>();
    private final Map<String, Field> keyFields = new HashMap<>();

    /**
     * 根据给定数据，校验并返回结果
     *
     * @param input        数据
     * @param stopWhenFail 是否发生校验失败时即停止检查
     * @return 校验结果列表
     */
    public List<Result> test(Map<String, String> input, boolean stopWhenFail) {
        if (fields.isEmpty()) {
            throw new IllegalArgumentException("没有任何查供校验的配置");
        }
        final List<Result> results = new ArrayList<>(1);
        for (Field field : this.fields) {
            field.setSourceByKey(input);
            final Result ret = performTest(field);
            if (null != ret && !ret.passed) {
                results.add(ret);
                if (stopWhenFail) {
                    break;
                }
            }
        }
        // 至少返回一个校验通过的结果
        if (results.isEmpty()) {
            results.add(Result.create(true, "PASSED"));
        }
        return results;
    }

    /**
     * 根据给定数据，校验并返回结果。发生校验失败时即停止检查。
     *
     * @param input 数据
     * @return 校验结果列表
     */
    public Result test(Map<String, String> input) {
        return test(input, true).get(0);
    }

    /**
     * 根据给定数据，校验并返回结果。发生校验失败时即停止检查。
     *
     * @param input 数据
     * @return 校验结果。
     * @throws ValidationException 如果校验失败，抛出异常
     */
    public boolean check(Map<String, String> input) throws ValidationException {
        final Result r = test(input);
        if (r.passed) {
            return true;
        } else {
            throw new ValidationException(r.errorMessage);
        }
    }

    /**
     * 根据给定数据，校验并返回结果。检查所有选项
     *
     * @param input 数据
     * @return 校验结果列表
     */
    public List<Result> testAll(Map<String, String> input) {
        return test(input, false);
    }

    /**
     * 添加指定Key的校验条目
     *
     * @param key     数值Key
     * @param schemes 校验方案列表
     * @return Validator
     */
    public Validator addField(String key, Scheme... schemes) {
        final Field found = this.keyFields.get(key);
        if (null != found) {
            found.addSchemes(schemes);
        } else {
            final Field newField = new Field().setKey(key)
                    .addSchemes(schemes);
            this.fields.add(newField);
            this.keyFields.put(key, newField);
        }
        return this;
    }

    /**
     * 添加指定数据源的校验条目
     *
     * @param source  数据源
     * @param schemes 校验方案
     * @return Validator
     */
    public Validator addField(Source source, Scheme... schemes) {
        final Field newField = new Field().setSource(source)
                .addSchemes(schemes);
        this.fields.add(newField);
        return this;
    }

    /**
     * 添加指定Key的校验条目
     *
     * @param key     数值Key
     * @param schemes 校验方案列表
     * @return Validator
     */
    public Fluent field(String key, Scheme... schemes) {
        return new Fluent(this, new Field()
                .setKey(key)
                .addSchemes(schemes));
    }

    /**
     * 添加指定数据源的校验条目
     *
     * @param source  数据源
     * @param schemes 校验方案
     * @return Validator
     */
    public Fluent field(Source source, Scheme... schemes) {
        return new Fluent(this, new Field()
                .setSource(source)
                .addSchemes(schemes));
    }

    ////

    public static class Fluent extends Field {

        private final Validator ref;
        private final Field field;

        public Fluent(Validator ref, Field field) {
            this.ref = ref;
            this.field = field;
        }

        public Validator add() {
            ref.fields.add(field);
            return ref;
        }
    }

    ////

    private Result performTest(Field field) {
        final String value = field.source.value();
        field.schemes.sort(SORT);
        try {
            for (Scheme scheme : field.schemes) {
                final String testValue = scheme.processValue(value);
                final boolean passed = scheme.perform(testValue);
                if (!passed) {
                    return Result.create(false, scheme.message);
                }
            }
        } catch (Exception e) {
            return Result.create(false, e.getMessage());
        }
        return null;
    }
}
