### 思路一
+ 将数组元素进行排序，然后取排在前k的元素
### 代码一
<details>
<summary>愚蠢而又直观版</summary>
<pre><code>
    public int[] topKFrequent(int[] nums, int k) {
        int[] returnNums = new int[k];
        Map<Integer, Integer> helpMap = new HashMap<>();
        List<Integer> countNumSet = new ArrayList<>();
        for (int num : nums) {
            if (helpMap.containsKey(num)) {
                helpMap.put(num, helpMap.get(num) + 1);
            } else {
                helpMap.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : helpMap.entrySet()) {
            countNumSet.add(entry.getValue());
        }
        Collections.sort(countNumSet);
        for (int i = 0; i < k; i++) {
            Iterator<Map.Entry<Integer, Integer>> iterator = helpMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> next = iterator.next();
                if (countNumSet.get(countNumSet.size() - 1 - i).equals(next.getValue())) {
                    returnNums[i] = next.getKey();
                    iterator.remove();
                    break;
                }
            }
        }
        return returnNums;
    } 
</code></pre>
</details>

### 复杂度
+ 时间复杂度：O(k * n)
+ 空间复杂度：O(n) 

------

### 思路二
+ 堆排序，使用优先队列，将出现次数前k的元素存放到优先队列中
### 代码二
<details>
<summary>堆排序，使用优先队列</summary>
<pre>
<code>
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> numsCountMap = new HashMap<>();
        for (int num : nums) {
            numsCountMap.put(num, numsCountMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> numsCountMap.get(o1) - numsCountMap.get(o2));
        for (Integer key : numsCountMap.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
                // 如果当前key对应的次数大于堆中最小的值时，则将堆中最小的值删除，并添加当前的key
            } else if (numsCountMap.get(key) > numsCountMap.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = pq.remove();
        }
        return res;
    }
</code>
</pre>
</details>

### 复杂度
+ 时间复杂度：O(nlogk)
+ 空间复杂度：O(n)

---

### 思路三
+ 桶排序，将出现次数相同的元素放到一个列表中，并使用一个列表数组存放这些列表，列表数组的下标代表列表中元素出现的次数
### 代码三
<details>
<summary>桶排序</summary>
<pre>
<code>
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> numsCountMap = new HashMap<>();
        for (int num : nums) {
            numsCountMap.put(num, numsCountMap.getOrDefault(num, 0) + 1);
        }
        // 数组下标代表所存元素的总个数
        List<Integer>[] list = new List[nums.length + 1];
        for (int key : numsCountMap.keySet()) {
            Integer value = numsCountMap.get(key);
            if (list[value] == null) {
                list[value] = new ArrayList<>();
            }
            list[value].add(key);
        }
        for (int i = list.length - 1, j = 0; i >= 0; i--) {
            if (list[i] == null) continue;
            List<Integer> readyList = list[i];
            for (int l = 0; l < readyList.size() && j < k; l++) {
                res[j++] = readyList.get(l);
            }
        }
        return res;
    }
</code>
</pre>
</details>

### 复杂度
+ 时间复杂度：O(n)
+ 空间复杂度：O(n)