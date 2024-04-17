package simulation.events;

import simulation.entities.Particle;
import simulation.events.abs.EventBase;

import java.util.Comparator;

public class Event extends EventBase<Event> {
    protected int p0Count, p1Count;

    public Event(double time, Particle p0, Particle p1) {
        super(time, p0, p1);

        p0Count = collisionCountOf(p0);
        p1Count = collisionCountOf(p1);
    }

    private int collisionCountOf(Particle particle) {
        if (particle == null) return -1;
        return particle.count();
    }

    @Override
    public boolean isValid() {
        return collisionCountOf(p0) == p0Count && collisionCountOf(p1) == p1Count;
    }

    @Override
    public int compareTo(Event o) {
        return Comparator.<Event>comparingDouble(p -> p.time).compare(this, o);
    }
}
