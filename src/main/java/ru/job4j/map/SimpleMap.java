package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean validPut = table[indexFor(hash(Objects.hashCode(key)))] == null;
        if (validPut) {
            table[indexFor(hash(Objects.hashCode(key)))] = new MapEntry<>(key, value);
            modCount++;
            count++;
        }
        return validPut;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity << 1];
        for (MapEntry el : oldTable) {
            if (el != null) {
                table[indexFor(hash(Objects.hashCode(el.key)))] = el;
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            return null;
        }
        K k = table[index].key;
        return (k == key || (Objects.hashCode(key) == Objects.hashCode(k) && Objects.equals(k, key))) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean validRemove = get(key) != null;
        if (validRemove) {
            table[indexFor(hash(Objects.hashCode(key)))] = null;
            modCount++;
            count--;
        }
        return validRemove;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final private int expectedModCount = modCount;
            MapEntry nextElement;
            boolean callNext = true;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException(
                            "Итератор не может продолжить работу, т.к. во время его работы в текущую карту были внесены изменения");
                }
                while (index < table.length && callNext) {
                    if (table[index] != null) {
                        nextElement = table[index];
                        callNext = false;
                        break;
                    }
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                index++;
                callNext = true;
                return (K) nextElement.key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}