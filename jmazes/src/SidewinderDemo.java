import util.ImageSaver;

import java.awt.image.BufferedImage;

public class SidewinderDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(4, 4);
        Sidewinder.on(grid);

        System.out.println(grid);

        BufferedImage img = grid.toImage();
        ImageSaver.saveImage(img, "jmazes/maze.png");
    }
}
