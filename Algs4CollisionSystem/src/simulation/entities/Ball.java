package simulation.entities;

import simulation.entities.abs.BallBase;
import simulation.bundles.CoordinatePair;

import static simulation.SimulationConstants.*;

public class Ball extends BallBase {
    public Ball(CoordinatePair ballPosition, CoordinatePair ballVelocity, double radius) {
        super(ballPosition, ballVelocity, radius);
    }

    public static Ball random() {
        final double r = random.nextDouble(minParticleRadius, maxParticleRadius);

        return new Ball(CoordinatePair.random(r, maxX - r), CoordinatePair.random(minRandomVelocity,
                maxRandomVelocity), r);
    }
}
