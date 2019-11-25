### 搜索插入位置
#### 题意
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。
#### 解法
1. 最简单的思路：直接遍历数组，当target <= nums[i] 的时候i就是所要插入的位置。或者当target比数组中的所有的元素都大的时候，
返回数组长度。<br/>
这样的时间复杂度是O(n)。<br/>
2. 使用二分查找的方法找到所要插入的位置。时间复杂度为O(log n)。<br/>
**注意：<br/>
(1). 除法可以使用位运算符 >>>(无符号右移)；<br/>
(2). target比数组中的所有的元素都大或者都小的时候。start == 0 或者 start == nums.length。**