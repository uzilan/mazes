package com.landsmann.mazes.algorithms;

import com.landsmann.mazes.core.Cell;
import com.landsmann.mazes.core.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sidewinder {

    public static Grid on(Grid grid) {
        Random random = new Random();
        grid.rowList().stream()
                .forEach(row -> {
                    List<Cell> run = new ArrayList<>();

                    row.stream()
                            .forEach(cell -> {
                                run.add(cell);

                                boolean atEasternBoundary = cell.getEast() == null;
                                boolean atNorthernBoundary = cell.getNorth() == null;

                                boolean shouldCloseOut = atEasternBoundary ||
                                        (!atNorthernBoundary && random.nextInt(2) == 0);

                                if (shouldCloseOut) {
                                    Cell member = sample(run);
                                    if (member.getNorth() != null) {
                                        member.link(member.getNorth());
                                    }
                                    run.clear();
                                } else {
                                    cell.link(cell.getEast());
                                }
                            });
                });
        return grid;
    }

    private static Cell sample(List<Cell> run) {
        Random random = new Random();
        int index = random.nextInt(run.size());
        return run.get(index);
    }
}
