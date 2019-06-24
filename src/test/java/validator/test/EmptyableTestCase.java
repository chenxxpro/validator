package validator.test;

import net.nextabc.validator.EmptyableTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 */
public class EmptyableTestCase {

    private final EmptyableTester mTester = new EmptyableTester() {
        @Override
        protected boolean testNotEmptyValue(Options opts, String value) throws Exception {
            return "abc".equals(value);
        }
    };

    @Test
    public void giveNullAndEmptyShouldPass() throws Exception {
        Assert.assertTrue(mTester.test(null, null));
        Assert.assertTrue(mTester.test(null, ""));
    }

    @Test
    public void giveValueShouldMatches() throws Exception {
        Assert.assertTrue(mTester.test(null, "abc"));
    }

    @Test
    public void giveValueShouldNotMatches() throws Exception {
        Assert.assertFalse(mTester.test(null, "FOO"));

    }
}
