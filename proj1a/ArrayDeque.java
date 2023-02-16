public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length - 1;
        nextLast = 0;
    }

//    /**
//     * Creates a deep copy of other.
//     * @param other
//     */
//    public ArrayDeque(ArrayDeque other) {
//        items = (T[]) new Object[8];
//        size = 0;
//        nextFirst = items.length - 1;
//        nextLast = 0;
//
//        /* other is an instantiation, we can only manipulate it my its public methods */
//        for (int i = 0; i < other.size(); i++) {
//            addLast((T) other.get(i));
//        }
//    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int firstPos = (nextFirst + 1) % items.length;
        int lastPos = (items.length + nextLast - 1) % items.length;
        if (firstPos <= lastPos) {
            System.arraycopy(items, firstPos, a, 0, lastPos - firstPos + 1);
        } else {
            System.arraycopy(items, firstPos, a, 0, items.length - firstPos);
            System.arraycopy(items, 0, a, items.length - firstPos, lastPos + 1);
        }
        nextFirst = capacity - 1;
        nextLast = (items.length + lastPos - firstPos) % items.length + 1;
        items = a;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = (items.length + nextFirst - 1) % items.length;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * @return
     */
    public boolean isEmpty() {
        if (size == 0) {
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
        int firstPos = (nextFirst + 1) % items.length;
        int lastPos = (items.length + nextLast - 1) % items.length;
        if (firstPos <= lastPos) {
            for (int i = firstPos; i <= lastPos; i++) {
                System.out.print(items[i]);
                System.out.print(" ");
            }
        } else {
            for (int i = firstPos; i <= lastPos + items.length; i++) {
                System.out.print(items[i % items.length]);
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int firstPos = (nextFirst + 1) % items.length;
        T res = items[firstPos];
        items[firstPos] = null;
        nextFirst = firstPos;
        size--;
        if (items.length >= 16 && (double) size / items.length <= 0.25) {
            resize(items.length / 2);
        }
        return res;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * @return
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int lastPos = (items.length + nextLast - 1) % items.length;
        T res = items[lastPos];
        items[lastPos] = null;
        nextLast = lastPos;
        size--;
        if (items.length >= 16 && (double) size / items.length <= 0.25) {
            resize(items.length / 2);
        }
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
        int firstPos = (nextFirst + 1) % items.length;
        return items[(firstPos + index) % items.length];
    }
}
