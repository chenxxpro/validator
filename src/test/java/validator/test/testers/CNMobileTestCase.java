package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.CNMobileTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 */
public class CNMobileTestCase {

    private Tester tester;

    @Before
    public void before() {
        tester = new CNMobileTester();
    }

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(tester.test(null, null));
        Assert.assertTrue(tester.test(null, ""));
        Assert.assertTrue(tester.test(null, "13800138000"));
        Assert.assertTrue(tester.test(null, "13570859999"));
        Assert.assertTrue(tester.test(null, "13112111001"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertFalse(tester.test(null, "12306"));
        Assert.assertFalse(tester.test(null, "mobile"));
        Assert.assertFalse(tester.test(null, "88888888"));
        Assert.assertFalse(tester.test(null, "123456789012"));
        Assert.assertFalse(tester.test(null, "1234567890X"));
    }
}