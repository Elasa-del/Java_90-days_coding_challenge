public class Day_04_Strings_Text_Analytics_Engine {
    // TODO 4.1: Implement isPalindrome(String input) - ignore punctuation and casing
    // HINT: Use Character.isLetterOrDigit() and Character.toLowerCase() inside a StringBuilder.
    public static boolean isPalindrome(String input) {
        if (input == null) return false;
        StringBuilder clean = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                clean.append(Character.toLowerCase(ch));
            }
        }

        int left = 0, right = clean.length() - 1;
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // TODO 4.2: Implement isAnagram(String s1, String s2) using a 26-element frequency array
    // HINT: Increment freq for s1, decrement for s2, verify all 0.
    public static boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) return false;

        String cleanS1 = s1.replaceAll("^[a-z]", "").toLowerCase();
        String cleanS2 = s1.replaceAll("^[a-z]", "").toLowerCase();

        if (cleanS1.length() != cleanS2.length()) return false;

        int[] charCounts = new int[26];

        for (int i = 0; i < cleanS1.length(); i++) {
            charCounts[cleanS1.charAt(i) - 'a']++;
            charCounts[cleanS2.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) return false;

        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is 'A man, a plan, a canal: Panama!' Palindrome? " + isPalindrome("A man, a plan, a canal: Panama!"));
        System.out.println("Are 'Listen' and 'Silent' Anagrams? " + isAnagram("Listen", "Silent"));
    }
}

