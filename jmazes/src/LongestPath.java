import java.util.List;

public class LongestPath {

    public static void main(String[] args) {
        DistanceGrid grid = new DistanceGrid(5, 5);
        BinaryTree.on(grid);

        Cell start = grid.get(0, 0);

        Distances distances = start.distances();
        List<Object> max = distances.max();
        Cell newStart = (Cell) max.get(0);
        int distance = (Integer) max.get(1);

        Distances newDistances = newStart.distances();
        List<Object> max1 = newDistances.max();
        Cell goal = (Cell) max1.get(0);
        distance = (int) max1.get(1);

        grid.setDistances(newDistances.pathTo(goal).distances());

        System.out.println(grid);
    }
}
