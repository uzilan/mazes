package com.landsmann.mazes.demos;

import com.landsmann.mazes.core.Cell;
import com.landsmann.mazes.core.ColoredGrid;
import com.landsmann.mazes.algorithms.HuntAndKill;
import com.landsmann.mazes.util.ImageSaver;

public class HuntAndKillColored {

    public static void main(String[] args) {

        ColoredGrid grid = new ColoredGrid(20, 20);
        HuntAndKill.on(grid);

        Cell middle = grid.get(grid.rows() / 2, grid.columns() / 2);
        grid.setDistances(middle.distances());

        String filename = "images/hunt and kill colored.png";
        ImageSaver.saveImage(grid.toImage(), filename);
        System.out.println("saved " + filename);
    }
}
