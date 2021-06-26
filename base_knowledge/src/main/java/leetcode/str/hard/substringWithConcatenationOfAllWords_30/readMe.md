### 思路
1.记录下标的方法
+ 使用Map记录words数组中所有元素的下标
+ 每次遍历时，使用Set记录已经遍历到words数组中元素的下标
+ 判断遍历到的某个元素是否在words中，如果存在则继续，如果不存在则直接遍历下一个子串
+ 当Set中已经存在某个元素的下标时，说明子串中该元素已经重复，直接遍历下一个子串
+ 当Set中成功添加某个元素的下边时，如果Set的长度和words的长度一致，则说明该子串是words组成的子串；如果长度不一致，则继续遍历

2.记录元素个数的方法
+ 使用Map1记录words数组中所有元素出现的个数
+ 每次遍历时，使用另外一个Map2记录子串中words数组中元素出现的个数
+ 如果遍历到的元素不在Map1中，则直接遍历下一个子串
+ 如果Map1和Map2中元素的个数都相同，则说明该子串是words组成的子串；如果长度不一致，则继续遍历

### 代码
```java
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
```

### 复杂度
+ 时间复杂度：O(n)~O(n3)
+ 空间复杂度：O(n)
