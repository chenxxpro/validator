package net.nextabc.validator.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.Texts;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class NotNullOrEmptyTester implements Tester {
    @Override
    public boolean test(Options opts, String value) throws Exception {
        return !Texts.isNullOrEmpty(value);
    }
}
