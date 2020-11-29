### 思路
+ 使用栈numStack记录遇到的数字，使用栈stringStack记录遇到的字符串，使用res表示现阶段拼接出来的字符串
+ 遇到数字时，将数字进行记录
+ 遇到字符串时，将字符串拼接到res的后面
+ 遇到'['时，将数字和res都入栈，并且将数字和res都重新初始化
+ 遇到']时，将栈numStack弹出来一个数字num，并将res重复拼接num次。拼接完之后将栈stringStack弹出来一个字符串与拼接后的res进行拼接，**并将res重置为拼接后的结果**

### 代码
<details>
    <pre><code>
public String decodeString(String s) {
        StringBuilder resSb = new StringBuilder();
        Stack<Integer> stringNums = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        char[] s_chars = s.toCharArray();
        int num = 0;
        for (int i = 0; i < s_chars.length; i++) {
            if (s_chars[i] >= '0' && s_chars[i] <= '9') {
                for (; i < s_chars.length && (s_chars[i] >= '0' && s_chars[i] <= '9'); i++) {
                    num = num * 10 + s_chars[i] - '0';
                }
                i--;
            } else if ((s_chars[i] >= 'A' && s_chars[i] <= 'Z') || (s_chars[i] >= 'a' && s_chars[i] <= 'z')) {
                resSb.append(s_chars[i]);
            } else if (s_chars[i] == '[') {
                stringNums.push(num);
                stringStack.push(resSb.toString());
                num = 0;
                resSb = new StringBuilder();
            } else {
                int numTemp = stringNums.pop();
                String stringTemp = resSb.toString();
                if (stringTemp.length() > 0) {
                    StringBuilder sbTemp = new StringBuilder();
                    for (int j = 0; j < numTemp; j++) {
                        sbTemp.append(stringTemp);
                    }
                    resSb = new StringBuilder(stringStack.pop() + sbTemp.toString());
                }
            }
        }
        return resSb.toString();
    }
    </code></pre>
</details>

### 复杂度分析
+ 时间复杂度：O(n)
+ 空间复杂度：O(n)

