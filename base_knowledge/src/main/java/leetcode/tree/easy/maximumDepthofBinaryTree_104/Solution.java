package leetcode.tree.easy.maximumDepthofBinaryTree_104;

import leetcode.tree.TreeNode;

import java.util.*;

public class Solution {
    /**
     * 方法一：递归
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 方法二：BFS队列迭代
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * 方法三：DFS栈迭代
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int depth = 0;
        while (!stack.isEmpty()) {
            int temp = value.pop();
            depth = Math.max(temp, depth);
            TreeNode treeNode = stack.pop();
            if (treeNode.left != null) {
                stack.push(treeNode.left);
                value.push(temp + 1);
            }
            if (treeNode.right != null) {
                stack.push(treeNode.right);
                value.push(temp + 1);
            }
        }
        return depth;
    }
}
