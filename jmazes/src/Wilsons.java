import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Wilsons {

    public static Grid on(Grid grid) {
        List<Cell> unvisited = new ArrayList<>();
        unvisited.addAll(grid.cells());

        Cell first = Utils.sample(unvisited);
        unvisited.remove(first);

        showUnvisited(unvisited);

        while (!unvisited.isEmpty()) {

            Cell cell = Utils.sample(unvisited);

            showCell(cell);

            List<Cell> path = new ArrayList<>();
            path.add(cell);

            showPath(path);

            while (unvisited.contains(cell)) {
                cell = Utils.sample(cell.neighbors());

                showCell(cell);

                int position = path.indexOf(cell);

                showPosition(position);

                if (position > -1) {
                    path = path.subList(0, position + 1);
                } else {
                    path.add(cell);
                }

                showPath(path);
            }

            for (int index = 0; index < path.size() - 1; index++) {
                path.get(index).link(path.get(index + 1));

                showLink(path.get(index), path.get(index + 1));

                unvisited.remove(path.get(index));

                showUnvisited(unvisited);
            }
        }

        return grid;
    }

    private static void showLink(Cell cell1, Cell cell2) {
        System.out.println("linking " + cell1 + " with " + cell2);
    }

    private static void showPosition(int position) {
        System.out.println("position: " + position + " => " + (position == -1 ? "not found in path" : "found in path"));
    }

    private static void showPath(List<Cell> path) {
        System.out.println("path: " + path);
    }

    private static void showCell(Cell cell) {
        System.out.println("cell: " + cell);
    }

    private static void showUnvisited(List<Cell> unvisited) {
        System.out.println("unvisited: " + unvisited);
    }
}
