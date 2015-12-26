package com.landsmann.mazes.ui;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private Image image = null;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {

            int width = getWidth() / 2 - image.getWidth(null) / 2;
            int height = getHeight() / 2 - image.getHeight(null) / 2;

            g.drawImage(image, width, height, this);
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
