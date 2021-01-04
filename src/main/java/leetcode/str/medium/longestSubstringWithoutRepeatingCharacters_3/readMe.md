### 思路
+ 使用Set存放子串，每当遍历到字符时，判断当前字符是否存在于Set中
+ 如果存在，则返回到上一个当前字符的下标
+ 如果不存在，则继续遍历

### 代码
```java
    // 使用set，通过遍历去找相同元素的下标
    public int lengthOfLongestSubstring(String s) {
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
```
```java
    // 使用map存放元素下标
    public int lengthOfLongestSubstring(String s) {
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
```

### 复杂度
+ 时间复杂度：O(n2)，使用Map存在元素下标可以将时间复杂度降为O(n)
+ 空间复杂度：O(n)