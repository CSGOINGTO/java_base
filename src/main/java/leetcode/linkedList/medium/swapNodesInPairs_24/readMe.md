### 思路

---
1 --> 2 --> 3 --> 4 --> 5 -- ^
---
    1. 
        leftNode = 1  
        rightNode = 2  
        res = 2  
        lastNode = 1  
        2 --> 1  

    2. 
        leftNode = 3  
        rightNode = 4  
        res = 2  
        lastNode = 3  
        2 --> 1 --> 4 --> 3

    3. 
        leftNode = 5  
        rightNode = ^  
        res = 2  
        lastNode = 5  
        2 -- 1 --> 4 --> 3 --> 5 --> ^


+ 使用res表示返回链表的头节点，使用lastNode表示当前已经处理链表的最后一个链表
+ 将已存在的链表的leftNode和rightNode的指向进行交换，并将lastNode指向leftNode，并将leftNode的next置为null

### 代码
```java
public ListNode swapPairs(ListNode head) {
        ListNode leftNode, rightNode;
        ListNode lastNode = head;
        ListNode res = null;
        while (head != null) {
            leftNode = head;
            rightNode = head.next;
            if (rightNode != null) {
                head = head.next.next;
                if (res == null) {
                    res = rightNode;
                } else {
                    lastNode.next = rightNode;
                }
                rightNode.next = leftNode;
                leftNode.next = null;
                lastNode = leftNode;
            } else {
                if (res == null) {
                    res = leftNode;
                } else {
                    lastNode.next = leftNode;
                    leftNode.next = null;
                }
                break;
            }
        }
        return res;
    }
```

### 复杂度分析
+ 时间复杂度：O(n)
+ 空间复杂度：O(1)