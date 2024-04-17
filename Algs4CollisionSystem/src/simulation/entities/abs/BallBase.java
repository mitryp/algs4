package simulation.entities.abs;

import edu.princeton.cs.algs4.StdDraw;
import simulation.SimulationConstants;
import simulation.bundles.CoordinatePair;

public abstract class BallBase {
    /**
     * Position coordinate
     */
    protected double x, y;

    /**
     * Velocity coordinate
     */
    protected double vx, vy;

    public final double radius;

    public BallBase(CoordinatePair position, CoordinatePair velocity, double radius) {
        x = position.x();
        y = position.y();

        vx = velocity.x();
        vy = velocity.y();
        this.radius = radius;
    }

    public void move(double dt) {
        move(dt, true);
    }

    protected void move(double dt, boolean checkWallCollisions) {
        if (checkWallCollisions) {
            if (x + vx * dt < radius || x + vx * dt > SimulationConstants.maxX - radius)
                vx = -vx;

            if (y + vy * dt < radius || y + vy * dt > SimulationConstants.maxY - radius)
                vy = -vy;
        }

        x += vx * dt;
        y += vy * dt;
    }

    public void draw() {
        StdDraw.filledCircle(x, y, radius);
    }

}
