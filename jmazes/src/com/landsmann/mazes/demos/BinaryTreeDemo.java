package com.landsmann.mazes.demos;

import com.landsmann.mazes.algorithms.BinaryTree;
import com.landsmann.mazes.core.Grid;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(4, 4);
        BinaryTree.on(grid);

        System.out.println(grid);
    }
}
