package net.nextabc.validator;

import java.util.regex.Pattern;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Texts {

    public static boolean isNullOrEmpty(String in) {
        return null == in || in.length() == 0;
    }

    public static boolean regexMatch(String input, String regex) {
        return Pattern.compile(regex).matcher(input).matches();
    }

    public static boolean isDigits(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
