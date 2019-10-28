package leetcode.tree.easy.pathSum_112;

import leetcode.tree.TreeNode;

public class Solution {

    /**
     * 解法一：通过加法递归
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return help(root, 0, sum);
    }

    private boolean help(TreeNode root, int count, int sum) {
        if (root == null) return false;
        count += root.val;
        if (count == sum && root.left == null && root.right == null) {
            return true;
        }
        if (root.left != null && root.left.val + count == sum && root.left.left == null && root.left.right == null) {
            return true;
        }
        if (root.right != null && root.right.val + count == sum && root.right.left == null && root.right.right == null) {
            return true;
        }
        return help(root.left, count, sum) || help(root.right, count, sum);
    }

    /**
     * 解法二：通过减法递归
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) return false;
        if (sum - root.val == 0 && root.left == null && root.right == null) {
            return true;
        }
        sum -= root.val;
        return hasPathSum1(root.left, sum) || hasPathSum1(root.right, sum);
    }
}
