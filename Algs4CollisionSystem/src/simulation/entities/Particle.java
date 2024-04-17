package simulation.entities;

import edu.princeton.cs.algs4.StdDraw;
import simulation.bundles.CoordinatePair;

import java.awt.Color;

import static simulation.SimulationConstants.*;
import static simulation.SimulationConstants.maxY;

public class Particle extends Ball {
    private static final Color[] ballColors = new Color[]{
            Color.decode("#4C5454"),
            Color.decode("#FF715B"),
            Color.decode("#1EA896"),
            Color.decode("#1EA896"),
            Color.decode("#523F38"),
    };

    private final Color color;
    public final double mass; // mass
    private int count; // number of collisions

    public Particle(CoordinatePair ballPosition, CoordinatePair ballVelocity, double radius, double mass) {
        super(ballPosition, ballVelocity, radius);
        this.mass = mass;
        this.color = ballColors[(int) (Math.random() * ballColors.length)];
    }

    public static Particle random() {
        return new Particle(CoordinatePair.random(maxParticleRadius, maxX - maxParticleRadius),
                CoordinatePair.random(.01, .05), random.nextDouble(minParticleRadius, maxParticleRadius),
                random.nextDouble(minParticleMass,
                        maxParticleMass));
    }

    @Override
    public void move(double dt) {
        move(dt, false);
    }

    public double timeToHit(Particle that) {
        if (this == that) return Double.POSITIVE_INFINITY;
        final double
                dx = that.x - this.x,
                dy = that.y - this.y;

        final double
                dvx = that.vx - this.vx,
                dvy = that.vy - this.vy;

        final double dvdr = dx * dvx + dy * dvy;

        if (dvdr > 0) return Double.POSITIVE_INFINITY;
        final double
                dvdv = dvx * dvx + dvy * dvy,
                drdr = dx * dx + dy * dy,

                sigma = this.radius + that.radius,
                d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);

        if (d < 0) return Double.POSITIVE_INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public void bounceOff(Particle that) {
        final double
                dx = that.x - this.x,
                dy = that.y - this.y,

                dvx = that.vx - this.vx,
                dvy = that.vy - this.vy,

                dvdr = dx * dvx + dy * dvy,

                dist = this.radius + that.radius,
                j = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist),
                jx = j * dx / dist,
                jy = j * dy / dist;

        vx += jx / this.mass;
        vy += jy / this.mass;

        that.vx -= jx / that.mass;
        that.vy -= jy / that.mass;

        this.count++;
        that.count++;
    }

    public double timeToHitVerticalWall() {
        return wallCollisionPrediction(x, vx, maxX);
    }

    public double timeToHitHorizontalWall() {
        return wallCollisionPrediction(y, vy, maxY);
    }

    private double wallCollisionPrediction(double coordinate, double velocity, double maxCoordinate) {
        if (velocity < 0)
            return (coordinate - radius) / -velocity;

        if (velocity > 0)
            return (maxCoordinate - coordinate - radius) / velocity;

        return Double.POSITIVE_INFINITY;
    }

    public void bounceOffVerticalWall() {
        vx = -vx;
        count++;
    }

    public void bounceOffHorizontalWall() {
        vy = -vy;
        count++;
    }

    public int count() {
        return count;
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(color);
        super.draw();
    }
}
