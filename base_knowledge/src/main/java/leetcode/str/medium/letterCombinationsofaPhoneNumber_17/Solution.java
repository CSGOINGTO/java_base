package leetcode.str.medium.letterCombinationsofaPhoneNumber_17;

import java.util.*;

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
    private List<String> res = new ArrayList<>();
    private static final Map<Character, List<String>> digitsStrings = new HashMap<>();

    static {
        digitsStrings.put('2', Arrays.asList("a", "b", "c"));
        digitsStrings.put('3', Arrays.asList("d", "e", "f"));
        digitsStrings.put('4', Arrays.asList("g", "h", "i"));
        digitsStrings.put('5', Arrays.asList("j", "k", "l"));
        digitsStrings.put('6', Arrays.asList("m", "n", "o"));
        digitsStrings.put('7', Arrays.asList("p", "q", "r", "s"));
        digitsStrings.put('8', Arrays.asList("t", "u", "v"));
        digitsStrings.put('9', Arrays.asList("w", "x", "y", "z"));
    }

    /**
     * 方法一：递归法
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return res;
        iterStr(digits, "", 0);
        return res;
    }

    private void iterStr(String str, String letter, int index) {
        if (index == str.length()) {
            res.add(letter);
            return;
        }
        char c = str.charAt(index);
        List<String> stringList = digitsStrings.get(c);
        for (String s : stringList) {
            iterStr(str, letter + s, index + 1);
        }
    }

    /**
     * 方法二：层次遍历法
     */
    public List<String> letterCombinations1(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        Queue<String> queue = new LinkedList<>();
        List<String> strings1 = digitsStrings.get(digits.charAt(0));
        for (String s : strings1) {
            queue.offer(s);
        }
        for (int i = 1; i < digits.length(); i++) {
            List<String> strings = digitsStrings.get(digits.charAt(i));
            int count = queue.size();
            while (count > 0) {
                String poll = queue.poll();
                count--;
                for (String string : strings) {
                    queue.offer(poll + string);
                }
            }
        }
        res.addAll(queue);
        return res;
    }

}
