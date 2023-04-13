package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int size = 0;
    boolean inFull = true;


    public T poll() {
        if (inFull) {
            transfer(in, out);
            inFull = false;
        }
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        T first =  out.pop();
        size--;
        return first;
    }

    private void transfer(SimpleStack<T> putOut, SimpleStack<T> putIn) {
        for (int i = 0; i < size; i++) {
            putIn.push(putOut.pop());
        }
    }

    public void push(T value) {
        if (!inFull) {
            transfer(out, in);
            inFull = true;
        }
        in.push(value);
        size++;
    }

}
