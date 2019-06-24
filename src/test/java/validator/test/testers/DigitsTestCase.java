package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.DigitsTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 */
public class DigitsTestCase {

    private final Tester tester = new DigitsTester();

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(tester.test(null, null));
        Assert.assertTrue(tester.test(null, ""));
        Assert.assertTrue(tester.test(null, "1"));
        Assert.assertTrue(tester.test(null, "123"));
        Assert.assertTrue(tester.test(null, "99999999999"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertFalse(tester.test(null, "1a"));
        Assert.assertFalse(tester.test(null, "123l"));
        Assert.assertFalse(tester.test(null, "123456L"));
        Assert.assertFalse(tester.test(null, "0x123"));
        Assert.assertFalse(tester.test(null, "0xFF"));
        Assert.assertFalse(tester.test(null, "0x00"));
        Assert.assertFalse(tester.test(null, "abc"));
    }
}