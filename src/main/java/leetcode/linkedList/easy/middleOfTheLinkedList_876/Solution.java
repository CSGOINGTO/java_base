package leetcode.linkedList.easy.middleOfTheLinkedList_876;

import leetcode.linkedList.ListNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }
        return slowNode;
    }

    public ListNode middleNode1(ListNode head) {
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        int len = 1;
        while (head != null) {
            nodeMap.put(len++, head);
            head = head.next;
        }
        if ((len - 1) % 2 == 0) {
            return nodeMap.get((len + 2) >>> 1);
        } else {
            return nodeMap.get((len + 1) >>> 1);
        }
    }
}
