package net.nextabc.validator.testers;

import net.nextabc.validator.EmptyableTester;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class LetterDigitsTester extends EmptyableTester {

    @Override
    public boolean testNotEmptyValue(Options opts, String value) throws Exception {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isLetterOrDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
