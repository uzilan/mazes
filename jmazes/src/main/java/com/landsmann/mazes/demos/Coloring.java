package com.landsmann.mazes.demos;

import com.landsmann.mazes.algorithms.BinaryTree;
import com.landsmann.mazes.core.Cell;
import com.landsmann.mazes.core.ColoredGrid;
import com.landsmann.mazes.util.ImageSaver;

import java.awt.image.BufferedImage;

public class Coloring {

    public static void main(String[] args) {
        ColoredGrid grid = new ColoredGrid(25, 25);
        BinaryTree.on(grid);

        Cell start = grid.get(grid.rows() / 2, grid.columns() / 2);

        grid.setDistances(start.distances());

        String filename = "images/colorized.png";
        BufferedImage img = grid.toImage();
        ImageSaver.saveImage(img, filename);
        System.out.println("saved to " + filename);
    }
}
