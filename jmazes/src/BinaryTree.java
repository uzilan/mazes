import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BinaryTree {

    public static Grid on(Grid grid) {
        Random random = new Random();
        grid.cells().stream()
                .forEach(cell -> {
                    List<Cell> neighbors = neighbors(cell);

                    if (neighbors.size() > 0) {
                        int index = random.nextInt(neighbors.size());
                        Cell neighbor = neighbors.get(index);

                        if (neighbor != null) {
                            cell.link(neighbor);
                        }
                    }
                });
        return grid;
    }

    private static List<Cell> neighbors(Cell cell) {
        return Arrays.asList(cell.getNorth(), cell.getEast())
                .stream()
                .filter(direction -> direction != null)
                .collect(Collectors.toList());
    }
}
