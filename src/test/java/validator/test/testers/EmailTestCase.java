package validator.test.testers;

import net.nextabc.validator.Tester;
import net.nextabc.validator.testers.EmailTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * http://blogs.msdn.com/b/testing123/archive/2009/02/05/email-address-test-cases.aspx
 */
public class EmailTestCase {

    private final Tester tester = new EmailTester();

    @Test
    public void testPassed() throws Exception {
        Assert.assertTrue(tester.test(null, null));
        Assert.assertTrue(tester.test(null, ""));
        Assert.assertTrue(tester.test(null, "email@domain.com"));
        Assert.assertTrue(tester.test(null, "firstname.lastname@domain.com"));
        Assert.assertTrue(tester.test(null, "email@subdomain.domain.com"));
        Assert.assertTrue(tester.test(null, "firstname+lastname@domain.com"));
        Assert.assertTrue(tester.test(null, "email@123.123.123.123"));
        Assert.assertTrue(tester.test(null, "1234567890@domain.com"));
        Assert.assertTrue(tester.test(null, "email@domain-one.com"));
        Assert.assertTrue(tester.test(null, "_______@domain.com"));
        Assert.assertTrue(tester.test(null, "email@domain.name"));
        Assert.assertTrue(tester.test(null, "email@domain.co.jp"));
        Assert.assertTrue(tester.test(null, "firstname-lastname@domain.com"));
    }

    @Test
    public void testFail() throws Exception {
        Assert.assertFalse(tester.test(null, "plainaddress"));
        Assert.assertFalse(tester.test(null, "#@%^%#$@#$@#.com"));
        Assert.assertFalse(tester.test(null, "@domain.com"));
        Assert.assertFalse(tester.test(null, "Joe Smith <email@domain.com>"));
        Assert.assertFalse(tester.test(null, "email.domain.com"));
        Assert.assertFalse(tester.test(null, "email@domain@domain.com"));
        Assert.assertFalse(tester.test(null, ".email@domain.com"));
        Assert.assertFalse(tester.test(null, "email.@domain.com"));
        Assert.assertFalse(tester.test(null, "email..email@domain.com"));
        Assert.assertFalse(tester.test(null, "あいうえお@domain.com"));
        Assert.assertFalse(tester.test(null, "email@domain.com (Joe Smith)"));
        Assert.assertFalse(tester.test(null, "email@domain"));
        Assert.assertFalse(tester.test(null, "email@-domain.com"));
        Assert.assertFalse(tester.test(null, "email@domain..com"));
    }
}