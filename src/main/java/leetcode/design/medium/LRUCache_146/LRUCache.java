package leetcode.design.medium.LRUCache_146;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Node> getNodeMap;

    private Node head = new Node();

    private Node tail = new Node();

    private int size;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.getNodeMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        // 存在key所对应的value
        Node valNode = getNodeMap.get(key);
        if (valNode != null) {
            deleteNode(valNode);
            // 将对应的node插入到tail前
            insertTailPrev(valNode);
            return valNode.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node keyNode = getNodeMap.get(key);
        // 如果key在链表中存在有对应的Node
        if (keyNode != null) {
            // 更新value值
            keyNode.val = value;
            // 将node从链表中删除
            deleteNode(keyNode);
            // 将node插入到尾结点之前
            insertTailPrev(keyNode);
        } else {
            Node newNode = new Node(key, value);
            // 判断是否还有存储空间
            // 有存储空间
            if (size < capacity) {
                // 初始化链表
                if (head.next == null) {
                    head.next = newNode;
                    newNode.prev = head;
                    newNode.next = tail;
                    tail.prev = newNode;
                } else {
                    // 直接在tail前插入新节点
                    insertTailPrev(newNode);
                }
            } else {
                // 没有存储空间
                // 删除head之后的节点
                Node deleteNode = head.next;
                getNodeMap.remove(deleteNode.key);
                size--;
                // 插入新节点
                insertTailPrev(newNode);
                // 删除deleteNode
                deleteNode(deleteNode);
            }
            size++;
            getNodeMap.put(key, newNode);
        }
    }

    private void insertTailPrev(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    private void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    class Node {

        private int key;

        private int val;

        private Node prev;

        private Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.print();
        lruCache.put(2, 2);
        lruCache.print();
        System.out.println(lruCache.get(1));
        lruCache.print();
        lruCache.put(3, 3);
        lruCache.print();
        System.out.println(lruCache.get(2));
        lruCache.print();
        lruCache.put(4, 4);
        lruCache.print();
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    private void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
