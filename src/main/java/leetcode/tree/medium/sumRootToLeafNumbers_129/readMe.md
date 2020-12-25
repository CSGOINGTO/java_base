### 思路
在中序遍历的基础上加以改造。 
+ 当遍历到的节点为叶子节点时（left和right都为null），表示遍历到一个新的数值（如何处理这个值的方式有多种）
    + 第一种处理方式是，定义一个List，将得到的新值都放到这个List中；
    + 第二种处理方式是，利用递归栈的特性，将新值暂存到递归栈中，然后在出栈时，再一一弹出栈；
    + 第三种处理方式是，定义一个全局变量，每当得到新值时，将这个新值直接加到全局变量中。
+ 当遍历到的节点不是叶子节点时，将value值*10，并加上当前节点的值，之后再递归遍历当前节点的左右节点

### 代码
```java
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
//        int value = 0;
//        List<Integer> resList = new ArrayList<>();
//        getValue(root, value, resList);
//        int sum = 0;
//        for (Integer integer : resList) {
//            sum += integer;
//        }
//        return sum;
        return getValue1(root, 0);
    }
    
    private void getValue(TreeNode root, int value, List<Integer> resList) {
        if (root != null) {
            value = value * 10 + root.val;
            if (root.left == null && root.right == null) {
                resList.add(value);
            } else {
                getValue(root.left, value, resList);
                getValue(root.right, value, resList);
            }
        }
    }

    private int getValue1(TreeNode root, int value) {
        if (root != null) {
            value = value * 10 + root.val;
            if (root.left == null && root.right == null) {
                return value;
            } else {
                return getValue1(root.left, value) + getValue1(root.right, value);
            }
        }
        return 0;
    }
```
### 复杂度分析
+ 时间复杂度：O(n)
+ 空间复杂度：O(h) h--树的高度