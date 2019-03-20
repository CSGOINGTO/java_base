package dataStructure_base.linkedList;

/**
 * 链表基础
 */
public class BasicKnowledge {
    public static void main(String[] args) {
        Node a = new Node();
        Node b = new Node();
        Node c = new Node();
        Node d = new Node();
        Node e = new Node();
        a.val = 10;
        a.next = b;
        b.val = 20;
        b.next = c;
        c.val = 30;
        c.next = d;
        d.val = 40;
        d.next = e;
        e.val = 50;
        e.next = null;
        Node temp = a;
        while (a != null) {
            System.out.println("xxxx" + a.val);
            a = a.next;
        }
        while(temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
}

class Node {
    int val;
    Node next;

    public Node() {
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}