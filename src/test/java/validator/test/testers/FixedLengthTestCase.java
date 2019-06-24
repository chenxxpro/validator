package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.FixedLengthTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public class FixedLengthTestCase {

    private final Tester tester = new FixedLengthTester();

    @Test
    public void test() throws Exception {
        final Tester.Options opts = Tester.Options.of("length", 4);
        Assert.assertTrue(tester.test(opts, null));
        Assert.assertTrue(tester.test(opts, ""));
        Assert.assertTrue(tester.test(opts, "1234"));
        Assert.assertFalse(tester.test(opts, "123"));
        Assert.assertFalse(tester.test(opts, "12345"));
    }
}
