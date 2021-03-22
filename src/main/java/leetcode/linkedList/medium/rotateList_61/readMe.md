### 思路
+ 首先获取到链表的长度，以得到旋转最小的k
+ 获得链表的次尾结点和尾结点，每次旋转时，将尾结点的next设置为head，将次尾结点的next设置为null，并将尾结点赋值给head，并进行下一次旋转
+ 需要注意链表的长度不足3时，是没有次尾结点的，这种情况需要单独处理。

### 代码
<details>代码
<pre>
<code>
   public ListNode rotateRight(ListNode head, int k) {
           if (head == null) {
               return head;
           }
           int len = 0;
           ListNode tempHead = head;
           while (tempHead != null) {
               len++;
               tempHead = tempHead.next;
           }
           k = k % len;
           while (k > 0) {
               ListNode tail = null;
               ListNode prevTail = null;
               ListNode newHead = head;
               while (newHead != null) {
                   tail = newHead;
                   newHead = newHead.next;
                   if (newHead != null && newHead.next != null && newHead.next.next == null) {
                       prevTail = newHead;
                   }
               }
               tail.next = head;
               if (prevTail != null) {
                   prevTail.next = null;
               } else {
                   head.next = null;
               }
               head = tail;
               k--;
           }
           return head;
       } 
</code>
</pre>
</details>

### 复杂度分析
+ 时间复杂度：O(k * n)
+ 空间复杂度：O(1)