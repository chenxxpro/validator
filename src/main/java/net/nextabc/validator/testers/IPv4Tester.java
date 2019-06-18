package net.nextabc.validator.testers;

import net.nextabc.validator.EmptyableTester;
import net.nextabc.validator.Texts;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class IPv4Tester extends EmptyableTester {

    @Override
    public boolean testNotEmptyValue(Options opts, String value) throws Exception {
        return isIPv4(value);
    }

    static boolean isIPv4(String value) {
        return Texts.regexMatch(value,
                "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
    }
}
