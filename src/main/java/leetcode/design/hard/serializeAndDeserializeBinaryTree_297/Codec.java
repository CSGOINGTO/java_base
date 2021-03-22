package leetcode.design.hard.serializeAndDeserializeBinaryTree_297;

import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    // [1,2,3,null,null,4,5]
    public String serialize(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.offer(root);
        while (!treeNodeQueue.isEmpty()) {
            TreeNode node = treeNodeQueue.poll();
            if (node != null) stringBuilder.append(node.val).append(",");
            
            if (node.left != null) {
                treeNodeQueue.offer(node.left);
            } else {
                treeNodeQueue.offer(null);
            }
            if (node.right != null) {
                treeNodeQueue.offer(node.right);
            } else {
                treeNodeQueue.offer(null);
            }


        }
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        return null;
    }
}
