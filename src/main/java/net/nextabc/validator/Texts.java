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

    ////

    public static int mustInt(String value) throws ValidationException {
        if (value == null) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new ValidationException("参数不能解析为Int类型:[" + value + "]", e);
        }
    }

    public static long mustLong(String value) throws ValidationException {
        if (value == null) {
            return 0;
        }
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            throw new ValidationException("参数不能解析为Long类型:[" + value + "]", e);
        }
    }

    public static boolean mustBoolean(String value) throws ValidationException {
        if (value == null) {
            return false;
        }
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            throw new ValidationException("参数不能解析为Boolean类型:[" + value + "]", e);
        }
    }
}
