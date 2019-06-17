package net.nextabc.validator;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
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

    public Scheme message(String message) {
        this.message = message;
        return this;
    }

    public Scheme trimValue(boolean trim) {
        this.trimValue = trim;
        return this;
    }

    public Scheme dontTrimValue() {
        return trimValue(false);
    }

    ////

    boolean perform(String value) throws Exception {
        return this.tester.test(this.options, value);
    }

    String processValue(String value) {
        if (trimValue) {
            return value.trim();
        } else {
            return value;
        }
    }
}
