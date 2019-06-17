package validator.test;

import net.nextabc.validator.Schemes;
import net.nextabc.validator.ValidationException;
import net.nextabc.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class ValidatorTest {

    @Test
    public void Test() throws ValidationException {
        Validator validator = new Validator()
                .addField("username", Schemes.require())
                .addField("password", Schemes.require(), Schemes.length(8))
                .addField("age", Schemes.digits());
        Map<String, String> data = new HashMap<>(2);
        data.put("username", "bitschen");
        data.put("password", "HAHAHAHo");
        Assert.assertTrue("Test failed", validator.check(data));
    }
}
