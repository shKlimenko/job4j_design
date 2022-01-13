package datastructures.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        Predicate<Node<E>> condition = el -> (el.children.size() > 2);
        return findByPredicate(condition).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(child).isEmpty() && findBy(parent).isPresent()) {
            findBy(parent).get().children.add(new Node(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> condition = el -> el.value.equals(value);
        return findByPredicate(condition);
    }
}