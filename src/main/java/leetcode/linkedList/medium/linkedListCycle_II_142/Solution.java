package leetcode.linkedList.medium.linkedListCycle_II_142;

import leetcode.linkedList.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        Set<ListNode> nodeSet = new HashSet<>();
        while (head != null) {
            if (nodeSet.contains(head)) {
                return head;
            }
            nodeSet.add(head);
            head = head.next;
        }
        return null;
    }
}
