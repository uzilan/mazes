package com.landsmann.mazes.demos;

import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.algorithms.Sidewinder;
import com.landsmann.mazes.util.ImageSaver;

import java.awt.image.BufferedImage;

public class SidewinderDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(4, 4);
        Sidewinder.on(grid);

        System.out.println(grid);

        BufferedImage img = grid.toImage();
        ImageSaver.saveImage(img, "images/maze.png");
    }
}
