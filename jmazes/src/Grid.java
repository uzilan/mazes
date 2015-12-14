import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {

    private int rows;
    private int columns;

    private List<List<Cell>> grid = new ArrayList<>();

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        prepareGrid();
        configureCells();
    }

    private void prepareGrid() {
        for (int i = 0; i < rows; i++) {
            List<Cell> row = new ArrayList<>();
            grid.add(row);
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell(i, j);
                row.add(cell);
            }
        }
    }

    private void configureCells() {
        cells().stream().forEach(cell -> {
            int row = cell.getRow();
            int column = cell.getColumn();

            if (row > 0) {
                cell.setNorth(get(row - 1, column));
            }
            if (row < rows - 1) {
                cell.setSouth(get(row + 1, column));
            }
            if (column > 0) {
                cell.setWest(get(row, column - 1));
            }
            if (column < columns - 1) {
                cell.setEast(get(row, column + 1));
            }
        });
    }

    public Cell get(int row, int column) {
        return grid.get(row).get(column);
    }

    public Cell randomCell() {
        Random rand = new Random(System.currentTimeMillis());
        int row = rand.nextInt(rows);
        int column = rand.nextInt(columns);
        return get(row, column);
    }

    public int size() {
        return rows * columns;
    }

    public List<List<Cell>> rows() {
        return grid;
    }

    public List<Cell> cells() {
        return grid.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    protected String contentsOf(Cell cell) {
        return " ";
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("+")
                .append(stimes("---+", columns))
                .append("\n");

        rows().stream().forEach(row -> {
            StringBuilder top = new StringBuilder("|");
            StringBuilder bottom = new StringBuilder("+");

            row.stream().forEach(cell -> {
                String body = " " + contentsOf(cell) + " ";
                String eastBoundary = cell.isLinked(cell.getEast()) ? " " : "|";
                top.append(body).append(eastBoundary);

                String southBoundary = cell.isLinked(cell.getSouth()) ? "   " : "---";
                String corner = "+";
                bottom.append(southBoundary).append(corner);
            });

            output.append(top).append("\n");
            output.append(bottom).append("\n");
        });

        return output.toString();
    }

    private String stimes(String s, int times) {
        return IntStream.range(0, times)
                .mapToObj(i -> s)
                .collect(Collectors.joining());
    }
}
