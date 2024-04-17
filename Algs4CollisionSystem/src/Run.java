import edu.princeton.cs.algs4.StdDraw;
import simulation.CollisionSystem;
import simulation.SimulationConstants;
import simulation.entities.Particle;

public class Run {
    public static void main(String[] args) {
        prepareStdDraw();

        final Particle[] particles = randomParticles(Integer.parseInt(args[0]));
        final CollisionSystem collisionSystem = new CollisionSystem(particles);

        while (collisionSystem.step(SimulationConstants.stepTime)) {
            display(particles);
        }
    }

    private static Particle[] randomParticles(int count) {
        final Particle[] particles = new Particle[count];

        for (int i = 0; i < particles.length; i++)
            particles[i] = Particle.random();

        return particles;
    }

    private static void prepareStdDraw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(600, 600);
    }

    private static void display(Particle[] balls) {
        StdDraw.clear();

        for (Particle b : balls)
            b.draw();

        StdDraw.show();
        StdDraw.pause(SimulationConstants.drawDelay);
    }
}
