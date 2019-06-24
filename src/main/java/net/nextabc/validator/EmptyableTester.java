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

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param opts  Options, nullable
     * @param value the input value, NotNull and NotEmpty.
     * @return {@code true} if the input value matches the tester, otherwise {@code false}
     * @throws Exception throw Exception if error
     */
    protected abstract boolean testNotEmptyValue(Options opts, String value) throws Exception;
}
