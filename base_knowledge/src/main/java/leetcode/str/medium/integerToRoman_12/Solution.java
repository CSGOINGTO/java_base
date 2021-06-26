package leetcode.str.medium.integerToRoman_12;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().intToRoman(1994));
    }

    /**
     * 方法一：先把数字转化为罗马字符，再根据罗马数字的表示规则进行调整
     */
    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int count_M = num / 1000;
        for (int i = 0; i < count_M; i++) {
            stringBuilder.append("M");
        }
        num -= 1000 * count_M;
        int count_D = num / 500;
        for (int i = 0; i < count_D; i++) {
            stringBuilder.append("D");
        }
        num -= 500 * count_D;
        int count_C = num / 100;
        for (int i = 0; i < count_C; i++) {
            stringBuilder.append("C");
        }
        num -= 100 * count_C;
        int count_L = num / 50;
        for (int i = 0; i < count_L; i++) {
            stringBuilder.append("L");
        }
        num -= 50 * count_L;
        int count_X = num / 10;
        for (int i = 0; i < count_X; i++) {
            stringBuilder.append("X");
        }
        num -= 10 * count_X;
        int count_V = num / 5;
        for (int i = 0; i < count_V; i++) {
            stringBuilder.append("V");
        }
        num -= 5 * count_V;
        for (int i = 0; i < num; i++) {
            stringBuilder.append("I");
        }
        int count = 1;
        for (int i = 1; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == stringBuilder.charAt(i - 1)) {
                count++;
            } else {
                count = 1;
            }
            if (count == 4) {
                if (stringBuilder.charAt(i) == 'C') {
                    if (i - count >= 0 && stringBuilder.charAt(i - count) == 'D') {
                        stringBuilder.replace(i - count, i + 1, "CM");
                        i -= 3;
                    } else {
                        stringBuilder.replace(i - count + 1, i + 1, "CD");
                        i -= 2;
                    }
                }
                if (stringBuilder.charAt(i) == 'X') {
                    if (i - count >= 0 && stringBuilder.charAt(i - count) == 'L') {
                        stringBuilder.replace(i - count, i + 1, "XC");
                        i -= 3;
                    } else {
                        stringBuilder.replace(i - count + 1, i + 1, "XL");
                        i -= 2;
                    }
                }
                if (stringBuilder.charAt(i) == 'I') {
                    if (i - count >= 0 && stringBuilder.charAt(i - count) == 'V') {
                        stringBuilder.replace(i - count, i + 1, "IX");
                        i -= 3;
                    } else {
                        stringBuilder.replace(i - count + 1, i + 1, "IV");
                        i -= 2;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 方法二：贪心算法。将罗马字符按照从大到小的顺序进行列举，然后按照如果当前数字大于罗马字符所表示的数字时，就将罗马字符进行拼接的策略进行拼接，直到当前数字为0结束。
     */
    public String intToRoman1(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < values.length && num > 0; i++) {
            if (num >= values[i]) {
                num -= values[i];
                stringBuilder.append(symbols[i]);
                i--;
            }
        }
        return stringBuilder.toString();
    }
}
