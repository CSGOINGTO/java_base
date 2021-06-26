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

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = null;
        Solution solution = new Solution();
        System.out.println(solution.reverseBetween1(l1, 3, 5));
    }

    public ListNode reverseBetween1(ListNode head, int m, int n) {
        /*
            1. 设置一个头结点resHead，其next为head，同时设置另外一个新的节点doHead（具体干活的节点）与resHead指向相同的对象。
            2. 将head遍历到第m个节点startHead，将m--n中的节点进行翻转。（在遍历的同时，将head指向下一个节点，遍历完之后，head指向的是原链表中第n个节点的下一个节点）
            3. 将doHead遍历到第n个节点处，并将其下一个节点指向head。
            4. 返回resHead的next节点
         */

        ListNode resHead = new ListNode(0);
        resHead.next = head;
        ListNode doHead = resHead;
        for (int i = 1; i < m; i++) {
            head = head.next;
            doHead = doHead.next;
        }
        ListNode startHead = null;
        for (int i = 1; i <= n - m + 1; i++) {
            ListNode tmpNode = head.next;
            head.next = startHead;
            startHead = head;
            head = tmpNode;
        }
//        doHead = doHead.next;
        doHead.next = startHead;
        // doHead头结点，遍历到当前head的前一个节点
        for (int i = 1; i <= n - m + 1; i++) {
            doHead = doHead.next;
        }
        doHead.next = head;
        return resHead.next;
    }
}
