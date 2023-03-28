package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

//        world[10][20] = Tileset.WALL;

//        addHexagon(world, new Position(0, 0), 4, Tileset.WALL);

        tesselationOfHexagons(world, new Position(3, 10), 3);

        ter.renderFrame(world);
    }

    private static void tesselationOfHexagons(TETile[][] tiles, Position p, int s) {
        for (int i = 0; i < 5; i++) {
            drawColumn(tiles, p, i, s);
        }
    }

    public static void drawColumn(TETile[][] tiles, Position p, int col, int s) {
        TETile[] ts = {
                Tileset.FLOWER, Tileset.GRASS, Tileset.SAND,
                Tileset.MOUNTAIN, Tileset.TREE,
        };
        for (int i = 0; i < blockNum(col); i++) {
            Position q = blockPosition(p, col, s, i);
            TETile t = ts[new Random().nextInt(ts.length)];
            addHexagon(tiles, q, s, t);
        }
    }

    private static int blockNum(int col) {
        if (col < 3) {
            return 3 + col;
        } else {
            return 7 - col;
        }
    }

    private static Position blockPosition(Position p, int col, int s, int i) {
        int x = p.getX() + col * (s * 2 - 1);
        int y = p.getY() + ColumnStartY(col, s) + i * 2 * s;
        return new Position(x, y);
    }

    private static int ColumnStartY(int col, int s) {
        if (col < 3) {
            return (2 - col) * s;
        } else {
            return (col - 2) * s;
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int i = 0; i < 2 * s; i++) {
            drawLine(world, p, i, s, t);
        }
    }

    public static void drawLine(TETile[][] Tiles, Position p, int row, int s, TETile t) {
        int startX = lineStart(p, row, s);
        int endX = lineEnd(startX, row, s);
        int lineY = p.getY() + row;
        for (int i = startX; i < endX; i++) {
            t = TETile.colorVariant(t, 20, 20, 20, new Random());
            Tiles[i][lineY] = t;
        }
    }

    public static int lineStart(Position p, int row, int s) {
        if (row < s) {
            return p.getX() + s - row - 1;
        } else {
            return p.getX() + row - s;
        }
    }

    public static int lineEnd(int start, int row, int s) {
        if (row < s) {
            return start + s + 2 * row;
        } else {
            return start + s + 2 * (s - 1) - 2 * (row - s);
        }
    }
}

class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
