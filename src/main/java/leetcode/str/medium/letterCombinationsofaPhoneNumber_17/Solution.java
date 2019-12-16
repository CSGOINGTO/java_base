package leetcode.str.medium.letterCombinationsofaPhoneNumber_17;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2 abc
 * 3 def
 * 4 ghi
 * 5 jkl
 * 6 mno
 * 7 pqrs
 * 8 tuv
 * 9 wxyz
 */
public class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null) return null;
        Map<Character, List<String>> digitsStrings = new HashMap<>();
        digitsStrings.put('2', Arrays.asList("a", "b", "c"));
        digitsStrings.put('3', Arrays.asList("d", "e", "f"));
        digitsStrings.put('4', Arrays.asList("g", "h", "i"));
        digitsStrings.put('5', Arrays.asList("j", "k", "l"));
        digitsStrings.put('6', Arrays.asList("m", "n", "o"));
        digitsStrings.put('7', Arrays.asList("p", "q", "r", "s"));
        digitsStrings.put('8', Arrays.asList("t", "u", "v"));
        digitsStrings.put('9', Arrays.asList("w", "x", "y", "z"));

        return null;

    }
}
