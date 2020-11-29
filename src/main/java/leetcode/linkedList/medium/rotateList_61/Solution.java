package leetcode.linkedList.medium.rotateList_61;

import leetcode.linkedList.ListNode;

public class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int len = 0;
        ListNode tempHead = head;
        while (tempHead != null) {
            len++;
            tempHead = tempHead.next;
        }
        k = k % len;
        while (k > 0) {
            ListNode tail = null;
            ListNode prevTail = null;
            ListNode newHead = head;
            while (newHead != null) {
                tail = newHead;
                newHead = newHead.next;
                if (newHead != null && newHead.next != null && newHead.next.next == null) {
                    prevTail = newHead;
                }
            }
            tail.next = head;
            if (prevTail != null) {
                prevTail.next = null;
            } else {
                head.next = null;
            }
            head = tail;
            k--;
        }
        return head;
    }

}
