package com.landsmann.mazes.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusPanel extends JPanel {

    private JLabel statusLabel = new JLabel("");

    public StatusPanel(JFrame frame) {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(frame.getWidth(), 16));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(statusLabel);
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }
}
