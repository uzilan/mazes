import util.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
                .append(Utils.stimes("---+", columns))
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

    public BufferedImage toImage() {
        return toImage(10);
    }

    public BufferedImage toImage(int cellSize) {
        int imgWidth = cellSize * columns;
        int imgHeight = cellSize * rows;

        Color background = Color.white;
        Color wall = Color.black;

        BufferedImage img = new BufferedImage(imgWidth + 1, imgHeight + 1, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = img.createGraphics();
        g2.setPaint(background);
        g2.fillRect(0, 0, img.getWidth(), img.getHeight());

        g2.setColor(wall);

        cells().forEach(cell -> {
            int x1 = cell.getColumn() * cellSize;
            int y1 = cell.getRow() * cellSize;
            int x2 = (cell.getColumn() + 1) * cellSize;
            int y2 = (cell.getRow() + 1) * cellSize;

            if (cell.getNorth() == null) {
                g2.drawLine(x1, y1, x2, y1);
            }

            if (cell.getWest() == null) {
                g2.drawLine(x1, y1, x1, y2);
            }

            if (!cell.isLinked(cell.getEast())) {
                g2.drawLine(x2, y1, x2, y2);
            }

            if (!cell.isLinked(cell.getSouth())) {
                g2.drawLine(x1, y2, x2, y2);
            }
        });

        return img;
    }
}
