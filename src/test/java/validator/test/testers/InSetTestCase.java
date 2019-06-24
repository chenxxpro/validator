package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.InSetTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public class InSetTestCase {

    private final Tester tester = new InSetTester();

    @Test
    public void testBool() throws Exception {
        Tester.Options opts = Tester.Options
                .of("true", 0)
                .field("false", 0);
        Assert.assertTrue(tester.test(opts, null));
        Assert.assertTrue(tester.test(opts, ""));
        Assert.assertTrue(tester.test(opts, "true"));
        Assert.assertTrue(tester.test(opts, "false"));

        Assert.assertFalse(tester.test(opts, "falsexxx"));
        Assert.assertFalse(tester.test(opts, "trueyyy"));
        Assert.assertFalse(tester.test(opts, "False"));
        Assert.assertFalse(tester.test(opts, "True"));
    }
}
