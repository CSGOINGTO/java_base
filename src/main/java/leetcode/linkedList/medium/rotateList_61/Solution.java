package leetcode.linkedList.medium.rotateList_61;

import leetcode.linkedList.ListNode;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = null;
        l3.next = l4;
        l4.next = l5;
        l5.next = null;
        System.out.println(solution.rotateRight1(l1, 2));
    }

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

    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null || k == 0) return head;
        ListNode tmpHead = head;
        int len = 1;
        // 找到最后一个节点，并获取整个链表的长度
        while (head.next != null) {
            head = head.next;
            len++;
        }
        k = k % len;
        if (k == 0) return tmpHead;
        // 将最后一个节点与第一个节点相连接，链表形成一个环
        head.next = tmpHead;
        ListNode newHead = tmpHead;
        ListNode resHead = newHead;
        for (int i = 0; i < k; i++) {
            while (newHead.next != head) {
                newHead = newHead.next;
            }
            resHead = head;
            head = newHead;
        }
        newHead.next = null;
        return resHead;
    }
}
