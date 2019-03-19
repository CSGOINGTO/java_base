package leetcode.linkedList.medium.reverseLinedList2_92;

import leetcode.linkedList.ListNode;

public class Solution {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode res = new ListNode(0);
        ListNode res1 = res;
        res.next = head;
        for(int i = 1; i < m; i++) {
            head = head.next;
        }
        ListNode startNode = head;
        for(int i = 0; i < n - m; i++) {
            head = head.next;
        }
        ListNode lastNode = head.next;
        for(int i = 0; i <= n - m;i++) {
            ListNode temp = startNode.next;
            startNode.next = lastNode;
            lastNode = startNode;
            startNode = temp;
        }
        for (int i = 1; i < m; i++) {
            res = res.next;
        }
        res.next = lastNode;
        return res1.next;
    }
}
