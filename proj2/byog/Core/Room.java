package byog.Core;

public class Room implements Comparable<Room> {
    public Position position;
    public int width;
    public int height;

    public Room(Position position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    @Override
    public int compareTo(Room o) {
        if (this.position.x < o.position.x) {
            return -1;
        } else if (this.position.x > o.position.x) {
            return 1;
        }
        return 0;
    }
}
