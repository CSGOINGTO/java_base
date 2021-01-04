package leetcode.str.medium.longestSubstringWithoutRepeatingCharacters_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        char[] s_chars = s.toCharArray();
        int max = 0;
        Map<Character, Integer> lastSites = new HashMap<>();
        for (int i = 0; i < s_chars.length; i++) {
            if (set.contains(s_chars[i])) {
                set.clear();
                i = lastSites.get(s_chars[i]);
                continue;
            } else {
                // 存放当前元素的位置
                lastSites.put(s_chars[i], i);
            }
            set.add(s_chars[i]);
            max = Math.max(max, set.size());
        }
        return max;
    }

    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = 0;
        int maxLen = Integer.MIN_VALUE;
        char[] s_chars = s.toCharArray();
        Set<Character> characterSet = new HashSet<>();
        for (int i = 0; i < s_chars.length; i++) {
            char c = s_chars[i];
            if (characterSet.contains(c)) {
                maxLen = Math.max(maxLen, len);
                characterSet.clear();
                for (int j = i - 1; j >= 0; j--) {
                    if (s_chars[j] == c) {
                        i = j;
                        break;
                    }
                }
                len = 0;
                continue;
            }
            characterSet.add(c);
            len++;
        }
        return Math.max(maxLen, len);
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = 0;
        int maxLen = Integer.MIN_VALUE;
        char[] s_chars = s.toCharArray();
        Map<Character, Integer> characterMap = new HashMap<>();
        for (int i = 0; i < s_chars.length; i++) {
            char c = s_chars[i];
            if (characterMap.containsKey(c)) {
                maxLen = Math.max(maxLen, len);
                i = characterMap.get(c);
                len = 0;
                characterMap.clear();
                continue;
            }
            characterMap.put(c, i);
            len++;
        }
        return Math.max(maxLen, len);
    }

    public int lengthOfLongestSubstring3(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring3("dvdf"));
    }
}
