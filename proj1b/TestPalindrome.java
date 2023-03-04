import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue("empty string is palindrome", palindrome.isPalindrome(""));
        assertTrue("\"a\" is palindrome", palindrome.isPalindrome("a"));
        assertTrue("\"racecar\" is palindrome", palindrome.isPalindrome("racecar"));
        assertFalse("\"cat\" is not palindrome", palindrome.isPalindrome("cat"));
        assertFalse("\"horse\" is not palindrome", palindrome.isPalindrome("horse"));
    }

    static CharacterComparator cc = new OffByOne();

    @Test
    public void testIsPalindromeOffByOne() {
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("f", cc));
        assertTrue(palindrome.isPalindrome("%&", cc));
        assertFalse(palindrome.isPalindrome("aba", cc));
        assertFalse(palindrome.isPalindrome("!@#$%^&   ", cc));
    }
}
