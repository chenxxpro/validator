package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.HostTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 */
public class HostTestCase {

    private final Tester tester = new HostTester();

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(tester.test(null, null));
        Assert.assertTrue(tester.test(null, ""));
        Assert.assertTrue(tester.test(null, "1.2.3.4"));
        Assert.assertTrue(tester.test(null, "abc.com"));
        Assert.assertTrue(tester.test(null, "api.abc.com"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertFalse(tester.test(null, "abc"));
        Assert.assertFalse(tester.test(null, "abc..com"));
        Assert.assertFalse(tester.test(null, "1.2.3.4.5"));
        Assert.assertFalse(tester.test(null, "http://abc.com"));
        Assert.assertFalse(tester.test(null, "http://1.2.3.4"));
    }
}