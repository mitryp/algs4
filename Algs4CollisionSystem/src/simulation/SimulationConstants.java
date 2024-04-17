package simulation;

import java.util.Random;

public abstract class SimulationConstants {
    public static final Random
            random = new Random();

    public static final double
            minParticleRadius = .01,
            maxParticleRadius = .02;

    public static final double
            minParticleMass = 1,
            maxParticleMass = 5;

    public static final double
            maxX = 1,
            maxY = 1;

    public static final double
            minRandomVelocity = -.01,
            maxRandomVelocity = .01;

    public static final int
            drawDelay = 1;

    public static final double
            stepTime = .01;

}
