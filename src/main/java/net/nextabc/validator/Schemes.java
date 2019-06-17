package net.nextabc.validator;

import net.nextabc.validator.testers.*;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Schemes {

    public static final int HIGH_PRIORITY = Integer.MIN_VALUE;
    public static final int DEFAULT_PRIORITY = 0;

    public static Scheme require() {
        return notNullOrEmpty();
    }

    public static Scheme notNullOrEmpty() {
        return new Scheme(
                HIGH_PRIORITY,
                new NotNullOrEmptyTester(),
                null)
                .dontTrimValue()
                .message("不能为空");
    }

    public static Scheme digits() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new DigitsTester(),
                null)
                .dontTrimValue()
                .message("必须为数字");
    }

    public static Scheme email() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new EmailTester(),
                null)
                .trimValue(true)
                .message("必须为数字");
    }

    public static Scheme cnIdCard() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new CNIdCardTester(),
                null)
                .trimValue(true)
                .message("必须为有效的身份证号码");
    }

    public static Scheme length(int length) {
        return new Scheme(
                DEFAULT_PRIORITY,
                new FixedLengthTester(),
                Tester.Options.of("length", length))
                .dontTrimValue()
                .message("长度必须为" + length);
    }

    public static Scheme maxLength(int max) {
        return new Scheme(
                DEFAULT_PRIORITY,
                new MaxLengthTester(),
                Tester.Options.of("length", max))
                .dontTrimValue()
                .message("长度最大为" + max);
    }

    public static Scheme minLength(int max) {
        return new Scheme(
                DEFAULT_PRIORITY,
                new MinLengthTester(),
                Tester.Options.of("length", max))
                .dontTrimValue()
                .message("长度至少为" + max);
    }

    public static Scheme inSet(Set<String> allowed) {
        return new Scheme(
                DEFAULT_PRIORITY,
                new InSetTester(),
                Tester.Options.from(allowed.stream().collect(Collectors.toMap(Function.identity(), Function.identity())))
        ).dontTrimValue()
                .message("不在允许的集合内");
    }
}
