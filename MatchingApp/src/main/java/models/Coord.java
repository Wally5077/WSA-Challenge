package models;

import static java.awt.geom.Point2D.distance;

public record Coord(int x, int y) implements Comparable<Coord> {

    @Override
    public int compareTo(Coord coord) {
        return (int) distance(x, y, coord.x, coord.y);
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
