package net.nextabc.validator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Field {

    // Options

    /**
     * 数据Key
     */
    String optsKey;

    /**
     * 目标数据类型
     */
    ValueType optsValueType = ValueType.String;

    // Vars

    final List<Scheme> schemes = new ArrayList<>();
    Source source;

    public Field schemes(Scheme... schemes) {
        return this.schemes(Arrays.asList(schemes));
    }

    public Field schemes(Collection<Scheme> schemes) {
        this.schemes.addAll(schemes.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        return this;
    }

    public Field source(Source source) {
        this.source = source;
        return this;
    }

    public Field options(Object... opts) {
        for (Object opt : opts) {
            if (opt instanceof String) {
                this.optionKey((String) opt);
            } else if (opt instanceof ValueType) {
                this.optionValueType((ValueType) opt);
            }
        }
        return this;
    }

    public Field optionValueType(ValueType type) {
        this.optsValueType = type;
        return this;
    }

    public Field optionKey(String key) {
        this.optsKey = key;
        return this;
    }

    //

    public static Field create(String key, ValueType type, Source source, Scheme... schemes) {
        return new Field()
                .optionKey(key)
                .optionValueType(type)
                .source(source)
                .schemes(schemes);
    }

}
