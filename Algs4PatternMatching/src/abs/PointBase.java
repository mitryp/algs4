package abs;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public abstract class PointBase<P extends PointBase<P>> implements Comparable<P> {
    public final int x, y;

    public PointBase(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(P o) {
        StdDraw.line(x, y, o.x, o.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    /**
     * Нахил між цією і that точкою.
     * (y1-y0)/(x1-x0)
     */
    public abstract double slopeTo(P o);

    /**
     * Порівняти точки за градієнтом до цієї точки.
     */
    public abstract Comparator<P> slopeOrder();
}