package com.landsmann.mazes.demos;

import com.landsmann.mazes.algorithms.RecursiveBackTracker;
import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.util.ImageSaver;

import java.awt.image.BufferedImage;

public class RecursiveBackTrackerDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(20, 20);
        RecursiveBackTracker.on(grid);

        String filename = "images/recursive backtracker.png";
        BufferedImage img = grid.toImage();
        ImageSaver.saveImage(img, filename);
        System.out.println("saved to " + filename);
    }
}
