package com.landsmann.mazes;

import java.util.Random;

public class AldousBroder {

    public static Grid on(Grid grid) {
        Cell cell = grid.randomCell();
        int unvisited = grid.size() - 1;

        Random random = new Random();
        while (unvisited > 0) {

            int index = random.nextInt(cell.neighbors().size());
            Cell neighbor = cell.neighbors().get(index);

            if (neighbor.getLinks().isEmpty()) {
                cell.link(neighbor);
                unvisited--;
            }

            cell = neighbor;
        }

        return grid;
    }
}
