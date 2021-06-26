package leetcode.tree.easy.balancedBinaryTree_110;

import leetcode.tree.TreeNode;

public class Solution {
    /**
     * 方法一：递归高级版（从底到顶）
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * 方法二：递归
     * @param root
     * @return
     */
    public boolean isBalanced1(TreeNode root) {
        if (root == null) return true;
        int leftHeight = getHeight1(root.left);
        int rightHeight = getHeight1(root.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced1(root.left) && isBalanced1(root.right);
    }

    private int getHeight1(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
