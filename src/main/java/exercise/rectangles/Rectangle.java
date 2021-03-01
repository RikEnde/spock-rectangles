package exercise.rectangles;

import java.util.Objects;

public class Rectangle {
    final Point topLeft;
    final Point bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * False if one rectangle is entirely to the left of the other
     * or if one rectangle is entirely above the other
     * or if one rectangle is entirely inside the other without touching the edge
     * Edge case, a contains b, but they touch edges
     */
    boolean intersects(Rectangle b) {
        var a = this;
        var intersects = a.topLeft.x < b.bottomRight.x &&
                a.bottomRight.x > b.topLeft.x &&
                a.topLeft.y > b.bottomRight.y &&
                a.bottomRight.y < b.topLeft.y;

        var contains = a.containsExclusiveEdges(b) || b.containsExclusiveEdges(a);

        return intersects && !contains;
    }

    boolean shareCoords(Rectangle b) {
        var a = this;
        return a.bottomRight.x == b.topLeft.x ||
                a.topLeft.x == b.bottomRight.x ||
                a.topLeft.y == b.bottomRight.y ||
                a.bottomRight.y == b.topLeft.y;
    }

    /**
     * Contains and does not touch edges
     */
    boolean containsExclusiveEdges(Rectangle b) {
        var a = this;
        var outside = b.topLeft.x <= a.topLeft.x ||
                b.bottomRight.x >= a.bottomRight.x ||
                b.topLeft.y >= a.topLeft.y ||
                b.bottomRight.y <= a.bottomRight.y;

        return !outside; // &&!adjacent
    }

    /**
     * Contains and may touch edges
     * False if b topleft is outside or if b.bottomright is outside
     */
    boolean contains(Rectangle b) {
        var a = this;
        var outside = b.topLeft.x < a.topLeft.x ||
                b.bottomRight.x > a.bottomRight.x ||
                b.topLeft.y > a.topLeft.y ||
                b.bottomRight.y < a.bottomRight.y;

        return !outside; // &&!adjacent
    }

    /**
     * True if: a right edge = b left edge
     * a left edge = b right edge or
     * a top edge = b bottom edge or
     * a bottom edge = b bottom edge
     *
     * and not:
     * (b top < a bottom or b bottom > a top) or (b left > a right or b right < a left)
     */
    boolean adjacent(Rectangle b) {
        var a = this;

        var outside = b.topLeft.y < a.bottomRight.y ||
                b.bottomRight.y > a.topLeft.y ||
                b.topLeft.x > a.bottomRight.x ||
                b.bottomRight.x < a.topLeft.x;

        return !outside && a.shareCoords(b);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "topLeft=" + topLeft +
                ", bottomRight=" + bottomRight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(topLeft, rectangle.topLeft) && Objects.equals(bottomRight, rectangle.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight);
    }
}
