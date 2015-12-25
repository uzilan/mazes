package com.landsmann.mazes.algorithms;

import com.landsmann.mazes.core.Cell;
import com.landsmann.mazes.core.DistanceGrid;
import com.landsmann.mazes.core.Distances;

import static com.landsmann.mazes.util.TupleFactory.Tuple2;

public class LongestPath {

    public static void main(String[] args) {
        DistanceGrid grid = new DistanceGrid(5, 5);
        BinaryTree.on(grid);

        Cell start = grid.get(0, 0);

        Distances distances = start.distances();
        Tuple2<Cell, Integer> max = distances.max();
        Cell newStart = max._1();
        int distance = max._2();

        Distances newDistances = newStart.distances();
        max = newDistances.max();
        Cell goal = max._1();
        distance = max._2();

        grid.setDistances(newDistances.pathTo(goal).distances());

        System.out.println(grid);
    }
}
