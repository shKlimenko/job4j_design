package collection;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            Object[] newArr = new Object[container.length * 2];
            System.arraycopy(container, 0, newArr, 0, size);
            container = (T[]) newArr;
        }
        this.container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        if (index < 0 || index > container.length) {
            throw new IndexOutOfBoundsException();
        }
        container[index] = newValue;
        return container[index];
    }

    @Override
    public T remove(int index) {
        T removed = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return removed;
    }

    @Override
    public T get(int index) {
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int point;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[point++];
            }

        };
    }
}