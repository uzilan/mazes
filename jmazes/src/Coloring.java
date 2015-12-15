import util.ImageSaver;

import java.awt.image.BufferedImage;

public class Coloring {

    public static void main(String[] args) {
        ColoredGrid grid = new ColoredGrid(25, 25);
        BinaryTree.on(grid);

        Cell start = grid.get(grid.rows() / 2, grid.columns() / 2);

        grid.setDistances(start.distances());

        String filename = "colorized.png";
        BufferedImage img = grid.toImage();
        ImageSaver.saveImage(img, filename);
        System.out.println("saved to " + filename);
    }
}
