package com.landsmann.mazes;

import com.landsmann.mazes.util.ImageSaver;

public class AldousBroderDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(20,20);
        AldousBroder.on(grid);

        String filename = "jmazes/aldous_broder.png";
        ImageSaver.saveImage(grid.toImage(), filename);
        System.out.println("saved " + filename);
    }
}
