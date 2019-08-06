package leetcode.tree.easy.sameTree_100;


import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(1);
        TreeNode q1 = new TreeNode(2);
        TreeNode q2 = new TreeNode(3);
        TreeNode p1 = new TreeNode(2);
        TreeNode p2 = new TreeNode(3);
        q.left = q1;
        q.right = q2;
        p.left = p1;
        p.right = p2;
//        boolean sameTree = Solution.isSameTree(p, q);
//        System.out.println(sameTree);
        boolean sameTree1 = Solution.isSameTree1(p, q);
        System.out.println(sameTree1);
    }

    public static boolean isSameTree1(TreeNode p, TreeNode q) {
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

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }
        if ((p != null && q == null) || (p == null && q != null)) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
