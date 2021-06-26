### 思路
+ 使用Set存放已经遍历过的节点，如果遍历到的节点在Set中存在，则返回该节点；如果链表已经遍历完，则返回null

### 代码
```java
public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        Set<ListNode> nodeSet = new HashSet<>();
        while(head != null) {
            if (nodeSet.contains(head)) {
                return head;
            }
            nodeSet.add(head);
            head = head.next;
        }
        return null;
    }
```

### 复杂度分析
+ 时间复杂度：O(n)
+ 空间复杂度：O(n)