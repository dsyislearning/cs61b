public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(Character.valueOf(word.charAt(i)));
        }
        return res;
    }

    private String dequeToWord(Deque<Character> wordDeque) {
        String res = "";
        while (!wordDeque.isEmpty()) {
            Character ch = wordDeque.removeFirst();
            res += ch.charValue();
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        if (wordDeque.isEmpty() || wordDeque.size() == 1) {
            return true;
        }
        Character first = wordDeque.removeFirst(),
                  last = wordDeque.removeLast();
        if (first.equals(last)) {
            return isPalindrome(dequeToWord(wordDeque));
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        if (wordDeque.isEmpty() || wordDeque.size() == 1) {
            return true;
        }
        Character first = wordDeque.removeFirst(),
                last = wordDeque.removeLast();
        if (cc.equalChars(first.charValue(), last.charValue())) {
            return isPalindrome(dequeToWord(wordDeque), cc);
        } else {
            return false;
        }
    }
}
