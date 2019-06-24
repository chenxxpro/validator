package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.RangeLengthTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.1
 */
public class RangeLengthTestCase {

    private final Tester verifier = new RangeLengthTester();

    private final Tester.Options opts = Tester.Options.of("min", 3)
            .field("max", 5);

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(verifier.test(opts, null));
        Assert.assertTrue(verifier.test(opts, ""));
        Assert.assertTrue(verifier.test(opts, "123"));
        Assert.assertTrue(verifier.test(opts, "1234"));
        Assert.assertTrue(verifier.test(opts, "12345"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertFalse(verifier.test(opts, "1"));
        Assert.assertFalse(verifier.test(opts, "12"));
        Assert.assertFalse(verifier.test(opts, "123456"));
        Assert.assertFalse(verifier.test(opts, "1234567"));
    }
}