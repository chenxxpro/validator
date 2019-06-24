package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.MaxLengthTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.1
 */
public class MaxLengthTestCase {

    private final Tester tester = new MaxLengthTester();

    private final Tester.Options opts = Tester.Options.of("length", 10);

    @Test
    public void test() throws Exception {
        Assert.assertTrue(tester.test(opts, null));
        Assert.assertTrue(tester.test(opts, ""));
        Assert.assertTrue(tester.test(opts, "1"));
        Assert.assertTrue(tester.test(opts, "12345"));
        Assert.assertTrue(tester.test(opts, "1234567890"));
        Assert.assertFalse(tester.test(opts, "1234567890A"));
        Assert.assertFalse(tester.test(opts, "1234567890ABC"));
    }

}