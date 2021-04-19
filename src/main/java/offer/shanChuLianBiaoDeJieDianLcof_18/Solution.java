package offer.shanChuLianBiaoDeJieDianLcof_18;

import leetcode.linkedList.ListNode;

public class Solution {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        Solution solution = new Solution();
        System.out.println(solution.deleteNode(l1, 1));

    }


    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        ListNode topHead = new ListNode(-1);
        topHead.next = head;
        while (topHead.next != null) {
            if (topHead.next.val == val) {
                topHead.next = topHead.next.next;
                if (head.val == val) {
                    return head.next;
                }
                return head;
            }
            topHead = topHead.next;
        }
        return head;
    }
}
