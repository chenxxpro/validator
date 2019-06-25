package validator.test;

import net.nextabc.validator.Result;
import net.nextabc.validator.Texts;
import net.nextabc.validator.ValidationException;
import net.nextabc.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static net.nextabc.validator.Schemes.*;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class ValidatorTest {

    private final Validator validator = new Validator()
            .stringField("username", require(), rangeLength(4, 8), lettersOrDigits())
            .stringField("password", require(), rangeLength(4, 8), letters())
            .intField("age", digits(), maxLength(2))
            .longField("money", digits(), maxLength(9))
            .stringField("cnid", cnIdCard())
            .stringField("mobile", cnMobile())
            .stringField("email", email())
            .stringField("server_host", host())
            .stringField("server_ip", ipv4())
            .stringField("time", unixTimestamp())
            .stringField("gender", inSet(Texts.setOf("male", "female")))
            ;

    @Test
    public void testRequireFieldsShouldPass() throws ValidationException {
        Map<String, String> data = new HashMap<>();
        data.put("username", "chen999");
        data.put("password", "abcdef");
        Assert.assertTrue(validator.check(data));
    }

    @Test
    public void testRequireFieldsShouldNotPass() {
        Map<String, String> data = new HashMap<>();
        data.put("username", "a");
        data.put("password", "123");
        final Result rs = validator.test(data);
        Assert.assertFalse(rs.errorMessage, rs.passed);
    }
}
