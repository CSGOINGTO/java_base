package offer.lianBiaoZhongDaoShuDiKgeJieDianLcof_22;

import leetcode.linkedList.ListNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public ListNode getKthFromEnd1(ListNode head, int k) {
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        ListNode cur = head;
        int index = 1;
        while(cur != null) {
            nodeMap.put(index++, cur);
            cur = cur.next;
        }
        return nodeMap.get(index - 1 - k + 1);
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode former = head, latter = head;
        for(int i = 0; i < k; i++)
            former = former.next;
        while(former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }
}
