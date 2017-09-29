package ch.zhaw.ads.praktikum1;

import java.util.ArrayList;
import java.util.List;

public class ListStack<T> implements Stack<T> {

    private List<T> list = new ArrayList<T>();

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public T pop() {
        return list.remove(0);
    }

    @Override
    public void push(T o) {
        list.add(0, o);
    }

    @Override
    public T peek() {
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
