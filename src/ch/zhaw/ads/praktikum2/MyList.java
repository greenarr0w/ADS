package ch.zhaw.ads.praktikum2;

import java.util.AbstractList;

public class MyList<T extends Comparable<T>> extends AbstractList<T> {

    protected ListNode head;

    @Override
    public boolean add(T object) {
        ListNode newListNode = new ListNode(object);
        if (head == null) {
            head = newListNode;
        } else {
            ListNode f = head;
            while (f.next != null) {
                f = f.next;
            }
            f.next = newListNode;
        }
        return true;
    }



    @Override
    public boolean remove(Object o) {
        if (!isEmpty() && o != null && o instanceof Comparable) {
            @SuppressWarnings("unchecked")
            Comparable<T> c = (Comparable<T>) o;
            if(c.compareTo(head.object) == 0) {
                head = head.next;
                return true;
            } else {
                ListNode node = head;
                while (node != null && node.next != null) {
                    if (c.compareTo(node.next.object) == 0) {
                        node.next = node.next.next;
                        return true;
                    }
                    node = node.next;
                }
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        ListNode node = null;
        if(!isEmpty()){
            node = head;
            for (int i = 0; i != index; i++) {
                node = node.next;
            }
        }
        if(node == null){
            throw new IndexOutOfBoundsException();
        } else {
            return node.object;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        int count = 0;
        if (!isEmpty()) {
            ListNode node = head;
            while (node != null) {
                count++;
                node = node.next;
            }
        }
        return count;
    }

    class ListNode {
        T object;
        ListNode next;

        ListNode(T object) {
            this.object = object;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n";
    }
}
