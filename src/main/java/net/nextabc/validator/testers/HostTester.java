package net.nextabc.validator.testers;

import net.nextabc.validator.EmptyableTester;
import net.nextabc.validator.Texts;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class HostTester extends EmptyableTester {

    @Override
    public boolean testNotEmptyValue(Options opts, String value) throws Exception {
        return IPv4Tester.isIPv4(value) ||
                Texts.regexMatch(value.toLowerCase(), "^([a-z0-9]([a-z0-9\\-]{0,65}[a-z0-9])?\\.)+[a-z]{2,6}$");
    }
}
