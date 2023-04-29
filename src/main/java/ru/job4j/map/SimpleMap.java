package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int indexOf(Object key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean validPut = table[indexOf(key)] == null;
        if (validPut) {
            table[indexOf(key)] = new MapEntry<>(key, value);
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
                table[indexOf(el.key)] = el;
            }
        }
    }

    private boolean valideKey(K key) {
        K k = table[indexOf(key)].key;
        return Objects.hashCode(key) == Objects.hashCode(k) && Objects.equals(k, key);
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        return table[index] != null && valideKey(key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean validRemove = get(key) != null;
        if (validRemove) {
            table[indexOf(key)] = null;
            modCount++;
            count--;
        }
        return validRemove;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final private int expectedModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException(
                            "Итератор не может продолжить работу, т.к. во время его работы в текущую карту были внесены изменения");
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
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