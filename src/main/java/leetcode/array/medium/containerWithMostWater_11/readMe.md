### 盛最多水的容器
#### 题意
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。
![](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg)
图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

#### 题解
本题的目标是找到两条线段之间的最大面积，而两条线段之间的面积取决于最短的那条线段。  
1. 法一：暴力法。  
依次遍历数组，找到最大的面积。时间复杂度是O(n2),空间复杂度是O(1)。
2. 法二：双指针法。
这种方法背后的思路在于，两线段之间形成的区域总是会受到其中较短那条长度的限制。此外，两线段距离越远，得到的面积就越大。

我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。 此外，我们会使用变量 maxareamaxarea 来持续存储到目前为止所获得的最大面积。 在每一步中，我们会找出指针所指向的两条线段形成的区域，更新 maxareamaxarea，并将指向较短线段的指针向较长线段那端移动一步。

查看下面的例子将有助于你更好地理解该算法：

1 8 6 2 5 4 8 3 7

![avatar](data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAfkAAAEcCAYAAADEP83VAAAgAElEQVR4Xu2dCZwcRdn/fz09xx7ZI9ncCSQBcmE4EyCIhqBchixqfIGIcgQIioqA3Mjx5/BVDuXSFwFFOcKlGNAkICIQgURIQk4CuUNCNtceOfaYq7v/n+rZ2czOzsx2z0zN9Mz++v/5v2q26umq71Pdv3mqq55SkOQyDMNI9jf+OwmQAAmQAAmQgLMIKIqixLeoyz9EC1DkneU8toYESIAESIAEUhGgyHN8kAAJkAAJkECREqDIF6lj2S0SIAESIAESoMhzDJAACZAACZBAkRKgyBepY9ktEiABEiABEqDIcwyQAAmQAAmQQJESoMgXqWPZLRIgARIgARKgyHMMkAAJkAAJkECREqDIF6lj2S0SIAESIAESoMhzDJAACZAACZBAkRKgyBepY9ktEiABEiABEqDIcwyQAAmQAAmQQJESoMgXqWPZLRIgARIgARKgyHMMkAAJkAAJkECREqDIF6lj2S0SIAESIAESoMhzDJAACZAACZBAkRKgyBepY9ktEiABEiABEqDIcwyQAAmQAAmQQJESoMgXqWPZLRIgARIgARKgyHMMkAAJkAAJkECREqDIF6lj2S0SIAESIAESoMhzDJAACZAACZBAkRKgyBepY9ktEiABEiABEqDIcwyQAAmQAAmQQJESoMgXqWPZLRIgARIgARKgyHMMkAAJkAAJkECREqDIF6lj2S0SIAESIAESoMhzDJAACZAACZBAkRKgyBepY9ktEiABEiABEqDIcwyQAAmQAAmQQJESoMgXqWPZLRIgARIgARKgyHMMkAAJkAAJkECREqDIF6lj2S0SIAESIAESoMhzDJAACZAACZBAkRKgyBepY9ktEiABEiABEqDIcwyQAAmQAAmQQJESoMgXqWPZLRIgARIgARKgyHMMkAAJkAAJkECREqDIF6lj2S0SIAESIAESoMhzDJAACZAACZBAkRKgyBepY9ktEiABEiABEqDIcwyQAAmQAAmQQJESoMgXqWPZLRIgARIgARKgyHMMkAAJkAAJkECREqDIF6lj2S0SIAESIAESoMhbHAOGYaChoQFNTU3o168fqqurLdZkMRIgARIgARLIDwGKvEXuO3bX449PPAEYOvyBIK6++irU1NRYrM1iJEACJEACJJB7AhR5i8zff/99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)  
最初我们考虑由最外围两条线段构成的区域。现在，为了使面积最大化，我们需要考虑更长的两条线段之间的区域。如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小。  
时间复杂度O(n)，空间复杂度O(1)。