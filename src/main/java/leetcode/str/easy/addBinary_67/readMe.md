### 67. 二进制求和
#### 题意：
给定两个二进制字符串，返回他们的和（用二进制表示）。  
输入为非空字符串且只包含数字 1 和 0。  
示例 1:
> 输入: a = "11", b = "1"  
> 输出: "100"

示例 2:
> 输入: a = "1010", b = "1011"  
> 输出: "10101"

#### 题解：
注意点：
   1. 需要维护一个进位
   2. 需要判断两个字符串的长度
   3. 如果得到的是一个翻转的字符串，最后结果需要翻转过来