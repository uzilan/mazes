package com.landsmann.mazes.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSaver {

    public static void saveImage(BufferedImage img, String filename) {
        File file = new File(filename);
        try {
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
