package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.CNIdCardTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CnIDCardTestCase {

    private Tester mTester;

    @Before
    public void before() {
        mTester = new CNIdCardTester();
    }

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(mTester.test(null, ""));
        Assert.assertTrue(mTester.test(null, "440901197709194316"));
        Assert.assertTrue(mTester.test(null, "440901197502198379"));
        Assert.assertTrue(mTester.test(null, "44090119840325427X"));
        Assert.assertTrue(mTester.test(null, "44090119840325427x"));
        Assert.assertTrue(mTester.test(null, "210303198412082729"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertFalse(mTester.test(null, "abc"));
        Assert.assertFalse(mTester.test(null, "1234"));
        Assert.assertFalse(mTester.test(null, "+440901197709194316"));
        Assert.assertFalse(mTester.test(null, "H440901197502198379"));
        Assert.assertFalse(mTester.test(null, "A44090119840325427X"));
        Assert.assertFalse(mTester.test(null, "0440901199006249711"));
    }
}