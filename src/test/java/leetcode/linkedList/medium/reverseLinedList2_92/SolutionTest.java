package leetcode.linkedList.medium.reverseLinedList2_92;

import leetcode.linkedList.ListNode;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void reverseBetween() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = null;

        Solution solution = new Solution();
        ListNode listNode = solution.reverseBetween(a, 2, 4);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}