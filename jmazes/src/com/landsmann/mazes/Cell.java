package com.landsmann.mazes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cell {

    private int row;
    private int column;

    private Cell north;
    private Cell south;
    private Cell east;
    private Cell west;

    private List<Cell> links = new ArrayList<>();

    public List<Cell> getLinks() {
        return links;
    }

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Cell link(Cell cell) {
        return link(cell, true);
    }

    public Cell link(Cell cell, boolean bidi) {
        links.add(cell);
        if (bidi) {
            cell.link(this, false);
        }
        return this;
    }

    public Cell unlink(Cell cell) {
        return unlink(cell, true);
    }

    public Cell unlink(Cell cell, boolean bidi) {
        links.remove(cell);
        if (bidi) {
            cell.unlink(this, false);
        }
        return this;
    }

    public boolean isLinked(Cell cell) {
        return links.contains(cell);
    }

    public List<Cell> neighbors() {
        return Arrays.asList(north, south, west, east)
                .stream()
                .filter(direction -> direction != null)
                .collect(Collectors.toList());
    }

    public Distances distances() {
        Distances distances = new Distances(this);
        List<Cell> frontier = Arrays.asList(this);

        while (frontier.size() != 0) {
            List<Cell> newFrontier = new ArrayList<>();

            for (Cell cell : frontier) {
                for (Cell linked : cell.links) {
                    if (distances.distances().containsKey(linked)) {
                        continue;
                    }
                    distances.setDistance(linked, distances.distances().get(cell) + 1);
                    newFrontier.add(linked);
                }
            }

            frontier = newFrontier;
        }

        return distances;
    }

    public Cell getNorth() {
        return north;
    }

    public void setNorth(Cell north) {
        this.north = north;
    }

    public Cell getSouth() {
        return south;
    }

    public void setSouth(Cell south) {
        this.south = south;
    }

    public Cell getEast() {
        return east;
    }

    public void setEast(Cell east) {
        this.east = east;
    }

    public Cell getWest() {
        return west;
    }

    public void setWest(Cell west) {
        this.west = west;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        //return Character.toString((char) ((row * 5 + column) + 65));
        return row + "," + column;
    }
}
