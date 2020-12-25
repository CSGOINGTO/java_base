package leetcode.tree.medium.sumRootToLeafNumbers_129;

import leetcode.tree.TreeNode;

import java.util.List;

public class Solution {

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

    /**
     * 中序遍历的思路（可以将list修改为定义一个int全局变量）
     * 先处理当前遍历到的节点的值，然后再分别处理左子节点和右子节点
     */
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

    /**
     * 利用递归栈的特性，将返回值保存在递归栈中，之后再一一弹出进行处理
     */
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
}
