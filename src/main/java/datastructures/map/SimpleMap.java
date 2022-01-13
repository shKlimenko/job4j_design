package datastructures.map;

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
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<K, V>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return  hashCode;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] bigTable = new MapEntry[capacity];
        for (MapEntry<K, V> element : table) {
            if (element != null) {
                int newIndex = indexFor(hash(element.key.hashCode()));
                bigTable[newIndex] = element;
            }
        }
        table = bigTable;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        MapEntry<K, V> cell = table[indexFor(hash(key.hashCode()))];
        if (cell != null && Objects.equals(cell.key, key)) {
            rsl = cell.value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = table[indexFor(hash(key.hashCode()))] != null;
        if (rsl && table[indexFor(hash(key.hashCode()))].key.equals(key)) {
            table[indexFor(hash(key.hashCode()))] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int current = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[current] == null) {
                    current++;
                }
                return table[current++].key;
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