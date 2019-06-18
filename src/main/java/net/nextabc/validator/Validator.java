package net.nextabc.validator;

import java.util.*;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Validator {

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
            // 指定Key时，从Input获取数据并生成Source
            if (!Texts.isNullOrEmpty(field.optsKey)) {
                field.source(() -> input.get(field.optsKey));
            }
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
        return addField(new Field()
                .optionKey(key).schemes(schemes));
    }

    /**
     * 添加指定数据源的校验条目
     *
     * @param source  数据源
     * @param schemes 校验方案
     * @return Validator
     */
    public Validator addField(Source source, Scheme... schemes) {
        return addField(new Field()
                .source(source)
                .schemes(schemes));
    }

    //// 扩展功能

    public Validator intField(String key, Scheme... schemes) {
        return dataField(key, DataType.Int, schemes);
    }

    public Validator longField(String key, Scheme... schemes) {
        return dataField(key, DataType.Long, schemes);
    }

    public Validator boolField(String key, Scheme... schemes) {
        return dataField(key, DataType.Boolean, schemes);
    }

    public Validator strField(String key, Scheme... schemes) {
        return dataField(key, DataType.String, schemes);
    }

    public Validator dataField(String key, DataType type, Scheme... schemes) {
        return addField(new Field()
                .optionDataType(type)
                .optionKey(key)
                .schemes(schemes));
    }

    /**
     * 添加指定数据源的校验条目
     *
     * @param in 数据源校验条目
     * @return Validator
     */
    public Validator addField(Field in) {
        final Field found = this.keyFields.get(in.optsKey);
        if (null != found) {
            found.schemes(in.schemes);
        } else {
            final Field newField = new Field()
                    .optionKey(in.optsKey)
                    .schemes(in.schemes);
            this.fields.add(newField);
            this.keyFields.put(in.optsKey, newField);
        }
        return this;
    }

    /**
     * 解析参数到指定数据类型
     *
     * @param input 原始输入数据
     * @return 已解析到特定类型的Map
     * @throws ValidationException 解析参数失败时
     */
    public Map<String, Object> parseByKey(Map<String, String> input) throws ValidationException {
        final Map<String, Object> out = new HashMap<>(input.size());
        for (Field field : this.fields) {
            if (Texts.isNullOrEmpty(field.optsKey)) {
                continue;
            }
            final String value = input.get(field.optsKey);
            switch (field.optsDataType) {
                case Int:
                    out.put(field.optsKey, Texts.mustInt(value));
                    break;

                case Long:
                    out.put(field.optsKey, Texts.mustLong(value));
                    break;

                case Boolean:
                    out.put(field.optsKey, Texts.mustBoolean(value));
                    break;

                default:
                    out.put(field.optsKey, value);
            }
        }
        return out;
    }

    ////

    private Result performTest(Field field) {
        final String value = field.source.value();
        field.schemes.sort(Comparator.comparingInt(lhs -> lhs.priority));
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
