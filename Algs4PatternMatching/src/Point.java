import abs.PointBase;

import java.util.Comparator;
import java.util.Objects;

public class Point extends PointBase<Point> {
    public Point(int x, int y) {
        super(x, y);
    }

    public int compareTo(Point o) {
        if (y == o.y) return x - o.x;
        return y - o.y;
    }

    /**
     * Нахил між цією і that точкою.
     * (y1-y0)/(x1-x0)
     */
    @Override
    public double slopeTo(Point o) {
        if (y == o.y && x == o.y) return Double.NEGATIVE_INFINITY;
        if (x == o.x) return Double.POSITIVE_INFINITY;
        if (y == o.y) return 0;

        return ((double) o.y - y) / (o.x - x);
    }

    /**
     * Порівняти точки за градієнтом до цієї точки.
     */
    public Comparator<Point> slopeOrder() {
        return (o1, o2) -> Double.compare(slopeTo(o2), slopeTo(o1));
    }

    public LineSegment segmentTo(Point o) {
        return new LineSegment(this, o);
    }

    public class LineSegment {
        public final Point p0, p1;

        public LineSegment(Point p0, Point p1) {
            this.p0 = p0;
            this.p1 = p1;
        }

        public void draw() {
            p0.drawTo(p1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LineSegment that = (LineSegment) o;
            return p0.equals(that.p0) && p1.equals(that.p1);
        }

        @Override
        public String toString() {
            return p0.toString() + " -> " + p1.toString();
        }

        @Override
        public int hashCode() {
            return Objects.hash(p0, p1);
        }
    }
}
