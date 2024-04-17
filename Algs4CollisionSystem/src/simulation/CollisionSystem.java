package simulation;

import simulation.entities.Particle;
import simulation.events.Event;

import java.util.PriorityQueue;

public class CollisionSystem {
    private final PriorityQueue<Event> pq = new PriorityQueue<>();
    private final Particle[] particles;
    private double t;

    public CollisionSystem(Particle[] particles) {
        this.particles = particles;

        for (Particle p : particles)
            predict(p);
    }

    private void predict(Particle a) {
        if (a == null) return;

        for (Particle particle : particles) {
            final double dt = a.timeToHit(particle);
            if (dt != Double.POSITIVE_INFINITY)
                pq.add(new Event(t + dt, a, particle));
        }

        final double timeToVertical = a.timeToHitVerticalWall(), timeToHorizontal = a.timeToHitHorizontalWall();
        if (timeToHorizontal != Double.POSITIVE_INFINITY)
            pq.add(new Event(t + a.timeToHitVerticalWall(), a, null));
        if (timeToVertical != Double.POSITIVE_INFINITY)
            pq.add(new Event(t + a.timeToHitHorizontalWall(), null, a));
    }

    public boolean step(double dt) {
        if (pq.isEmpty()) return false;

        while (!pq.isEmpty() && pq.peek().time <= t) {
            final Event nextEvent = pq.remove();

            if (nextEvent.isValid()) {
                Particle
                        p0 = nextEvent.p0,
                        p1 = nextEvent.p1;

                t = nextEvent.time;

                if (p0 != null && p1 != null)
                    p0.bounceOff(p1);
                else if (p0 != null)
                    p0.bounceOffVerticalWall();
                else if (p1 != null)
                    p1.bounceOffHorizontalWall();

                predict(p0);
                predict(p1);
            }
        }

        for (Particle particle : particles)
            particle.move(dt);

        t += dt;

        return true;
    }

//    // the previous variant that runs the inner cycle.
//    // also works! :)
//    public void simulate() {
//        for (Particle p : particles)
//            predict(p);
//
//        pq.add(new Event(0, null, null));
//
//        while (!pq.isEmpty()) {
//            Event event = pq.remove();
//
//            if (!event.isValid()) continue;
//
//            Particle a = event.p0;
//            Particle b = event.p1;
//            for (Particle p : particles)
//                p.move(event.time - t);
//
//            t = event.time;
//
//            if (a != null && b != null) a.bounceOff(b);
//            else if (a != null) a.bounceOffVerticalWall();
//            else if (b != null) b.bounceOffHorizontalWall();
//            else redraw();
//
//            predict(a);
//            predict(b);
//        }
//    }
//
//    private void redraw() {
//        StdDraw.clear();
//
//        for (Particle p : particles)
//            p.draw();
//
//        StdDraw.show();
//        StdDraw.pause(SimulationConstants.drawDelay);
//
//        pq.add(new Event(t + SimulationConstants.stepTime, null, null));
//    }

}
