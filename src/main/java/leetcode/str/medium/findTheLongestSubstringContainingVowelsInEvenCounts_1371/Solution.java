package leetcode.str.medium.findTheLongestSubstringContainingVowelsInEvenCounts_1371;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findTheLongestSubstring("eleetminicoworoep"));
    }

    public int findTheLongestSubstring(String s) {
        int n = s.length();
        //1左移5次 相当于2^5，从00000-11111种状态，代表5个元音字母的个数是否为偶数，0代表偶数，1代表奇数
        int[] pos = new int[1 << 5];
        //把数组用-1填充，区分00000的情况
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        //有n个字符 循环n次，状态值初始为0
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                //与1异或，相同为0，不同为1
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            //如果status对应的pos[status]大于0 说明已经找到符合要求的子串
            //因为两个子串的奇偶性相等，说明中间子串是符合要求的。
            //奇偶性相同的两个数的差，必定为偶数 只会有一个偶数00000
            //因此出现两个相同状态的数，他们中间必定出现了偶数次数的aeiou
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                //pos[status]==-1 说明status是第一次出现，只保存这个值
                pos[status] = i + 1;
            }
        }
        return ans;
    }
}
