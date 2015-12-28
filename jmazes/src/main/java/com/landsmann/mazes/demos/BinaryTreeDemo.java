package com.landsmann.mazes.demos;

import com.landsmann.mazes.algorithms.BinaryTree;
import com.landsmann.mazes.core.Cell;
import com.landsmann.mazes.core.Grid;

import java.util.List;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(4, 4);
        BinaryTree.on(grid);

        System.out.println(grid);

        List<Cell> deadends = grid.deadends();
        System.out.println(deadends.size() + " dead-ends");
    }
}
