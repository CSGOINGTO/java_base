### 思路
使用层次遍历(BFS)，将每一层的节点按照从右到左的顺序，当遍历到最后一层的最后一个节点时，该节点的值就是所要的值。

### 代码
```java
    public int findBottomLeftValue(TreeNode root) {
        int val = root.val;
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.offer(root);
        while(!treeNodeQueue.isEmpty()) {
            int levelSize = treeNodeQueue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = treeNodeQueue.poll();
                if (i == levelSize - 1) {
                    val = node.val;
                }
                if (node.right != null) {
                    treeNodeQueue.offer(node.right);
                }
                if (node.left != null) {
                    treeNodeQueue.offer(node.left);
                }
            }
        }
        return val;
    }
``` 
### 复杂度分析
+ 时间复杂度：O(n)
+ 空间复杂度：O(n)