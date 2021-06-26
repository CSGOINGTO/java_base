package leetcode.array.medium.topKFrequentElements_347;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1, 2}, 2)));
    }

    /**
     * 愚蠢而又直观的方法
     */
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

    /**
     * 使用最小堆的方法
     */
    public int[] topKFrequent1(int[] nums, int k) {
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

    /**
     * 桶排序
     */
    public int[] topKFrequent2(int[] nums, int k) {
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
}
