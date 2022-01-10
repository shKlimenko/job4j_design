package map;

import java.util.Iterator;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry(key, value);
            count++;
            modCount++;
        }
        if (rsl && count >= capacity * LOAD_FACTOR) {
            expand();
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
        MapEntry<K, V> element = table[indexFor(hash(key.hashCode()))];
        return element == null ? null : element.value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = table[hash(key.hashCode())] != null;
        if (rsl) {
            table[hash(key.hashCode())] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    //TODO дописать итератор и тесты
    @Override
    public Iterator<K> iterator() {
        return null;
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