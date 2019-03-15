package leetcode.linkedList.reverseLinkedList_206;

import leetcode.linkedList.ListNode;

public class Solution {

    public static ListNode reverseList(ListNode head) {
        ListNode res = null;
        while(head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = res;
            res = temp;
        }
        return res;
    }
}
