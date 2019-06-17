package net.nextabc.validator;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public abstract class EmptyableTester implements Tester {
    @Override
    final public boolean test(Options opts, String value) throws Exception {
        if (Texts.isNullOrEmpty(value)) {
            return true;
        } else {
            return testNotEmptyValue(opts, value);
        }
    }

    protected abstract boolean testNotEmptyValue(Options opts, String value) throws Exception;
}
