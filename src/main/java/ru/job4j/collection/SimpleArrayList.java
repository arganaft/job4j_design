package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void expand() {
        container = Arrays.copyOf(container, (size + 1) * 2);
    }


    @Override
    public void add(T value) {
        if (size == container.length) {
            expand();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        if (index < size - 1) {
            System.arraycopy(container,
                    index + 1,
                    container,
                    index,
                    size - index - 1);
        }
        size--;
        container[size] = null;
        modCount++;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final private int expectedModCount = modCount;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException(
                            "Итератор не может продолжить работу, т.к. во время его работы в текущий список были внесены изменения");
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}
