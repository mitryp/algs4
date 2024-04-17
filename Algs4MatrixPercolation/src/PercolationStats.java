import requirements.StatsRequirements;
import utils.Pair;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class PercolationStats extends StatsRequirements {
    private final int matrixSide;
    private final int totalCells;
    private final double[] runs;
    private long executionTime;

    public PercolationStats(int n, int t) {
        matrixSide = n;
        totalCells = n * n;
        runs = new double[t];

        runAll();
    }

    @Override
    public double mean() {
        return Arrays.stream(runs).sum() / runs.length;
    }

    @Override
    public double stddev() {
        final double mean = mean();

        return Arrays.stream(runs).map(x -> Math.pow(x - mean, 2)).sum() / (runs.length - 1);
    }

    @Override
    public Pair<Double, Double> trustInterval() {
        final double magicConstant = 1.96;
        final double mean = mean();
        final double deviationDivT = Math.sqrt(stddev() / runs.length);
        final double eps = magicConstant * deviationDivT;

        return new Pair<>(mean - eps, mean + eps);
    }

    private double run() {
        final Percolation matrix = new Percolation(matrixSide);
        final RandomGenerator rand = RandomGenerator.getDefault();

        do {
            final int x = rand.nextInt(matrixSide), y = rand.nextInt(matrixSide);

            matrix.open(x, y);
        } while (matrix.getOpenedCount() < matrixSide || !matrix.percolates());

        return matrix.getOpenedCount() / (double) totalCells;
    }

    private void runAll() {
        long startTime = System.nanoTime();

        for (int i = 0; i < runs.length; i++) {
            runs[i] = run();
        }

        executionTime = (System.nanoTime() - startTime) / 1_000_000;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong arguments length! Expected arguments: PercolationStats {int matrixSide} {int " +
                    "runTimes}");
            return;
        }

        final int matrixSide, runs;
        try {
            matrixSide = Integer.parseInt(args[0]);
            runs = Integer.parseInt(args[1]);
        } catch (IllegalArgumentException ignored) {
            System.out.println("Wrong arguments!");
            return;
        }

        final PercolationStats stats = new PercolationStats(matrixSide, runs);

        System.out.printf("mean = %f%n", stats.mean());
        System.out.printf("stddev = %f%n", stats.stddev());
        System.out.println("95% confidence interval = " + stats.trustInterval());
        System.out.println("Completed in " + stats.executionTime + "ms");
    }
}
