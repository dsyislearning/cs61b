import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return a == b;
    }

    @Test
    public void testIsSameNumber() {
        boolean fact127 = Flik.isSameNumber(127, 127);
        boolean fact128 = Flik.isSameNumber(128, 128);

        assertTrue("127 doesn't equal to 127", fact127);
        assertTrue("128 doesn't equal to 128", fact128);
    }
}
