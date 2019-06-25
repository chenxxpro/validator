package net.nextabc.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Texts {


    public static Set<String> setOf(Object...items){
        return Arrays.stream(items).map(String::valueOf).collect(Collectors.toSet());
    }

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

    public static boolean isLetters(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isLetter(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    ////

    public static Integer mustInt(String value) throws ValidationException {
        if (value == null) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new ValidationException("参数不能解析为Int类型:[" + value + "]", e);
        }
    }

    public static Long mustLong(String value) throws ValidationException {
        if (value == null) {
            return null;
        }
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            throw new ValidationException("参数不能解析为Long类型:[" + value + "]", e);
        }
    }

    public static Float mustFloat(String value) throws ValidationException {
        if (value == null) {
            return null;
        }
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            throw new ValidationException("参数不能解析为Long类型:[" + value + "]", e);
        }
    }

    public static Double mustDouble(String value) throws ValidationException {
        if (value == null) {
            return null;
        }
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            throw new ValidationException("参数不能解析为Long类型:[" + value + "]", e);
        }
    }

    public static Boolean mustBoolean(String value) throws ValidationException {
        if (value == null) {
            return null;
        }
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            throw new ValidationException("参数不能解析为Boolean类型:[" + value + "]", e);
        }
    }
}
