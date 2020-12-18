package leetcode.design.medium.LRUCache_146;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Node> getNodeMap;

    private Node head = new Node(-1, -1);

    private Node tail;

    private int size;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.getNodeMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        // 将获取到的valNode放到链表的最后
        if (getNodeMap.containsKey(key)) {
            Node valNode = getNodeMap.get(key);
            if (valNode != tail) {
                Node nextNode = valNode.next;
                valNode.prev.next = nextNode;
                nextNode.prev = valNode.prev;
                insertNode(tail, valNode);
            }
            return valNode.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (capacity > size) {
            // 初始化队列
            if (tail == null) {
                tail = node;
                head.next = tail;
                tail.prev = head;
            } else {
                insertNode(tail, node);
            }
        } else {
            Node removeNode = head.next;
            getNodeMap.remove(removeNode.key);
            // 判断removeNode是否为尾结点
            if (removeNode != tail) {
                head.next = removeNode.next;
                removeNode.next.prev = head;
                insertNode(tail, node);
            } else {
                head.next = node;
                node.prev = head;
                tail = node;
            }
            size--;
        }
        size++;
        getNodeMap.put(key, node);
    }

    private void insertNode(Node tail, Node node) {
        tail.next = node;
        node.prev = tail;
        node.next = null;
        this.tail = node;
    }

    class Node {

        private int key;

        private int val;

        private Node prev;

        private Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.put(2, 1);
        System.out.println(lruCache.get(2));
        lruCache.put(3, 2);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }
}
