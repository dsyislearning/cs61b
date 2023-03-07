import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 10000; i++) {
            double ran = StdRandom.uniform();
            Integer in = Integer.valueOf(i);
            Integer expect, actual;
            if (ran < 0.25) {
                student.addFirst(in);
                solution.addFirst(in);
                message += "addFirst(" + in + ")\n";
                actual = Integer.valueOf(student.size());
                expect = Integer.valueOf(solution.size());
                message += "size()\n";
                assertEquals(message, expect, actual);
            } else if (ran < 0.5) {
                student.addLast(in);
                solution.addLast(in);
                message += "addLast(" + in + ")\n";
                actual = Integer.valueOf(student.size());
                expect = Integer.valueOf(solution.size());
                message += "size()\n";
                assertEquals(message, expect, actual);
            } else if (ran < 0.75) {
                actual = student.removeFirst();
                expect = solution.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, expect, actual);
            } else {
                actual = student.removeLast();
                expect = solution.removeLast();
                message += "removeLast()\n";
                assertEquals(message, expect, actual);
            }
        }
    }
}
