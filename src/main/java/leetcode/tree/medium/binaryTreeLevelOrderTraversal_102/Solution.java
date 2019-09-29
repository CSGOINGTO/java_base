package leetcode.tree.medium.binaryTreeLevelOrderTraversal_102;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> temp_list = new ArrayList<>();
            while (count > 0) {
                TreeNode temp_treeNode = queue.poll();
                if (temp_treeNode.left != null) {
                    queue.offer(temp_treeNode.left);
                }
                if (temp_treeNode.right != null) {
                    queue.offer(temp_treeNode.right);
                }
                temp_list.add(temp_treeNode.val);
                count--;
            }
            res.add(temp_list);
        }
        return res;
    }
}
