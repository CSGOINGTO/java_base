package leetcode.linkedList.medium.swapNodesInPairs_24;

import leetcode.linkedList.ListNode;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode leftNode, rightNode;
        ListNode lastNode = head;
        ListNode res = null;
        while (head != null) {
            leftNode = head;
            rightNode = head.next;
            if (rightNode != null) {
                head = head.next.next;
                if (res == null) {
                    res = rightNode;
                } else {
                    lastNode.next = rightNode;
                }
                rightNode.next = leftNode;
                leftNode.next = null;
                lastNode = leftNode;
            } else {
                if (res == null) {
                    res = leftNode;
                } else {
                    lastNode.next = leftNode;
                    leftNode.next = null;
                }
                break;
            }
        }
        return res;
    }
}
