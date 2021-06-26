package leetcode.tree.medium.binaryTreePreorderTraversal_144;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    private List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node2.left = node3;
        node2.right = node4;
        node1.right = node5;
        node5.right = node6;
        Solution solution = new Solution();
        System.out.println(solution.preorderTraversal1(node1));
    }


    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            // 如果当前节点不为null
            if (root != null) {
                // 将当前节点的值加入到结果list
                res.add(root.val);
                // 将当前节点push到stack
                stack.push(root);
                // 遍历当前节点的左子节点
                root = root.left;
            } else {
                // 如果当前节点为null，则从stack中pop出一个节点，并遍历该节点的右子节点
                TreeNode tmp = stack.pop();
                root = tmp.right;
            }
        }
        return res;
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return ans;
        ans.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return ans;
    }
}
