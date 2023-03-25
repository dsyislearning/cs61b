package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertEquals(10, arb.capacity());
        assertEquals(0, arb.fillCount());
        arb.enqueue(1);
        assertEquals(Integer.valueOf(1), arb.peek());
        assertEquals(1, arb.fillCount());
        arb.enqueue(2);
        assertEquals(2, arb.fillCount());
        Integer i = arb.dequeue();
        assertEquals(i, Integer.valueOf(1));
        assertEquals(Integer.valueOf(2), arb.peek());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
