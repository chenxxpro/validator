package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.LettersTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.1
 */
public class LettersTestCase {

    private Tester tester = new LettersTester();

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(tester.test(null, null));
        Assert.assertTrue(tester.test(null, ""));
        Assert.assertTrue(tester.test(null, "A"));
        Assert.assertTrue(tester.test(null, "BC"));
        Assert.assertTrue(tester.test(null, "a"));
        Assert.assertTrue(tester.test(null, "abc"));
        Assert.assertTrue(tester.test(null, "aSdF"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertFalse(tester.test(null, "-"));
        Assert.assertFalse(tester.test(null, "2."));
        Assert.assertFalse(tester.test(null, "2.0"));
        Assert.assertFalse(tester.test(null, "2A"));
        Assert.assertFalse(tester.test(null, "2a"));
        Assert.assertFalse(tester.test(null, "***//"));
        Assert.assertFalse(tester.test(null, "abc123..."));
    }
}