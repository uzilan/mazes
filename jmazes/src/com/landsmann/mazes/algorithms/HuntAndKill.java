package com.landsmann.mazes.algorithms;

import com.landsmann.mazes.core.Cell;
import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.util.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class HuntAndKill {

    public static Grid on(Grid grid) {
        Cell current = grid.randomCell();

        while (current != null) {
            List<Cell> unvisitedNeighbors = current.neighbors().stream()
                    .filter(n -> n.getLinks().isEmpty())
                    .collect(Collectors.toList());

            if (!unvisitedNeighbors.isEmpty()) {
                Cell neighbor = Utils.sample(unvisitedNeighbors);
                current.link(neighbor);
                current = neighbor;
            } else {
                current = null;

                for (Cell cell : grid.cells()) {
                    List<Cell> visitedNeighbors = cell.neighbors().stream()
                            .filter(n -> !n.getLinks().isEmpty())
                            .collect(Collectors.toList());
                    if (cell.getLinks().isEmpty() && !visitedNeighbors.isEmpty()) {
                        current = cell;

                        Cell neighbor = Utils.sample(visitedNeighbors);
                        current.link(neighbor);

                        break;
                    }
                }
            }
        }

        return grid;
    }
}
