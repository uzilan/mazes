package com.landsmann.mazes.ui;

import com.landsmann.mazes.core.Grid;
import com.landsmann.mazes.util.MazeCreator;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class UI extends JFrame {

    private JComboBox mazeChooser = new JComboBox();
    private JSpinner rowsSpinner = new JSpinner();
    private JSpinner columnsSpinner = new JSpinner();
    private JCheckBox colorCheckBox = new JCheckBox("Color");
    private JButton draw = new JButton("Draw");
    private MazePanel mazePanel = new MazePanel();
    private StatusPanel statusPanel = new StatusPanel(this);

    public UI() {
        super("Maze-o-matic");

        Arrays.stream(MazeCreator.MazeType.values())
                .forEach(t -> mazeChooser.addItem(t.getName()));
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
            String typeName = mazeChooser.getSelectedItem().toString();
            MazeCreator.MazeType type = MazeCreator.MazeType.getFromName(typeName);

            long start = System.currentTimeMillis();

            Grid grid = MazeCreator.create(type, rows, columns, colorCheckBox.isSelected());

            mazePanel.setImage(grid.toImage());
            mazePanel.repaint();

            long millis = System.currentTimeMillis() - start;
            statusPanel.setStatus(typeName + " took " + millis + " ms to generate");
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
