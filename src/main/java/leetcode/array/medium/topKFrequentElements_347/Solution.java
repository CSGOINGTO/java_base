package leetcode.array.medium.topKFrequentElements_347;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1, 2}, 2)));
    }


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
}
