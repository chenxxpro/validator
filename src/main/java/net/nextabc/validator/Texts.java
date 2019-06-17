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

    /**
     * If input matched regex
     *
     * @param input Input String
     * @param regex Regex
     * @return is matched
     */
    public static boolean regexMatch(String input, String regex) {
        return Pattern.compile(regex).matcher(input).matches();
    }
}
