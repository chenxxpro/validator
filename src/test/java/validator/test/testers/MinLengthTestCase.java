package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.MinLengthTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.1
 */
public class MinLengthTestCase {

    private final Tester tester = new MinLengthTester();

    private final Tester.Options opts = Tester.Options.of("length", 5);

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(tester.test(opts, null));
        Assert.assertTrue(tester.test(opts, ""));
        Assert.assertTrue(tester.test(opts, "12345"));
        Assert.assertTrue(tester.test(opts, "123456"));
        Assert.assertTrue(tester.test(opts, "123456-----"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertTrue(tester.test(opts, null));
        Assert.assertTrue(tester.test(opts, ""));
        Assert.assertTrue(tester.test(opts, "12345"));
        Assert.assertTrue(tester.test(opts, "123456"));
        Assert.assertTrue(tester.test(opts, "123456-----"));
    }
}