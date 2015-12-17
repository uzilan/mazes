import util.ImageSaver;

import java.util.stream.IntStream;

public class AldousBroderColored {

    public static void main(String[] args) {

        IntStream.range(0, 6).
                forEach(n -> {
                    ColoredGrid grid = new ColoredGrid(20, 20);
                    AldousBroder.on(grid);

                    Cell middle = grid.get(grid.rows() / 2, grid.columns() / 2);
                    grid.setDistances(middle.distances());

                    String filename = String.format("jmazes/aldous_broder_%02d.png", n);
                    ImageSaver.saveImage(grid.toImage(), filename);
                    System.out.println("saved " + filename);
                });
    }
}
