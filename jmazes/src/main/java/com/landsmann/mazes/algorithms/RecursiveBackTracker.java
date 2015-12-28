package com.landsmann.mazes.algorithms;

import com.landsmann.mazes.core.Cell;
import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.util.Utils;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class RecursiveBackTracker {

    public static Grid on(Grid grid) {
        return on(grid, grid.randomCell());
    }

    public static Grid on(Grid grid, Cell startAt) {
        Stack<Cell> stack = new Stack<>();
        stack.push(startAt);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            List<Cell> neighbors = current.neighbors().stream()
                    .filter(c -> c.getLinks().isEmpty())
                    .collect(Collectors.toList());

            if (neighbors.isEmpty()) {
                stack.pop();
            } else {
                Cell neighbor = Utils.sample(neighbors);
                current.link(neighbor);
                stack.push(neighbor);
            }
        }

        return grid;
    }
}
