package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.NotNullOrEmptyTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public class NotNullEmptyTestCase {

    private final Tester tester = new NotNullOrEmptyTester();

    @Test
    public void test() throws Exception {
        Assert.assertTrue(tester.test(null, "0"));
        Assert.assertTrue(tester.test(null, "a"));
        Assert.assertFalse(tester.test(null, ""));
        Assert.assertFalse(tester.test(null, null));
    }
}
