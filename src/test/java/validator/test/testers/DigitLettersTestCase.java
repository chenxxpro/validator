package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.LetterDigitsTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 */
public class DigitLettersTestCase {

    private final Tester tester = new LetterDigitsTester();

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(tester.test(null, null));
        Assert.assertTrue(tester.test(null, ""));
        Assert.assertTrue(tester.test(null, "0"));
        Assert.assertTrue(tester.test(null, "123"));
        Assert.assertTrue(tester.test(null, "A"));
        Assert.assertTrue(tester.test(null, "ABC"));
        Assert.assertTrue(tester.test(null, "d"));
        Assert.assertTrue(tester.test(null, "def"));
        Assert.assertTrue(tester.test(null, "2B"));
        Assert.assertTrue(tester.test(null, "123ABC"));
        Assert.assertTrue(tester.test(null, "123abc"));
        Assert.assertTrue(tester.test(null, "123abcDEF"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertFalse(tester.test(null, "-1"));
        Assert.assertFalse(tester.test(null, "2."));
        Assert.assertFalse(tester.test(null, "2.0"));
        Assert.assertFalse(tester.test(null, "2.A"));
        Assert.assertFalse(tester.test(null, "2.a"));
        Assert.assertFalse(tester.test(null, "***//"));
        Assert.assertFalse(tester.test(null, "123..."));
        Assert.assertFalse(tester.test(null, "123..."));
        Assert.assertFalse(tester.test(null, "abc..."));
        Assert.assertFalse(tester.test(null, "ABC..."));
    }
}