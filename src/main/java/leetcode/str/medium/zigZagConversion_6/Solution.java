package leetcode.str.medium.zigZagConversion_6;

public class Solution {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        String[][] hasStr = new String[s.length()][numRows];
        int len = 0;
        // 按列进行遍历
        for (int i = 0; i < s.length(); i++) {
            // 行
            for (int j = 0; j < numRows; j++) {
                if (i % (numRows - 1) == 0) {
                    hasStr[i][j] = String.valueOf(s.charAt(len++));
                    if (len == s.length()) break;
                    continue;
                }
                int a = i % (numRows - 1);
                if (j == numRows - a - 1) {
                    hasStr[i][j] = String.valueOf(s.charAt(len++));
                }
            }
            if (len == s.length()) break;
        }
        StringBuilder stringBuffer = new StringBuilder();
        // 按行输出
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (hasStr[j][i] != null) {
                    stringBuffer.append(hasStr[j][i]);
                }
            }
        }
        return stringBuffer.toString();
    }
}
