package datastructures.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in.getSize() == 0 && out.getSize() == 0) {
            throw new NoSuchElementException();
        }
        if (out.getSize() == 0) {
            int i = in.getSize();
            while (i > 0) {
                out.push(in.pop());
                i--;
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}