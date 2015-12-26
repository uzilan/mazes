package com.landsmann.mazes.core;

import com.landsmann.mazes.util.TupleFactory;

import java.awt.*;

public class ColoredGrid extends Grid {

    private Distances distances;
    private int maximum;

    public ColoredGrid(int rows, int columns) {
        super(rows, columns);
    }

    public void setDistances(Distances distances) {
        this.distances = distances;
        TupleFactory.Tuple2<Cell, Integer> max = distances.max();
        Cell farthest = max._1();
        maximum = max._2();
    }

    @Override
    protected Color backgroundColorFor(Cell cell) {
        Integer distance = distances.getDistance(cell);
        if (distance == null) {
            return null;
        }
        float intensity = ((float) (maximum - distance)) / maximum;
        int dark = Math.round(255 * intensity);
        int bright = 128 + Math.round(127 * intensity);
        return new Color(dark, bright, dark);
    }
}
