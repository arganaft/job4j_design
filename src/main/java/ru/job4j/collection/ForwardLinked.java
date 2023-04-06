package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private ForwardLinked.Node<T> head;
    private ForwardLinked.Node<T> last;

    public void add(T value) {
        if (head == null) {
            head = new ForwardLinked.Node<>(value, null);
            last = head;
        } else {
            ForwardLinked.Node<T> past = last;
            last = new ForwardLinked.Node<>(value, null);
            past.next = last;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = new ForwardLinked.Node<>(value, null);
            last = head;
        } else {
            head = new Node<>(value, head);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Iterator<T> iterator = iterator();
        T element = null;
        for (int i = 0; i <= index; i++) {
            element = iterator.next();
        }
        return element;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("В коллекции нет элементов");
        }
        Node<T> first = head;
        head = head.next;
        return first.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final private int expectedModCount = modCount;
            private ForwardLinked.Node<T> nextElement = head;
            public ForwardLinked.Node<T> actualElement;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException(
                            "Итератор не может продолжить работу, т.к. во время его работы в текущий список были внесены изменения");
                }
                return nextElement != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                actualElement = nextElement;
                nextElement = nextElement.next;
                return actualElement.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private ForwardLinked.Node<T> next;

        Node(T element, ForwardLinked.Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
