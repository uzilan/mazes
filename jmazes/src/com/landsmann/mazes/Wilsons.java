package com.landsmann.mazes;

import com.landsmann.mazes.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Wilsons {

    public static Grid on(Grid grid) {
        List<Cell> unvisited = new ArrayList<>();
        unvisited.addAll(grid.cells());

        Cell first = Utils.sample(unvisited);
        unvisited.remove(first);

        while (!unvisited.isEmpty()) {

            Cell cell = Utils.sample(unvisited);

            List<Cell> path = new ArrayList<>();
            path.add(cell);

            while (unvisited.contains(cell)) {
                cell = Utils.sample(cell.neighbors());

                int position = path.indexOf(cell);

                if (position > -1) {
                    path = path.subList(0, position + 1);
                } else {
                    path.add(cell);
                }
            }

            for (int index = 0; index < path.size() - 1; index++) {
                path.get(index).link(path.get(index + 1));

                unvisited.remove(path.get(index));
            }
        }

        return grid;
    }
}
