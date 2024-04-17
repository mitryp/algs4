import java.util.*;

public abstract class Algorithm {
    public abstract Set<Point.LineSegment> lines(Point[] points);

    protected Point.LineSegment getLine(Point... points) {
        Arrays.sort(points);

        return points[0].segmentTo(points[points.length - 1]);
    }

    protected boolean slopesEqual(Point... points) {
        double previousSlope = points[0].slopeTo(points[1]);

        for (int i = 1; i < points.length - 1; i++) {
            double slope = points[i].slopeTo(points[i + 1]);

            if (previousSlope != slope) return false;

            previousSlope = slope;
        }

        return true;
    }

    public static Algorithm brute() {
        return new Brute();
    }

    public static Algorithm fast() {
        return new Fast();
    }
}

class Brute extends Algorithm {
    @Override
    public Set<Point.LineSegment> lines(Point[] points) {
        final HashSet<Point.LineSegment> lineSegments = new HashSet<>();

        for (int p = 0; p < points.length; p++) {
            for (int q = 0; q < points.length; q++) {
                if (q == p) continue;
                for (int r = 0; r < points.length; r++) {
                    if (r == p || r == q) continue;
                    for (int s = 0; s < points.length; s++) {
                        if (s == p || s == r || s == q) continue;
                        final Point pp = points[p], pq = points[q], pr = points[r], ps = points[s];
                        if (slopesEqual(pp, pq, pr, ps)) {
                            lineSegments.add(getLine(pp, pq, pr, ps));
                        }
                    }
                }
            }
        }

        return lineSegments;
    }
}

class Fast extends Algorithm {
    @Override
    public Set<Point.LineSegment> lines(Point[] points) {
        final HashSet<Point.LineSegment> lineSegments = new HashSet<>();
        Point[] sortedPoints = new Point[points.length];
        System.arraycopy(points, 0, sortedPoints, 0, sortedPoints.length);

        for (final Point p : points) {
            Arrays.sort(sortedPoints, p.slopeOrder());

            for (int i = 1; i < sortedPoints.length - 2; i++) {
                if (sortedPoints[i].compareTo(p) == 0) continue;
                final Point[] temp = {p, sortedPoints[i], sortedPoints[i + 1], sortedPoints[i + 2]};

                if (slopesEqual(temp)) {
                    lineSegments.add(getLine(temp));
                }
            }
        }

        return lineSegments;
    }
}
