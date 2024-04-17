package simulation.bundles;

import simulation.SimulationConstants;

import java.util.Random;

public record CoordinatePair(double x, double y) {
    public static CoordinatePair random(double min, double max) {
        final Random r = SimulationConstants.random;

        return new CoordinatePair(r.nextDouble(min, max), r.nextDouble(min, max));
    }
}
