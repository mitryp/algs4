package requirements;

public abstract class PercolationRequirements {
    public abstract int getOpenedCount();

    public abstract void open(int i, int j);

    public abstract boolean isOpened(int i, int j);

    public abstract boolean percolates();
}
