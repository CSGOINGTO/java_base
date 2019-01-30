package leetcode.atoi_08;

import java.math.BigInteger;

public class Atoi_08 {

    public static void main(String[] args) {
        Atoi_08 atoi_08 = new Atoi_08();
        int myAtoi = atoi_08.myAtoi1("+");
        System.out.println(myAtoi);
    }

    public int myAtoi1(String str) {
        str = str.trim();
        if(str.length() == 0) {
            return 0;
        }
        char[] str_chars = str.toCharArray();
        boolean isPositive = false;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if(i == 0) {
                if(str_chars[i] == '-') {
                    isPositive = false;
                    continue;
                } else if (str_chars[i] == '+') {
                    isPositive = true;
                    continue;
                } else if (str_chars[i] >= '0' && str_chars[i] <= '9'){
                    sb.append(str_chars[i]);
                    continue;
                } else {
                    return 0;
                }
            }
            if(str_chars[i] >= '0' && str_chars[i] <= '9') {
                sb.append(str_chars[i]);
            } else {
                break;
            }
        }
        // 数字字符串
        str = sb.toString();
        if(str.length() == 0) {
            return 0;
        }
        // 如果是负数
        if (!isPositive) {
            long res = 0;
            if(str.length() > 10) {
                return Integer.MIN_VALUE;
            } else if (str.length() == 10) {
                res = Long.valueOf(str);
                res = res < Integer.MIN_VALUE ? Integer.MIN_VALUE : res;
                return (int)res;
            } else {
                return -Integer.valueOf(str);
            }
        }
        // 正数
        if(str.length() > 10) {
            return Integer.MAX_VALUE;
        } else if(str.length() == 10) {
            long res = Long.valueOf(str);
            res = res > Integer.MAX_VALUE ? Integer.MAX_VALUE : res;
            return (int)res;
        } else {
            return Integer.valueOf(str);
        }
    }

    public int myAtoi(String str) {
        str = str.trim();
        char[] str_chars = str.toCharArray();
        if(str_chars.length == 0 || ('-' != str_chars[0] && !('0' <= str_chars[0] && '9' >= str_chars[0]))) {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str_chars.length; i++) {
            if(i == 0 && str_chars[i] == '-') {
                sb.append('-');
                continue;
            }
            if(str_chars[i] >= '0' && str_chars[i] <= '9') {
                sb.append(str_chars[i]);
            } else {
                break;
            }
        }
        str = sb.toString();
        if(str.equals("-")) {
            return 0;
        }
        BigInteger str_num = new BigInteger(str);
        BigInteger int_max = new BigInteger(String.valueOf(Integer.MAX_VALUE));
        BigInteger int_min = new BigInteger(String.valueOf(Integer.MIN_VALUE));
        if(str_chars[0] == '-') {
            BigInteger res_min = str_num.max(int_min);
            return res_min.intValue();
        }
        return str_num.min(int_max).intValue();
    }
}
