package net.nextabc.validator.testers;

import net.nextabc.validator.EmptyableTester;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class MaxLengthTester extends EmptyableTester {
    @Override
    protected boolean testNotEmptyValue(Options opts, String value) throws Exception {
        final int length = opts.option("length");
        return value.length() <= length;
    }
}
