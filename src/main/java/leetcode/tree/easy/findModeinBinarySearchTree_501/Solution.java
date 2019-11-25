package leetcode.tree.easy.findModeinBinarySearchTree_501;

import leetcode.tree.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    // [6,2,8,0,4,7,9,null,null,2,6]
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(6);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(8);
        TreeNode treeNode3 = new TreeNode(0);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(7);
        TreeNode treeNode6 = new TreeNode(9);
        TreeNode treeNode7 = new TreeNode(2);
        TreeNode treeNode8 = new TreeNode(6);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;
        treeNode5.left = treeNode7;
        treeNode5.right = treeNode8;
        int[] mode = new Solution().findMode3(treeNode);
        System.out.println(Arrays.toString(mode));
    }

    /**
     * 法一：迭代（第一版（垃圾版））
     *
     * @param root
     * @return
     */
    public static int[] findMode(TreeNode root) {
        Map<Integer, Integer> val_map = new HashMap<>();
        if (root == null) return new int[]{};
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.offer(root);
        while (!treeNodes.isEmpty()) {
            TreeNode tempNode = treeNodes.poll();
            if (!val_map.containsKey(tempNode.val)) {
                val_map.put(tempNode.val, 1);
            } else {
                val_map.put(tempNode.val, val_map.get(tempNode.val) + 1);
            }
            if (tempNode.left != null) treeNodes.offer(tempNode.left);
            if (tempNode.right != null) treeNodes.offer(tempNode.right);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(val_map.entrySet());
        if (list.size() == 1) {
            return new int[]{list.get(0).getKey()};
        }
        Collections.sort(list, (o1, o2) -> o1.getValue() - o2.getValue());
        int max = list.get(list.size() - 1).getValue();
        List<Integer> helpList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getValue() == max) {
                if (!helpList.contains(list.get(i).getKey())) {
                    helpList.add(list.get(i).getKey());
                }
            }
        }
        int[] res = new int[helpList.size()];
        for (int i = 0; i < helpList.size(); i++) {
            res[i] = helpList.get(i);
        }
        return res;
    }

    /**
     * 法二：迭代（第二版（稍微优化版））
     *
     * @param root
     * @return
     */
    public static int[] findMode2(TreeNode root) {
        Map<Integer, Integer> val_map = new HashMap<>();
        if (root == null) return new int[]{};
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.offer(root);
        int max = 0;
        while (!treeNodes.isEmpty()) {
            TreeNode tempNode = treeNodes.poll();
            if (!val_map.containsKey(tempNode.val)) {
                val_map.put(tempNode.val, 1);
            } else {
                val_map.put(tempNode.val, val_map.get(tempNode.val) + 1);
            }
            max = Math.max(val_map.get(tempNode.val), max);
            if (tempNode.left != null) treeNodes.offer(tempNode.left);
            if (tempNode.right != null) treeNodes.offer(tempNode.right);
        }
        List<Integer> helpList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : val_map.entrySet()) {
            if (entry.getValue() == max) {
                helpList.add(entry.getKey());
            }
        }
        int[] res = new int[helpList.size()];
        for (int i = 0; i < helpList.size(); i++) {
            res[i] = helpList.get(i);
        }
        return res;
    }

    // 注意递归时，局部变量和全局变量的作用域
    private int max = 0;

    /**
     * 方法三： 递归调用（中序遍历）
     *
     * @param root
     * @return
     */
    public int[] findMode3(TreeNode root) {
        Map<Integer, Integer> tree_map = new HashMap<>();
        if (root == null) return new int[]{};
        dfs(root, tree_map);
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : tree_map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void dfs(TreeNode root, Map<Integer, Integer> tree_map) {
        if (root.left != null) {
            dfs(root.left, tree_map);
        }
        // 前序调用时判断root的左右节点肯定不为null时才调用，因此当前root节点肯定不会为null（第一次调用时，调用方传入的root不为null）
        tree_map.put(root.val, tree_map.getOrDefault(root.val, 0) + 1);
        max = Math.max(max, tree_map.get(root.val));

        if (root.right != null) {
            dfs(root.right, tree_map);
        }
    }
}
