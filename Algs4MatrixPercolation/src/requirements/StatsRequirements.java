package requirements;

import utils.Pair;

public abstract class StatsRequirements {
    /**
     * Returns the average percolation threshold of the runs.
     */
    public abstract double mean();

    /**
     * Returns the standard deviation of the experiments.
     */
    public abstract double stddev();

    public abstract Pair<Double, Double> trustInterval();
}
