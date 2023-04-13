package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int outSize = 0;
    int inSize = 0;
    boolean inFull = true;


    public T poll() {
        if (outSize == 0) {
            while (inSize > 0) {
                out.push(in.pop());
                inSize--;
                outSize++;
            }
        }
        if (outSize + inSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        T first =  out.pop();
        outSize--;
        return first;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }

}
