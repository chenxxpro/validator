package net.nextabc.validator.testers;

import net.nextabc.validator.EmptyableTester;
import net.nextabc.validator.Texts;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class MobileTester extends EmptyableTester {

    @Override
    public boolean testNotEmptyValue(Options opts, String value) throws Exception {
        return Texts.regexMatch(value, "^(\\+?\\d{2}-?)?1[3456789]\\d{9}$");
    }
}
