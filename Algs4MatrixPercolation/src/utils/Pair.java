package utils;

public record Pair<T0, T1>(T0 t0, T1 t1) {
    @Override
    public String toString() {
        return "[" + t0 + ", " + t1 + ']';
    }
}
