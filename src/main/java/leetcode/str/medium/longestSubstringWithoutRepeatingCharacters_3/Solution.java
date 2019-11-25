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
}
