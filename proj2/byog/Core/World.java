package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Arrays;
import java.util.Random;

public class World {
    private final TETile[][] world;
    private final int WIDTH;
    private final int HEIGHT;
    private Room[] roomList;
    private boolean DEBUG = false;

    public World(int width, int height) {
        WIDTH = width;
        HEIGHT = height;

        // initialize tiles
        world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public TETile[][] getWorld() {
        return world;
    }

    public void generateRandomRooms(Random r) {
        int roomNumber = RandomUtils.uniform(r, 30, 40);

        roomList = new Room[roomNumber];

        int cnt = 0;
        while (cnt < roomNumber) {
            Room room = addRoom(r);
            roomList[cnt] = room;
            cnt++;
        }
    }

    private Room addRoom(Random r) {
        Room room;
        do {
            room = getRandomRoom(r);
        } while (room == null);

        int iStart = room.position.x;
        int iEnd = iStart + room.width;
        int jStart = room.position.y;
        int jEnd = jStart + room.height;

        for (int i = iStart; i < iEnd; i++) {
            for (int j = jStart; j < jEnd; j++) {
                if ((i == iStart) || (i == iEnd - 1) || (j == jStart) || (j == jEnd - 1)) {
                    world[i][j] = TETile.colorVariant(Tileset.WALL, 20, 20, 20, new Random());
                } else {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }

        return room;
    }

    private Room getRandomRoom(Random r) {
        Position p = getRandomPosition(r);
        int width = getRandomWidth(r);
        int height = getRandomHeight(r);
        Room room = new Room(p, width, height);

        if (roomOverlap(room) || roomOutOfBounds(room)) {
            return null;
        } else {
            return room;
        }
    }

    private Position getRandomPosition(Random r) {
        int x = RandomUtils.uniform(r, WIDTH);
        int y = RandomUtils.uniform(r, HEIGHT);
        return new Position(x, y);
    }

    private int getRandomWidth(Random r) {
        return RandomUtils.uniform(r, 3, WIDTH / 5);
    }

    private int getRandomHeight(Random r) {
        return RandomUtils.uniform(r, 3, HEIGHT / 6);
    }

    private boolean roomOverlap(Room room) {
        int x0L = room.position.x;
        int y0L = room.position.y;
        int x0R = x0L + room.width;
        int y0R = y0L + room.height;

        for (Room value : roomList) {
            if (value == null) {
                break;
            }

            int xL = value.position.x;
            int yL = value.position.y;
            int xR = xL + value.width;
            int yR = yL + value.height;

            if (!(x0L > xR || x0R < xL || y0L > yR || y0R < yL)) {
                return true;
            }
        }

        return false;
    }

    private boolean roomOutOfBounds(Room room) {
        int xL = room.position.x;
        int yL = room.position.y;
        int xR = xL + room.width;
        int yR = yL + room.height;

        return !(xL >= 0 && yL >= 0 && xR < WIDTH && yR < HEIGHT);
    }

    public void generateRandomHallways(Random r) {
        Arrays.sort(roomList);

        for (int i = 0; i < roomList.length - 1; i++) {
            connect(r, roomList[i], roomList[i + 1]);
        }

        wrapWalls();
    }

    private void connect(Random r, Room a, Room b) {
        Position pa = getRandomRoomDoor(r, a);
        Position pb = getRandomRoomDoor(r, b);
        addHallway(r, pa, pb);
    }

    private Position getRandomRoomDoor(Random r, Room room) {
        int x = room.position.x + RandomUtils.uniform(r, 1, room.width - 1);
        int y = room.position.y + RandomUtils.uniform(r, 1, room.height - 1);
        if (DEBUG) {
            world[x][y] = Tileset.FLOWER;
        }
        return new Position(x, y);
    }

    private void addHallway(Random r, Position a, Position b) {
        int xStart = a.x;
        int xEnd = b.x;
        int yStart = a.y;
        int yEnd = b.y;

        switch (RandomUtils.uniform(r, 2)) {
            case 0:
                addUpTurn(xStart, yStart, xEnd, yEnd);
                break;
            case 1:
                addDownTurn(xStart, yStart, xEnd, yEnd);
                break;
            default:
        }
    }

    private void addUpTurn(int xStart, int yStart, int xEnd, int yEnd) {
        int i;
        i = yStart;
        while (i != yEnd) {
            if (yStart < yEnd) {
                i++;
            } else {
                i--;
            }
            world[xStart][i] = Tileset.FLOOR;
        }
        i = xStart;
        while (i != xEnd) {
            if (xStart < xEnd) {
                i++;
            } else {
                i--;
            }
            world[i][yEnd] = Tileset.FLOOR;
        }
    }

    private void addDownTurn(int xStart, int yStart, int xEnd, int yEnd) {
        int i;
        i = xStart;
        while (i != xEnd) {
            if (xStart < xEnd) {
                i++;
            } else {
                i--;
            }
            world[i][yStart] = Tileset.FLOOR;
        }
        i = yStart;
        while (i != yEnd) {
            if (yStart < yEnd) {
                i++;
            } else {
                i--;
            }
            world[xEnd][i] = Tileset.FLOOR;
        }
    }

    private void wrapWalls() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j].equals(Tileset.NOTHING)) {
                    if (hasFloorNear(i, j)) {
                        world[i][j] = TETile.colorVariant(Tileset.WALL, 20, 20, 20, new Random());
                    }
                }
            }
        }
    }

    private boolean hasFloorNear(int x, int y) {
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int i = 0; i < 8; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            if (a >= 0 && a < WIDTH && b >= 0 && b < HEIGHT) {
                if (world[a][b].equals(Tileset.FLOOR)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void generateDoorAndPlayer(Random r) {
        boolean found = false;
        do {
            int x = RandomUtils.uniform(r, WIDTH);
            int y = RandomUtils.uniform(r, HEIGHT);
            if (world[x][y].equals(Tileset.WALL)) {
                if (canBeDoor(x, y)) {
                    world[x][y] = Tileset.LOCKED_DOOR;
                    found = true;
                }
            }
        } while (!found);

        found = false;
        do {
            int x = RandomUtils.uniform(r, WIDTH);
            int y = RandomUtils.uniform(r, HEIGHT);
            if (world[x][y].equals(Tileset.FLOOR)) {
                world[x][y] = Tileset.PLAYER;
                found = true;
            }
        } while (!found);
    }

    private boolean canBeDoor(int x, int y) {
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int i = 0; i < 8; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            if (a >= 0 && a < WIDTH && b >= 0 && b < HEIGHT) {
                if (world[a][b].equals(Tileset.NOTHING)) {
                    return true;
                }
            }
        }
        return false;
    }
}
