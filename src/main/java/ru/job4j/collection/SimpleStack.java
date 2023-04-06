package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        T upper = linked.get(0);
        linked.deleteFirst();
        return upper;
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}
