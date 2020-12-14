### 思路
+ 使用快慢指针找到链表中的中间节点作为树的root节点
+ root节点的左右子节点，分别是链表左右子链表的中间节点（最终的目的就是找到中间节点，非常符合递归的解题思路）
### 代码
<details>
<summary>代码</summary>

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return help(head, null);
    }
    
    private TreeNode help(ListNode startNode, ListNode endNode) {
        if (startNode == endNode) return null;
        ListNode fastNode = startNode;
        ListNode slowNode = startNode;
        while (fastNode != endNode && fastNode.next != endNode) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        TreeNode rootNode = new TreeNode(slowNode.val);
        rootNode.left = help(startNode, slowNode);
        rootNode.right = help(slowNode.next, endNode);
        return rootNode;
    }
</details>

### 复杂度分析
+ 时间复杂度：O(n*logn)
+ 空间复杂度：O(n)