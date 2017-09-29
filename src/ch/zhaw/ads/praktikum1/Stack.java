package ch.zhaw.ads.praktikum1;

public interface Stack<T> {

    boolean isFull();

    boolean isEmpty();

    T pop();

    void push(T t);

    T peek();

}
