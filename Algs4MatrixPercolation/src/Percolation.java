import matrix.PercolationMatrix;
import requirements.PercolationRequirements;

public class Percolation extends PercolationRequirements {
    private final PercolationMatrix pMatrix;

    public Percolation(int n) {
        pMatrix = new PercolationMatrix(n);
    }

    @Override
    public int getOpenedCount() {
        return pMatrix.openNodesCount();
    }

    // row = y, column = x
    @Override
    public void open(int x, int y) {
        pMatrix.open(x, y);
    }

    // row = y, column = x
    @Override
    public boolean isOpened(int x, int y) {
        return pMatrix.isOpen(x, y);
    }

    @Override
    public boolean percolates() {
        return pMatrix.percolates();
    }
}
