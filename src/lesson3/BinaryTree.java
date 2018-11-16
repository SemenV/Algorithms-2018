package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    protected static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    public Node<T> getRoot() {
        return root;
    }

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    protected Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {}

        /**
         * Поиск следующего элемента
         * Средняя
         */
        private Node<T> findNext() {
            // TODO
            throw new NotImplementedError();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    protected void addSize() {
        size++;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return new BinarySubTree(this, fromElement, toElement,true);
    }


    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        return new BinarySubTree(this, null, toElement,false);
    }


    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return new BinarySubTree(this, fromElement, null,true);
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}

class BinarySubTree<V extends Comparable<V>> extends BinaryTree<V> {
    private Node<V> root;
    V fromElement, toElement;
    boolean containsEdge;
    int size;

    public BinarySubTree(BinaryTree<V> tree, V fromElement, V toElement, boolean containsEdge) {
        Node<V> tempRoot = tree.getRoot();


        if (fromElement == null && toElement.compareTo(tempRoot.value) < 0)
            tempRoot = findGenNode(tempRoot, toElement, toElement);
        else if (toElement == null && fromElement.compareTo(tempRoot.value) > 0) {
            tempRoot = findGenNode(tempRoot, fromElement, fromElement);
        }

        if (toElement != null && fromElement != null)
            tempRoot = findGenNode(tempRoot,fromElement,toElement);

        this.root = tempRoot;
        this.fromElement = fromElement;
        this.toElement = toElement;
        this.containsEdge = containsEdge;
        this.size = countSize(this.root);
    }


    //find nearest general node between toElement & fromElement
    private Node<V> findGenNode(Node<V> start, V fromElement, V toElement) {
        if (fromElement.compareTo(start.value) < 0 && toElement.compareTo(start.value) < 0) {
            if (start.left == null) return start;
            return findGenNode(start.left, fromElement, toElement);
        } else if (fromElement.compareTo(start.value) > 0 && toElement.compareTo(start.value) > 0) {
            if (start.right == null) return start;
            return findGenNode(start.right, fromElement, toElement);
        }
        // when fromElement or toElement = start.value
        return start;
    }


    private boolean isInside(V v) {
        return (fromElement == null || v.compareTo(fromElement) > 0 || containsEdge && v.compareTo(fromElement) == 0) &&
                (toElement == null || v.compareTo(toElement) < 0 || containsEdge && v.compareTo(toElement) == 0);
    }

    //переопределил для ускоренного поиска от нового root те find(this.root,t)
    @Override
    public boolean add(V t) {
        if (!isInside(t)) return false;
        Node<V> closest = find(this.root,t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<V> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        this.size++;
        addSize();
        return true;
    }

    //по той же пречине переопределил
    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        V t = (V) o;
        if (this.root == null) throw new NoSuchElementException();
        Node<V> closest = find(this.root,t);
        return closest != null && t.compareTo(closest.value) == 0 && isInside((V) o);
    }

    private int countSize(Node<V> start) {
        int currSize = 0;
        if (start != null) {
            if (isInside(start.value))
                currSize++;
            currSize += countSize(start.left);
            currSize += countSize(start.right);
        }
        return currSize;
    }

    @Override
    public int size() {
        return size;
    }
}
