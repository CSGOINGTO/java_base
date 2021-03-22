package leetcode.linkedList.hard.reverseNodesInKGroup_25;

import leetcode.linkedList.ListNode;

public class Solution {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = null;
        Solution solution = new Solution();
        System.out.println(solution.reverseKGroup(l1, 6));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode resHead = new ListNode(0);
        resHead.next = head;
        ListNode doHead = resHead;
        ListNode startNode = head;
        boolean isReverse = false;
        for (int i = 1; i <= k; i++) {
            head = head.next;
            // 翻转从第1个--第k个节点
            if (head != null && i == k || (head == null && i == k)) {
                startNode = reverseListNode(startNode, doHead, k);
                if (head == null && i == k) break;
                isReverse = true;
                i = 0;
            } else if (head == null) {
                doHead.next = startNode;
                break;
            }
            for (int j = 1; j <= k && isReverse; j++) {
                doHead = doHead.next;
            }
            isReverse = false;
        }
        return resHead.next;
    }


    /**
     * 将包括startNode在内的k个Node翻转，并将翻转之后的下一个节点返回
     */
    private ListNode reverseListNode(ListNode startNode, ListNode doHead, int k) {
        ListNode tmpHead = null;
        for (int i = 1; i <= k; i++) {
            ListNode temNode = startNode.next;
            startNode.next = tmpHead;
            tmpHead = startNode;
            startNode = temNode;
        }
        doHead.next = tmpHead;
        return startNode;
    }


}
