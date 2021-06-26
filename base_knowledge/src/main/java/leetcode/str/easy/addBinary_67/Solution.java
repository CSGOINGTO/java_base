package leetcode.str.easy.addBinary_67;

public class Solution {

    public String addBinary(String a, String b) {
        if ((a == null || a.length() == 0) && (b == null || b.length() == 0)) return "";
        int add = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int nowSeat = add;
            nowSeat += i >= 0 ? a.charAt(i) - '0' : 0;
            nowSeat += j >= 0 ? b.charAt(j) - '0' : 0;
            int newInt = nowSeat % 2;
            sb.append(newInt);
            add = nowSeat / 2;
        }
        if (add != 0) sb.append(add);
        return sb.reverse().toString();
    }
}
