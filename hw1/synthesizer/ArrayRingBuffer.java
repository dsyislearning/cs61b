package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    private int first = 0;
    private int last = 0;
    private T[] rb;

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int index;
        private int cnt;

        public ArrayRingBufferIterator() {
            index = first; // !!! not from 0
            cnt = 0;
        }

        @Override
        public boolean hasNext() {
            return cnt < fillCount;
        }

        @Override
        public T next() {
            T res = rb[index];
            index = (index + 1) % rb.length; // ring buffer!!!
            cnt++;
            return res;
        }
    }

    public ArrayRingBuffer(int capacity) {
        super.fillCount = 0;
        super.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = (last + 1) % rb.length;
        super.fillCount++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T res = rb[first];
        first = (first + 1) % rb.length;
        super.fillCount--;
        return res;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
}
