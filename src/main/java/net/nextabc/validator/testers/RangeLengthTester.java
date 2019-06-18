package net.nextabc.validator.testers;

import net.nextabc.validator.EmptyableTester;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class RangeLengthTester extends EmptyableTester {
    @Override
    protected boolean testNotEmptyValue(Options opts, String value) throws Exception {
        final int min = opts.option("min");
        final int max = opts.option("max");
        final int len = value.length();
        return min <= len && len <= max;
    }
}
