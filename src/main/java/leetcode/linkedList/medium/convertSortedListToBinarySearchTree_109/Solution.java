package leetcode.linkedList.medium.convertSortedListToBinarySearchTree_109;

import leetcode.linkedList.ListNode;
import leetcode.tree.TreeNode;

public class Solution {

    public static void main(String[] args) {
        ListNode head = new ListNode(-10);
        ListNode head1 = new ListNode(-3);
        ListNode head2 = new ListNode(0);
        ListNode head3 = new ListNode(5);
        ListNode head4 = new ListNode(9);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next= null;
        Solution solution = new Solution();
        System.out.println(solution.sortedListToBST(head));
    }

    /**
     * 方法一：递归法
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return help(head, null);
    }

    private TreeNode help(ListNode startNode, ListNode endNode) {
        if (startNode == endNode) return null;
        ListNode fastNode = startNode;
        ListNode slowNode = startNode;
        while (fastNode != endNode && fastNode.next != endNode) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        TreeNode rootNode = new TreeNode(slowNode.val);
        rootNode.left = help(startNode, slowNode);
        rootNode.right = help(slowNode.next, endNode);
        return rootNode;
    }
}
