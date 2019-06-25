package net.nextabc.validator;

/**
 * Scheme是校验模式，包括检查条目的Tester接口和对输入数据的处理方法。
 * @author 陈哈哈 (bitschen@163.com)
 */
public class Scheme {

    /**
     * 当验证失败时的提示消息
     */
    String message;

    /**
     * 对输入内容做Trim处理
     */
    private boolean trimValue;

    /**
     * 校验优先级
     */
    final int priority;

    /**
     * 具体校验算法实现接口
     */
    private final Tester tester;

    /**
     * Tester参数选项
     */
    private final Tester.Options options;

    Scheme(int priority, Tester tester, Tester.Options opts) {
        this.priority = priority;
        this.tester = tester;
        this.options = opts;
    }

    /**
     * 设置Scheme检查失败时的出错提示消息
     *
     * @param message 提示消息
     * @return Scheme
     */
    public Scheme message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置在Scheme检查时，是否需要对输入参数Trim处理。
     * @param trim 是否Trim操作，默认为false
     * @return Scheme
     */
    public Scheme trimValue(boolean trim) {
        this.trimValue = trim;
        return this;
    }

    /**
     * 设置Scheme检查时，不要Trim输入参数。
     * @return Scheme
     */
    public Scheme dontTrimValue() {
        return trimValue(false);
    }

    ////

    boolean perform(String nullableValue) throws Exception {
        return this.tester.test(this.options,
                processValue(nullableValue));
    }

    private String processValue(String nullableValue) {
        if (null != nullableValue && trimValue) {
            return nullableValue.trim();
        } else {
            return nullableValue;
        }
    }

    ////


    @Override
    public String toString() {
        return "Scheme{" +
                "message='" + message + '\'' +
                ", trimValue=" + trimValue +
                ", priority=" + priority +
                ", tester=" + tester +
                ", options=" + options +
                '}';
    }
}
