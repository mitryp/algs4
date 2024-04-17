package matrix.abs;

public abstract class PercolationMatrixBase implements ArrayMatrixIndexed {
    /**
     * Returns the respective index in one-dimensional array based on the
     * given x and y coordinates in two-dimensional array.
     *
     * @throws IllegalArgumentException if the coordinates are incorrect
     */
    public abstract int index(int x, int y) throws IllegalArgumentException;

    /**
     * Opens a node with coordinates x and y in this percolation matrix.
     *
     * @throws IllegalArgumentException if the coordinates are incorrect
     */
    public abstract void open(int x, int y) throws IllegalArgumentException;

    /**
     * Returns true if the node with the given coordinates is open.
     * Otherwise, returns false.
     */
    public abstract boolean isOpen(int x, int y) throws IllegalArgumentException;

    public abstract int openNodesCount();

    /**
     * Returns true if the top of the {@link matrix.PercolationMatrix} is connected to the
     */
    public abstract boolean percolates();
}
