package net.nextabc.validator;

import java.util.*;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Validator {

    private final List<Field> fields = new ArrayList<>();
    private final Set<String> keys = new HashSet<>();
    private boolean debug = false;

    private MessageFormatter formatter = (template, field) -> {
        String message = template;
        message = message.replace("{name}", (field.optsName == null ? "" : field.optsName));
        message = message.replace("{key}", (field.optsKey == null ? "" : field.optsKey));
        return message;
    };

    /**
     * 根据给定数据，检查并返回结果
     *
     * @param input        输入数据，KeyValue类型
     * @param stopWhenFail 是否发生检查失败时即停止检查
     * @return 检查结果列表
     */
    public List<Result> test(Map<String, String> input, boolean stopWhenFail) {
        if (fields.isEmpty()) {
            throw new IllegalArgumentException("没有任何检查配置");
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
        // 至少返回一个检查通过的结果
        if (results.isEmpty()) {
            results.add(Result.create(true, "PASSED"));
        }
        return results;
    }

    /**
     * 根据给定数据，检查并返回结果。发生检查失败时即停止检查。
     *
     * @param input 数据
     * @return 检查结果列表
     */
    public Result test(Map<String, String> input) {
        return test(input, true).get(0);
    }

    /**
     * 根据给定数据，检查并返回结果。发生检查失败时即停止检查。
     *
     * @param input 数据
     * @return 检查结果。
     * @throws ValidationException 如果检查失败，抛出异常
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
     * 根据给定数据，检查并返回结果。检查所有选项
     *
     * @param input 数据
     * @return 检查结果列表
     */
    public List<Result> testAll(Map<String, String> input) {
        return test(input, false);
    }

    /**
     * 添加指定Key的检查条目
     *
     * @param key     数值Key
     * @param schemes 检查方案列表
     * @return Validator
     */
    public Validator addField(String key, Scheme... schemes) {
        return field(key, ValueType.String, schemes).add();
    }

    /**
     * 添加指定数据源的检查条目
     *
     * @param source  数据源
     * @param schemes 检查方案
     * @return Validator
     */
    public Validator addField(Source source, Scheme... schemes) {
        return field("", ValueType.String, schemes).source(source).add();
    }

    //// 扩展功能

    /**
     * 添加解析数据类型为Int的检查条目
     *
     * @param key     数值Key
     * @param schemes Scheme列表
     * @return Validator
     */
    public Validator intField(String key, Scheme... schemes) {
        return fieldInt(key, schemes).add();
    }

    public Field fieldInt(String key, Scheme... schemes) {
        return field(key, ValueType.Int, schemes);
    }

    /**
     * 添加解析数据类型为Int的检查条目
     *
     * @param key     数值Key
     * @param schemes Scheme列表
     * @return Validator
     */
    public Validator longField(String key, Scheme... schemes) {
        return fieldLong(key, schemes).add();
    }

    public Field fieldLong(String key, Scheme... schemes) {
        return field(key, ValueType.Long, schemes);
    }

    /**
     * 添加解析数据类型为Int的检查条目
     *
     * @param key     数值Key
     * @param schemes Scheme列表
     * @return Validator
     */
    public Validator floatField(String key, Scheme... schemes) {
        return fieldFloat(key, schemes).add();
    }

    public Field fieldFloat(String key, Scheme... schemes) {
        return field(key, ValueType.Float, schemes);
    }

    /**
     * 添加解析数据类型为Int的检查条目
     *
     * @param key     数值Key
     * @param schemes Scheme列表
     * @return Validator
     */
    public Validator doubleField(String key, Scheme... schemes) {
        return fieldDouble(key, schemes).add();
    }

    public Field fieldDouble(String key, Scheme... schemes) {
        return field(key, ValueType.Double, schemes);
    }

    /**
     * 添加解析数据类型为Int的检查条目
     *
     * @param key     数值Key
     * @param schemes Scheme列表
     * @return Validator
     */
    public Validator boolField(String key, Scheme... schemes) {
        return fieldBool(key, schemes).add();
    }

    public Field fieldBool(String key, Scheme... schemes) {
        return field(key, ValueType.Boolean, schemes);
    }

    /**
     * 添加解析数据类型为Int的检查条目
     *
     * @param key     数值Key
     * @param schemes Scheme列表
     * @return Validator
     */
    public Validator stringField(String key, Scheme... schemes) {
        return fieldString(key, schemes).add();
    }

    public Field fieldString(String key, Scheme... schemes) {
        return field(key, ValueType.String, schemes);
    }

    /**
     * 添加指定数据源的检查条目
     *
     * @param newField 数据源检查条目
     * @return Validator
     */
    public Validator addField(Field newField) {
        if (this.keys.contains(newField.optsKey)) {
            throw new RuntimeException("重复的参数Key[" + newField.optsKey + "]");
        }
        this.fields.add(newField);
        this.keys.add(newField.optsKey);
        return this;
    }

    /**
     * 创建Field对象，需要调用 {@link Field#add()} 函数来将Field添加到Validator。
     *
     * @param key     Key
     * @param type    解析数据类型
     * @param schemes Scheme列表
     * @return Field对象
     */
    public Field field(String key, ValueType type, Scheme... schemes) {
        return new Field(this::addField).optionValueType(type).optionKey(key).schemes(schemes);
    }

    /**
     * 设置是否启用Debug输出
     *
     * @param enable 开启Debug
     * @return Validator
     */
    public Validator debug(boolean enable) {
        this.debug = enable;
        return this;
    }

    /**
     * 设置消息格式化处理接口
     *
     * @param formatter 出错消息格式化接口
     * @return Validator
     */
    public Validator messageFormatter(MessageFormatter formatter) {
        this.formatter = Objects.requireNonNull(formatter);
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
            final Object value = parseValue(field.optsValueType, input.get(field.optsKey));
            if (value != null) {
                out.put(field.optsKey, value);
            }
        }
        return out;
    }

    ////

    private Object parseValue(ValueType type, String value) throws ValidationException {
        switch (type) {
            case Int:
                return Texts.mustInt(value);

            case Long:
                return Texts.mustLong(value);

            case Float:
                return Texts.mustFloat(value);

            case Double:
                return Texts.mustDouble(value);

            case Boolean:
                return Texts.mustBoolean(value);

            default:
                return value;
        }
    }

    private Result performTest(Field field) {
        final String nullableValue = field.source.value();
        field.schemes.sort(Comparator.comparingInt(lhs -> lhs.priority));
        try {
            for (Scheme scheme : field.schemes) {
                final boolean passed = scheme.perform(nullableValue);
                if (!passed) {
                    if (debug) {
                        System.err.println("TEST FAILED" +
                                ": RawValue=" + nullableValue +
                                ", Key=" + field.optsKey +
                                ", Name=" + field.optsName +
                                ", ValueType=" + field.optsValueType +
                                ", " + scheme);
                    }
                    return Result.create(false, formatter.format(scheme.message, field));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (debug) {
                e.printStackTrace();
            }
            return Result.create(false, "ERR:" + e.getMessage());
        }
        return null;
    }
}
