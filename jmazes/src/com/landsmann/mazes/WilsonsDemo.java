package com.landsmann.mazes;

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
