package net.nextabc.validator;

import net.nextabc.validator.testers.*;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Schemes {

    private static final int HIGH_PRIORITY = Integer.MIN_VALUE;
    private static final int DEFAULT_PRIORITY = 0;

    private static Scheme Optional = null;

    public static Scheme require() {
        return new Scheme(
                HIGH_PRIORITY,
                new NotNullOrEmptyTester(), null)
                .dontTrimValue()
                .message("{name}{key}不能为空");
    }

    public static Scheme ifRequire(boolean require) {
        return require ? require() : Optional;
    }

    public static Scheme notNullOrEmpty() {
        return require();
    }

    public static Scheme digits() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new DigitsTester(), null)
                .dontTrimValue()
                .message("{name}{key}必须是数字");
    }

    public static Scheme letters() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new LettersTester(), null)
                .dontTrimValue()
                .message("{name}{key}必须是字母");
    }

    public static Scheme lettersOrDigits() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new LetterDigitsTester(), null)
                .dontTrimValue()
                .message("{name}{key}必须是数字或字母");
    }

    public static Scheme email() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new EmailTester(), null)
                .trimValue(true)
                .message("{name}{key}必须是有效的邮件地址");
    }

    public static Scheme host() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new HostTester(), null)
                .trimValue(true)
                .message("{name}{key}必须是有效的主机地址");
    }

    public static Scheme ipv4() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new IPv4Tester(), null)
                .trimValue(true)
                .message("{name}{key}必须是有效的IPv4地址");
    }

    public static Scheme cnMobile() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new CNMobileTester(), null)
                .trimValue(true)
                .message("{name}{key}必须是有效的手机号码");
    }

    public static Scheme cnIdCard() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new CNIdCardTester(), null)
                .trimValue(true)
                .message("{name}{key}必须是有效的身份证号码");
    }

    public static Scheme fixedLength(int fixedLength) {
        return new Scheme(
                DEFAULT_PRIORITY,
                new FixedLengthTester(), Tester.Options.of("length", fixedLength))
                .dontTrimValue()
                .message("{name}{key}长度必须等于" + fixedLength);
    }

    public static Scheme length(int length) {
        return fixedLength(length);
    }

    public static Scheme maxLength(int max) {
        return new Scheme(
                DEFAULT_PRIORITY,
                new MaxLengthTester(), Tester.Options.of("length", max))
                .dontTrimValue()
                .message("{name}{key}最大字符长度是" + max);
    }

    public static Scheme minLength(int min) {
        return new Scheme(
                DEFAULT_PRIORITY,
                new MinLengthTester(), Tester.Options.of("length", min))
                .dontTrimValue()
                .message("{name}{key}最小字符长度是" + min);
    }

    public static Scheme rangeLength(int min, int max) {
        return new Scheme(
                DEFAULT_PRIORITY,
                new RangeLengthTester(), Tester.Options.of("min", min).field("max", max))
                .dontTrimValue()
                .message("{name}{key}字符长度是[" + min + "," + max + "]");
    }

    public static Scheme unixTimestamp() {
        return new Scheme(
                DEFAULT_PRIORITY,
                new UnixTimestampTester(), null)
                .dontTrimValue()
                .message("{name}{key}必须是Unix时间戳");
    }

    public static Scheme inSet(Set<String> allowed) {
        final Map<String, Object> map = allowed.stream()
                .collect(Collectors.toMap(Function.identity(), v -> 0));
        return new Scheme(
                DEFAULT_PRIORITY,
                new InSetTester(), Tester.Options.from(map)
        ).dontTrimValue()
                .message("{name}{key}不在允许的集合内");
    }
}
