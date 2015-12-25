package com.landsmann.mazes.demos;

import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.algorithms.Wilsons;
import com.landsmann.mazes.util.ImageSaver;

import java.awt.image.BufferedImage;

public class WilsonsDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(20, 20);
        Wilsons.on(grid);

        String filename = "jmazes/wilsons.png";
        BufferedImage img = grid.toImage();
        ImageSaver.saveImage(img, filename);
        System.out.println("saved to " + filename);
    }
}
