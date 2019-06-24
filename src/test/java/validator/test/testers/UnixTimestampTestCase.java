package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.UnixTimestampTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public class UnixTimestampTestCase {

    private final Tester tester = new UnixTimestampTester();

    @Test
    public void test() throws Exception {
        Assert.assertTrue(tester.test(null, null));
        Assert.assertTrue(tester.test(null, ""));
        Assert.assertTrue(tester.test(null, String.valueOf(System.currentTimeMillis() / 1000)));
        Assert.assertTrue(tester.test(null, "1561384496"));
        Assert.assertFalse(tester.test(null, "abc"));
        Assert.assertFalse(tester.test(null, "12345"));
        Assert.assertFalse(tester.test(null, String.valueOf(System.currentTimeMillis())));
    }
}
