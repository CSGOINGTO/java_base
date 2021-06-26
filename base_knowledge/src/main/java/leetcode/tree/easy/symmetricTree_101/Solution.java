package leetcode.tree.easy.symmetricTree_101;

import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(3);
        a.left = b;
        a.right = c;
        b.right = d;
        c.left = e;
        boolean symmetric = Solution.isSymmetric1(a);
        System.out.println(symmetric);
    }

    /**
     * 方法一：递归解法
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelp(root.left, root.right);
    }

    private static boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if ((left == null && right != null) || (left != null && right == null)) {
            return false;
        }
        if (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
        }
        return true;
    }

    /**
     * 方法二：DFS栈
     * @return
     */
    public static boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();
        TreeNode curLeft = root.left;
        TreeNode curRight = root.right;
        while (curLeft != null || !stackLeft.isEmpty() || curRight!=null || !stackRight.isEmpty()) {
            // 节点不为空一直压栈
            while (curLeft != null) {
                stackLeft.push(curLeft);
                curLeft = curLeft.left; // 考虑左子树
            }
            while (curRight != null) {
                stackRight.push(curRight);
                curRight = curRight.right; // 考虑右子树
            }
            //长度不同就返回 false
            if (stackLeft.size() != stackRight.size()) {
                return false;
            }
            // 节点为空，就出栈
            curLeft = stackLeft.pop();
            curRight = stackRight.pop();

            // 当前值判断
            if (curLeft.val != curRight.val) {
                return false;
            }
            // 考虑右子树
            curLeft = curLeft.right;
            curRight = curRight.left;
        }
        return true;
    }

    /**
     * 方法三：BFS队列
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> leftTree = new LinkedList<>();
        Queue<TreeNode> rightTree = new LinkedList<>();
        //两个树的根节点分别加入
        leftTree.offer(root.left);
        rightTree.offer(root.right);
        while (!leftTree.isEmpty() && !rightTree.isEmpty()) {
            TreeNode curLeft = leftTree.poll();
            TreeNode curRight = rightTree.poll();
            if (curLeft == null && curRight != null || curLeft != null && curRight == null) {
                return false;
            }
            if (curLeft != null && curRight != null) {
                if (curLeft.val != curRight.val) {
                    return false;
                }
                //先加入左子树后加入右子树
                leftTree.offer(curLeft.left);
                leftTree.offer(curLeft.right);

                //先加入右子树后加入左子树
                rightTree.offer(curRight.right);
                rightTree.offer(curRight.left);
            }

        }
        if (!leftTree.isEmpty() || !rightTree.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 方法四：
     * @param root
     * @return
     */
    public static boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode left_treeNode = queue.poll();
            TreeNode right_treeNode = queue.poll();
            if (left_treeNode == null && right_treeNode == null) {
                continue;
            }
            if (left_treeNode == null || right_treeNode == null) {
                return false;
            }
            queue.offer(left_treeNode.left);
            queue.offer(right_treeNode.right);
            queue.offer(left_treeNode.right);
            queue.offer(right_treeNode.left);
        }
        return true;
    }
}