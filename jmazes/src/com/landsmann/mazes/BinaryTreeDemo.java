package com.landsmann.mazes;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(4, 4);
        BinaryTree.on(grid);

        System.out.println(grid);
    }
}
