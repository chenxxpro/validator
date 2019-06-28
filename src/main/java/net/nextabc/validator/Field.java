package net.nextabc.validator;

import java.util.*;
import java.util.function.Function;
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
     * 参数名
     */
    String optsName;

    /**
     * 目标数据类型
     */
    ValueType optsValueType = ValueType.String;

    // Vars
    private final Function<Field, Validator> addToHostFunc;
    final List<Scheme> schemes = new ArrayList<>();
    Source source;

    public Field(Function<Field, Validator> addToHostFunc) {
        this.addToHostFunc = addToHostFunc;
    }

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
     *
     * @param schemes Schemes
     * @return Field
     */
    public Field schemes(Collection<Scheme> schemes) {
        this.schemes.addAll(schemes.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        return this;
    }

    /**
     * 添加数据源接口
     *
     * @param source 数据源接口
     * @return Field
     */
    public Field source(Source source) {
        this.source = source;
        return this;
    }

    /**
     * 设置Option，指定解析数值类型
     *
     * @param type 数值类型
     * @return Field
     */
    public Field optionValueType(ValueType type) {
        this.optsValueType = type;
        return this;
    }

    /**
     * 设置Option，指定解析数据源的Key
     *
     * @param key 数据源的Key，
     * @return Field
     */
    public Field optionKey(String key) {
        this.optsKey = key;
        return this;
    }

    /**
     * 设置Option, 指定参数名
     *
     * @param name 参数名
     * @return Field
     */
    public Field optionName(String name) {
        this.optsName = name;
        return this;
    }

    /**
     * 通过外置函数添加到Validator中
     *
     * @return Validator
     */
    public Validator add() {
        return this.addToHostFunc.apply(this);
    }

}
