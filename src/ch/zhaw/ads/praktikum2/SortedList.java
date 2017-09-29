package ch.zhaw.ads.praktikum2;

public class SortedList<T extends Comparable<T>> extends MyList<T> {

    private final boolean asc;

    public SortedList(final boolean asc) {
        this.asc = asc;
    }

    @Override
    public boolean add(T object) {
        ListNode newListNode = new ListNode(object);
        if (head == null) {
            head = newListNode;
        } else if (head.next == null) {
            if (asc) {
                if (head.object.compareTo(object) > 0) {
                    newListNode.next = head;
                    head = newListNode;
                } else {
                    head.next = newListNode;
                }
            } else {
                if (head.object.compareTo(object) < 0) {
                    newListNode.next = head;
                    head = newListNode;
                } else {
                    head.next = newListNode;
                }
            }
        } else {
            if (asc) {
                if (head.object.compareTo(object) > 0) {
                    newListNode.next = head;
                    head = newListNode;
                    return true;
                }
            } else {
                if (head.object.compareTo(object) < 0) {
                    newListNode.next = head;
                    head = newListNode;
                    return true;
                }
            }

            ListNode node = head;
            while (node.next != null) {
                if (asc) {
                    if (node.next.object.compareTo(object) > 0) {
                        newListNode.next = node.next;
                        node.next = newListNode;
                        return true;
                    }
                } else {
                    if (node.next.object.compareTo(object) < 0) {
                        newListNode.next = node.next;
                        node.next = newListNode;
                        return true;
                    }
                }
                node = node.next;
            }
            node.next = newListNode;
        }
        return true;
    }
}
