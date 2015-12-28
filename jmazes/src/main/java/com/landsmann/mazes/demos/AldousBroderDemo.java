package com.landsmann.mazes.demos;

import com.landsmann.mazes.algorithms.AldousBroder;
import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.util.ImageSaver;

public class AldousBroderDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(20,20);
        AldousBroder.on(grid);

        String filename = "images/aldous_broder.png";
        ImageSaver.saveImage(grid.toImage(), filename);
        System.out.println("saved " + filename);
    }
}
