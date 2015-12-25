package com.landsmann.mazes.ui;

import com.landsmann.mazes.algorithms.*;
import com.landsmann.mazes.core.Cell;
import com.landsmann.mazes.core.ColoredGrid;
import com.landsmann.mazes.core.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

;

public class UI extends JFrame {

    private static final String BINARY_TREE = "Binary tree";
    private static final String SIDEWINDER = "Sidewinder";
    private static final String ALDOUS_BRODER = "Aldous Broder";
    private static final String WILSONS = "Wilsons";
    private static final String HUNT_AND_KILL = "Hunt and kill";

    private static List<String> MAZE_TYPES = Arrays.asList(BINARY_TREE, SIDEWINDER, ALDOUS_BRODER, WILSONS, HUNT_AND_KILL);

    private JComboBox mazeChooser = new JComboBox();
    private JSpinner rowsSpinner = new JSpinner();
    private JSpinner columnsSpinner = new JSpinner();
    private JCheckBox colorCheckBox = new JCheckBox("Color");
    private JButton draw = new JButton("Draw");
    private MazePanel mazePanel = new MazePanel();
    private StatusPanel statusPanel = new StatusPanel(this);
    private Grid grid;

    public UI() {
        super("Maze-o-matic");

        MAZE_TYPES.forEach(t -> mazeChooser.addItem(t));
        rowsSpinner.setValue(20);
        columnsSpinner.setValue(20);

        JPanel top = new JPanel();

        top.add(mazeChooser);

        top.add(new JLabel("Rows:"));
        top.add(rowsSpinner);

        top.add(new JLabel("Columns:"));
        top.add(columnsSpinner);

        top.add(colorCheckBox);

        top.add(draw);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mazePanel, BorderLayout.CENTER);
        getContentPane().add(statusPanel, BorderLayout.SOUTH);

        draw.addActionListener(e -> {

            int rows = (int) rowsSpinner.getValue();
            int columns = (int) columnsSpinner.getValue();

            long start = System.currentTimeMillis();

            if (colorCheckBox.isSelected()) {
                grid = new ColoredGrid(rows, columns);
            } else {
                grid = new Grid(rows, columns);
            }

            String mazeType = mazeChooser.getSelectedItem().toString();
            switch (mazeType) {
                case BINARY_TREE:
                    BinaryTree.on(grid);
                    break;
                case SIDEWINDER:
                    Sidewinder.on(grid);
                    break;
                case ALDOUS_BRODER:
                    AldousBroder.on(grid);
                    break;
                case WILSONS:
                    Wilsons.on(grid);
                    break;
                case HUNT_AND_KILL:
                    HuntAndKill.on(grid);
                    break;
            }

            if (colorCheckBox.isSelected()) {
                Cell middle = grid.get(grid.rows() / 2, grid.columns() / 2);
                ((ColoredGrid) grid).setDistances(middle.distances());
            }

            mazePanel.setImage(grid.toImage());
            mazePanel.repaint();

            long millis = System.currentTimeMillis() - start;
            statusPanel.setStatus(mazeType + " took " + millis + " ms to generate");
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationByPlatform(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UI();
    }
}
