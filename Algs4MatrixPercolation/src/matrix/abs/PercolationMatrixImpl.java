package matrix.abs;

import unionfind.WeightedQuickUnionUF;
import utils.Console;
import utils.Pair;

import java.util.List;

import static utils.Console.formatText;

public abstract class PercolationMatrixImpl extends PercolationMatrixBase {
    private static final List<Pair<Integer, Integer>> OPEN_VECTORS = List.of(
            new Pair<>(1, 0),
            new Pair<>(-1, 0),
            new Pair<>(0, 1),
            new Pair<>(0, -1)
    );

    private final boolean[] openNodes;
    private int opened = 0;

    public final int sideLength;
    public final int totalNodes;
    public final WeightedQuickUnionUF unionFind;
    public int top;
    public int bottom;

    protected PercolationMatrixImpl(int sideLength) {
        this.sideLength = sideLength;
        totalNodes = sideLength * sideLength;
        this.unionFind = new WeightedQuickUnionUF(totalNodes + 2); // including the top and bottom nodes
        top = totalNodes;
        bottom = top + 1;
        openNodes = new boolean[totalNodes];

        connectVirtualNodes();
    }

    public int index(int x, int y) {
        return getIndex(x, y, sideLength);
    }

    public void open(int x, int y) {
        final int id = index(x, y);
        if (openNodes[id]) return;

        open(id);

        for (final Pair<Integer, Integer> vector : OPEN_VECTORS) {
            final int nextX = x + vector.t0(), nextY = y + vector.t1();
            final int nextId = safeIndex(nextX, nextY);

            if (nextId < 0 || !openNodes[nextId]) continue;
            unionFind.union(id, nextId);
        }
    }

    @Override
    public boolean isOpen(int x, int y) {
        return openNodes[index(x, y)];
    }

    @Override
    public int openNodesCount() {
        return opened;
    }

    @Override
    public boolean percolates() {
        return unionFind.connected(top, bottom);
    }

    @Override
    public String toString() {
        return MatrixOutput.stringify(this);
    }

    private void connectVirtualNodes() {
        final int bottomRowStart = index(0, sideLength - 1);

        for (int column = 0; column < sideLength; column++) {
            unionFind.union(top, column);
            unionFind.union(bottom, bottomRowStart + column);
        }
    }

    private void open(int id) {
        openNodes[id] = true;
        opened++;
    }

    private int safeIndex(int x, int y) {
        try {
            return index(x, y);
        } catch (IllegalArgumentException ignored) {
            return -1;
        }
    }

    boolean isConnectedToTop(int x, int y) {
        return unionFind.connected(index(x, y), top);
    }
}

abstract class MatrixOutput {
    private static final String nodeRepr = "  ▣  ";

    static String stringify(PercolationMatrixImpl matrix) {
        final StringBuilder stringBuilder = new StringBuilder();

        for (int y = 0; y < matrix.sideLength; y++)
            for (int x = 0; x < matrix.sideLength; x++) {
                String color = Console.Decorations.ANSI_WHITE;
                if (matrix.isOpen(x, y)) {
                    if (matrix.isConnectedToTop(x, y)) color = Console.Decorations.ANSI_GREEN;
                    else color = Console.Decorations.ANSI_BLUE;
                }

                final String node = formatText(nodeRepr, color);

                stringBuilder.append(node);
                if (x == matrix.sideLength - 1 && y != matrix.sideLength - 1)
                    stringBuilder.append('\n');
            }

        return stringBuilder.toString();
    }
}
