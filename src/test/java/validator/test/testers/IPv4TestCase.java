package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.IPv4Tester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 */
public class IPv4TestCase {

    private final Tester tester = new IPv4Tester();

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(tester.test(null, null));
        Assert.assertTrue(tester.test(null, ""));
        Assert.assertTrue(tester.test(null, "0.0.0.0"));
        Assert.assertTrue(tester.test(null, "1.1.1.1"));
        Assert.assertTrue(tester.test(null, "192.168.1.100"));
        Assert.assertTrue(tester.test(null, "10.0.0.0"));
        Assert.assertTrue(tester.test(null, "255.255.255.255"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertFalse(tester.test(null, "1"));
        Assert.assertFalse(tester.test(null, "1.2.3.4.5"));
        Assert.assertFalse(tester.test(null, "300.2.3.4"));
        Assert.assertFalse(tester.test(null, "256.2.3.4"));
        Assert.assertFalse(tester.test(null, "255.255.255.256"));
        Assert.assertFalse(tester.test(null, "255.255.255.abc"));
        Assert.assertFalse(tester.test(null, "255.255.256"));
        Assert.assertFalse(tester.test(null, "255.255..256"));
        Assert.assertFalse(tester.test(null, "0-123-234-1"));
    }
}