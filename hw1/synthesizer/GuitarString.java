package synthesizer;

public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private BoundedQueue<Double> buffer; /* Buffer for storing sound data. */

    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        while (!buffer.isFull()) {
            buffer.enqueue(.0);
        }
    }

    public void pluck() {
        int cnt = buffer.fillCount();
        while (cnt > 0) {
            buffer.dequeue();
            buffer.enqueue(Math.random() - 0.5);
            cnt--;
        }
    }

    public void tic() {
        double frontSample = buffer.dequeue();
        double newSample = (frontSample + buffer.peek()) / 2 * DECAY;
        buffer.enqueue(newSample);
    }

    public double sample() {
        return buffer.peek();
    }
}
