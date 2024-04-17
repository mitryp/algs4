package simulation.events.abs;

import simulation.entities.Particle;


public abstract class EventBase<T extends EventBase<T>> implements Comparable<T> {
    public final double time;
    public final Particle p0, p1;

    public EventBase(double time, Particle p0, Particle p1) {
        this.time = time;
        this.p0 = p0;
        this.p1 = p1;
    }

    public abstract boolean isValid();
}
