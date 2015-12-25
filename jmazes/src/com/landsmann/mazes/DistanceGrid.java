package com.landsmann.mazes;

import java.util.HashMap;
import java.util.Map;

public class DistanceGrid extends Grid {

    private Map<Cell, Integer> distances = new HashMap<>();

    public DistanceGrid(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    protected String contentsOf(Cell cell) {
        if (!distances.isEmpty() && distances.keySet().contains(cell)) {
            return maybeAscii(distances.get(cell));
        } else {
            return super.contentsOf(cell);
        }
    }

    private String maybeAscii(int i) {
        if (i < 10) {
            return "" + i;
        }
        return Character.toString((char) (i + 55));
    }

    public Map<Cell, Integer> getDistances() {
        return distances;
    }

    public void setDistances(Map<Cell, Integer> distances) {
        this.distances = distances;
    }
}
