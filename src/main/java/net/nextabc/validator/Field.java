package net.nextabc.validator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Field为等待检查的输入条目，包含检查条目的元数据。
 *
 * @author 陈哈哈 (yoojiachen@gmail.com)
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

    /**
     * 添加Scheme列表
     *
     * @param schemes Schemes
     * @return Field
     */
    public Field schemes(Scheme... schemes) {
        return this.schemes(Arrays.asList(schemes));
    }

    /**
     * 添加Scheme列表
     * @param schemes Schemes
     * @return Field
     */
    public Field schemes(Collection<Scheme> schemes) {
        this.schemes.addAll(schemes.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        return this;
    }

    /**
     * 添加数据源接口
     * @param source 数据源接口
     * @return Field
     */
    public Field source(Source source) {
        this.source = source;
        return this;
    }

    /**
     * 设置Option，指定解析数值类型
     * @param type 数值类型
     * @return Field
     */
    public Field optionValueType(ValueType type) {
        this.optsValueType = type;
        return this;
    }

    /**
     * 设置Option，指定解析数据源的Key
     * @param key 数据源的Key，
     * @return Field
     */
    public Field optionKey(String key) {
        this.optsKey = key;
        return this;
    }

    //

    /**
     * 工厂方法
     */
    public static Field create(String key, ValueType type, Source source, Scheme... schemes) {
        return new Field()
                .optionKey(key)
                .optionValueType(type)
                .source(source)
                .schemes(schemes);
    }

}
