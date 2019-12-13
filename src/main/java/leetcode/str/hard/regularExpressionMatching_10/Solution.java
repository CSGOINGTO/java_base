package leetcode.str.hard.regularExpressionMatching_10;

/**
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(isMatch1("aaa", "a*a")); // true
        System.out.println(isMatch1("ab", ".*c")); //false
        System.out.println(isMatch1("aab", "c*a*b")); // true
        System.out.println(isMatch1("aaa", "ab*ac*a")); // true
        System.out.println(isMatch1("aaa", "ab*a*c*a")); // true
        "aaa".matches("/*");
    }

    public static boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) return true;
        if (s.length() == 0) return false;
        int matchLength = 0;
        int count = 0; // *的个数
        for (int i = 0; i < p.length(); i++) {
            char tempP = p.charAt(i);
            boolean isLen = true;
            while (matchLength < s.length() && isLen) {
                if (tempP == '.') { // 当前正则值为 . 直接跳出循环
                    matchLength++;
                    break;
                } else if (tempP == '*') { // 当前正则值为 * 内循环直到不匹配为止
                    count++;
                    if (i - 1 >= 0) {
                        char tempPrev = p.charAt(i - 1);
                        for (int j = matchLength; j < s.length() && !isOver(s, matchLength, p, i, tempPrev, count); j++) {
                            // . 可以当做任意字符
                            if (tempPrev == '.') {
                                matchLength++;
                                continue;
                            }
                            if (s.charAt(matchLength) != tempPrev) {
                                isLen = false;
                                break;
                            }
                            matchLength++;
                        }
                    } else { // p为 *
                        return false;
                    }
                } else {
                    if (tempP != s.charAt(matchLength)) {  //  *可以匹配0个
                        if (i + 1 < p.length()) {
                            tempP = p.charAt(i + 1);
                            if (tempP == '*') { // 不匹配时看下一个字符是否是 *
                                isLen = false;
                                i++;
                                count += 2;
                                continue;
                            }
                        } else {
                            return false;
                        }
                    }
                    matchLength++;
                    break;
                }
            }
            if (i >= 1) {
                if (isOver(s, matchLength, p, i, p.charAt(i - 1), count)) return true;
            }
//            if (s.length() == matchLength) return false;
        }
        return false;
    }

    /**
     * 判断字符串是否匹配完毕
     *
     * @param str        字符串
     * @param strLength  已经匹配的长度
     * @param pStr       正则表达式
     * @param pStrLength 正则表达式已经匹配的长度
     * @return 是否完毕
     */
    private static boolean isOver(String str, int strLength, String pStr, int pStrLength, char prevChar, int count) {
        if (str.length() < pStr.length() - count) {
            return false;
        }
        if (str.length() == strLength) { // 只有当被匹配的字符串完全匹配时才进行是否匹配完毕的判断
            for (int i = pStrLength + 1; i < pStr.length(); i++) {
                if (pStr.charAt(i) != '*' && (pStr.charAt(i) != prevChar && i + 1 < pStr.length() && pStr.charAt(i + 1) != '*')) {
                    return false;
                }
                pStrLength++;
            }
            return pStrLength == pStr.length() - 1;
        }
        return false;
    }

    public static boolean isMatch1(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) dp[0][i + 1] = true;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j -1];
                    } else {
                        dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
