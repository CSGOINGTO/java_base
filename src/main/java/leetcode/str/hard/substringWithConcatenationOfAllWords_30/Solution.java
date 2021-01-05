package leetcode.str.hard.substringWithConcatenationOfAllWords_30;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> resList = new ArrayList<>();
        if (s == null || s.length() == 0) return resList;
        Map<String, Set<Integer>> wordsMap = new HashMap<>();
        int strLen = words[0].length();
        for (int i = 0; i < words.length; i++) {
            Set<Integer> indexSet = wordsMap.get(words[i]);
            if (indexSet == null) {
                HashSet<Integer> set = new HashSet<>();
                set.add(i);
                wordsMap.put(words[i], set);
            } else {
                indexSet.add(i);
                wordsMap.put(words[i], indexSet);
            }
        }
        int allWordsLen = strLen * words.length;
        char[] s_chars = s.toCharArray();
        for (int i = 0; i <= s_chars.length - allWordsLen; i++) {
            Set<Integer> subStrIndexSet = new HashSet<>();
            for (int j = i; j <= s_chars.length - strLen; j += strLen) {
                String subString = s.substring(j, j + strLen);
                // 判断子串是否在words列表中
                if (wordsMap.containsKey(subString)) {
                    // 判断当前已经存在的下标是否存在
                    boolean isExist = false;
                    Set<Integer> subStringIndexSet = wordsMap.get(subString);
                    Iterator<Integer> iterator = subStringIndexSet.iterator();
                    while (!isExist && iterator.hasNext()) {
                        Integer index = iterator.next();
                        isExist = subStrIndexSet.add(index);
                    }
                    if (!isExist) break;
                    // 如果已经存在的下标的长度和map的长度一致，说明是一个子串
                    if (subStrIndexSet.size() == words.length) {
                        resList.add(i);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return resList;
    }
}
