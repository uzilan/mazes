package com.landsmann.mazes.demos;

import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.algorithms.HuntAndKill;
import com.landsmann.mazes.util.ImageSaver;

import java.awt.image.BufferedImage;

public class HuntAndKillDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(20, 20);
        HuntAndKill.on(grid);

        String filename = "jmazes/hunt and kill.png";
        BufferedImage img = grid.toImage();
        ImageSaver.saveImage(img, filename);
        System.out.println("saved to " + filename);
    }
}
