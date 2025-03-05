package telran.list.model;

import telran.list.intefaces.IList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements IList<E>, Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    // O(1)
    @Override
    public int size() {
        return size;
    }

    // O(n), O(1) for last, first
    @Override
    public boolean add(int index, E element) {
        if (index == size) {
            Node<E> newNode = new Node<>(last, element, null);
            if (last != null) {
                last.next = newNode;
            } else {
                first = newNode;
            }
            last = newNode;
        } else {
            Node<E> node = getNodeByIndex(index);
            Node<E> newNode = new Node<>(node.prev, element, node);
            node.prev = newNode;
            if (index != 0) {
                newNode.prev.next = newNode;
            } else {
                first = newNode;
            }
        }
        size++;
        return true;
    }

    private Node<E> getNodeByIndex(int index) {
        checkIndex(index);
        Node<E> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    // O(n)
    @Override
    public E get(int index) {
        return getNodeByIndex(index).payload;
    }


    //O(n)
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o != null) {
            for (Node<E> node = first; node != null; node = node.next, index++) {
                if (o.equals(node.payload)) {
                    return index;
                }
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next, index++) {
                if (null == node.payload) {
                    return index;
                }
            }
        }
        return -1;
    }

    //O(n)
    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        if (o != null) {
            for (Node<E> node = last; node != null; node = node.prev, index--) {
                if (o.equals(node.payload)) {
                    return index;
                }
            }
        } else {
            for (Node<E> node = last; node != null; node = node.prev, index--) {
                if (null == node.payload) {
                    return index;
                }
            }
        }
        return -1;
    }

    //O(n)
    @Override
    public E remove(int index) {
        Node<E> removeNode = getNodeByIndex(index);
        if (removeNode.prev != null) {
            removeNode.prev.next = removeNode.next;
        } else {
            first = removeNode.next;
        }
        if (removeNode.next != null) {
            removeNode.next.prev = removeNode.prev;
        } else {
            last = removeNode.prev;
        }
        size--;
        return removeNode.payload;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = first;
            private Node<E> lastGet = null;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastGet = current;
                current = current.next;
                return lastGet.payload;
            }
        };
    }

    private static class Node<E> {
        E payload;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E payload, Node<E> next) {
            this.payload = payload;
            this.next = next;
            this.prev = prev;
        }
    }
}
