package leetcode.tree.easy.sameTree_100;


import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * 方法一：采用层次遍历的方式
     */
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p != null && q == null) || (p == null && q != null)) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode temp1 = queue.poll();
            TreeNode temp2 = queue.poll();
            if ((temp1 == null && temp2 != null) || (temp1 != null && temp2 == null)) {
                return false;
            }
            if (temp1 != null && temp2 != null) {
                if (temp1.val != temp2.val) {
                    return false;
                }
                queue.add(temp1.left);
                queue.add(temp2.left);
                queue.add(temp1.right);
                queue.add(temp2.right);
            }
        }
        return true;
    }

    /**
     * 方法二：采用递归的方法
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }
        if ((p != null && q == null) || (p == null && q != null)) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
