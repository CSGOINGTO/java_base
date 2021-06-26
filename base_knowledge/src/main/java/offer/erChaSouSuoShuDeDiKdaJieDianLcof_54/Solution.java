package offer.erChaSouSuoShuDeDiKdaJieDianLcof_54;

import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(3);
        TreeNode l2 = new TreeNode(1);
        TreeNode l3 = new TreeNode(4);
        TreeNode l4 = new TreeNode(2);
        l1.left = l2;
        l1.right = l3;
        l2.left = l4;
        Solution solution = new Solution();
        System.out.println(solution.kthLargest(l1, 3));
    }

    public int kthLargest(TreeNode root, int k) {
        Queue<Integer> queue = new PriorityQueue<>(k);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            if (queue.size() == k && node.val > queue.peek()) {
                queue.poll();
            }
            if (queue.size() < k) {
                queue.offer(node.val);
            }
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        return queue.peek();
    }
}
