package offer.fuZaLianBiaoDeFuZhiLcof_35;

import leetcode.linkedList.Node;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        Node n1 = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);
        n1.next = n2;
        n1.random = null;
        n2.next = n3;
        n2.random = n1;
        n3.next = n4;
        n3.random = n5;
        n4.next = n5;
        n4.random = n3;
        n5.next = null;
        n5.random = n1;
        Solution solution = new Solution();
        System.out.println(solution.copyRandomList2(n1));

    }

    public Node copyRandomList2(Node head) {
        if (head == null) return head;
        Node cur = head;
        // 将新节点插入到之前的链表，1 -> new1 -> 2 -> new2 -> 3 -> new3 -> null
        while (cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        cur = head;
        // 按照原链表的规则，将新节点的random指向新的节点
        // 1 -> new1 -> 2 -> new2 -> 3 -> new3 -> null
        // ↓            ↓            ↓
        // 2 -> new2    3 -> new3    1 -> new1
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // 拆分链表
        cur = head.next;
        Node pre = head, res = head.next;
        while (cur.next != null) {
            pre.next = pre.next.next;
            pre = pre.next;
            cur.next = cur.next.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> node2NewNodeMap = new HashMap<>();
        Node newHead = new Node(-1);
        Node oldHead = head;
        Node curHead = newHead;
        while (head != null) {
            newHead.next = new Node(head.val);
            node2NewNodeMap.put(head, newHead.next);
            newHead = newHead.next;
            head = head.next;
        }
        Node tmpHead = curHead;
        while (tmpHead.next != null) {
            tmpHead.next.random = node2NewNodeMap.get(oldHead.random);
            tmpHead = tmpHead.next;
            oldHead = oldHead.next;
        }
        return curHead.next;
    }
}
