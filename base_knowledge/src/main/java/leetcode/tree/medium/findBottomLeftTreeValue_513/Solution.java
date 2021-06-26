package leetcode.tree.medium.findBottomLeftTreeValue_513;

import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        int val = root.val;
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.offer(root);
        while(!treeNodeQueue.isEmpty()) {
            int levelSize = treeNodeQueue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = treeNodeQueue.poll();
                if (i == levelSize - 1) {
                    val = node.val;
                }
                if (node.right != null) {
                    treeNodeQueue.offer(node.right);
                }
                if (node.left != null) {
                    treeNodeQueue.offer(node.left);
                }
            }
        }
        return val;
    }
}
