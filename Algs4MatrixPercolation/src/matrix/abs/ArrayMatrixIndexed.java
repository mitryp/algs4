package matrix.abs;

public interface ArrayMatrixIndexed {
    default int getIndex(int x, int y, int sideLength) throws IllegalArgumentException {
        if (x < 0 || x >= sideLength || y < 0 || y >= sideLength)
            throw new IllegalArgumentException("Value %d is bigger than matrix size %d".formatted(Math.max(x, y), sideLength));

        return sideLength * y + x;
    }
}
