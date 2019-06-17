package net.nextabc.validator.testers;

import net.nextabc.validator.EmptyableTester;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class InSetTester extends EmptyableTester {

    @Override
    public boolean testNotEmptyValue(Options opts, String value) throws Exception {
        return opts.containsKey(value);
    }
}
