package com.landsmann.mazes.util;

import com.landsmann.mazes.algorithms.*;
import com.landsmann.mazes.core.Cell;
import com.landsmann.mazes.core.ColoredGrid;
import com.landsmann.mazes.core.Grid;

import java.util.Arrays;

public class MazeCreator {

    public enum MazeType {
        BINARY_TREE("Binary tree"),
        SIDEWINDER("Sidewinder"),
        ALDOUS_BRODER("Aldous Broder"),
        WILSONS("Wilsons"),
        HUNT_AND_KILL("Hunt and kill"),
        RECURSIVE_BACKTRACKER("Recursive backtracker");

        private String name;

        MazeType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static MazeType getFromName(String name) {
            return Arrays.stream(MazeType.values())
                    .filter(t -> t.name.equals(name))
                    .findFirst()
                    .get();
        }
    }

    public static Grid create(MazeType type, int rows, int columns, boolean color) {
        Grid grid;

        if (color) {
            grid = new ColoredGrid(rows, columns);
        } else {
            grid = new Grid(rows, columns);
        }

        switch (type) {
            case BINARY_TREE:
                BinaryTree.on(grid);
                break;
            case SIDEWINDER:
                Sidewinder.on(grid);
                break;
            case ALDOUS_BRODER:
                AldousBroder.on(grid);
                break;
            case WILSONS:
                Wilsons.on(grid);
                break;
            case HUNT_AND_KILL:
                HuntAndKill.on(grid);
                break;
            case RECURSIVE_BACKTRACKER:
                RecursiveBackTracker.on(grid);
                break;
        }

        if (color) {
            Cell middle = grid.get(grid.rows() / 2, grid.columns() / 2);
            ((ColoredGrid) grid).setDistances(middle.distances());
        }

        return grid;
    }
}
