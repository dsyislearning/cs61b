public class LinkedListDeque<T> {
    private class TNode {
        private T item;
        private TNode prev;
        private TNode next;

        public TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /**
     * The first item (if it exists) is at sentinel.next.
     */
    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        /* other is an instantiation, we can only manipulate it my its public methods */
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        TNode oldFirst = sentinel.next;
        sentinel.next = new TNode(item, sentinel, oldFirst);
        oldFirst.prev = sentinel.next;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item
     */
    public void addLast(T item) {
        TNode oldLast = sentinel.prev;
        sentinel.prev = new TNode(item, oldLast, sentinel);
        oldLast.next = sentinel.prev;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * @return
     */
    public boolean isEmpty() {
        if (sentinel == sentinel.next) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in the deque.
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        TNode p = sentinel.next;
        while (p != sentinel) {
            if (p.next == sentinel) {
                System.out.print(p.item);
            } else {
                System.out.print(p.item + " ");
            }
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return
     */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return res;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * @return
     */
    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return res;
    }

    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * @param index
     * @return
     */
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        TNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /* Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        TNode p = sentinel;
        if (index == 0) {
            return sentinel.next.item;
        }
        sentinel = sentinel.next;
        T res = getRecursive(index - 1);
        sentinel = p;
        return res;
    }
}
