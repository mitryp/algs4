import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.io.IOException;

public class Run {
    static final String input6 = "input/input6.txt";
    static final String input8 = "input/input8.txt";
    static final String input40 = "input/input40.txt";
    static final String input50 = "input/input50.txt";
    static final String input56 = "input/input56.txt";
    static final String input100 = "input/input100.txt";
    static final String input400 = "input/input400.txt";
    static final String rs1423 = "input/rs1423.txt";
    static final String grid6x6 = "input/grid6x6.txt";
    static final String horizontal100 = "input/horizontal100.txt";

    public static void main(String[] args) {
        final Point[] points = processInput(rs1423);
        prepareStdDraw();
        drawPoints(points);
        StdDraw.show();

//        final Algorithm alg = Algorithm.brute();
        final Algorithm alg = Algorithm.fast();

        alg.lines(points).forEach(lineSegment -> {
            lineSegment.draw();
            System.out.println(lineSegment);
        });
        StdDraw.show();
    }

    private static Point[] processInput(String filename) {
        final int[] ints = new In(filename).readAllInts();
        Point[] points = new Point[ints[0]];

        for (int i = 0; i < points.length; i++) points[i] = new Point(ints[i * 2 + 1], ints[i * 2 + 2]);

        return points;
    }

    private static void drawPoints(Point[] points) {
        for (final Point p : points) p.draw();
    }

    private static void prepareStdDraw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
    }
}
