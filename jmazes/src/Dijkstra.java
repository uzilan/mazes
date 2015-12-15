public class Dijkstra {

    public static void main(String[] args) {
        DistanceGrid grid = new DistanceGrid(10, 10);
        BinaryTree.on(grid);

        Cell start = grid.get(0, 0);
        Distances distances = start.distances();

        grid.setDistances(distances.distances());
        System.out.println(grid);

        System.out.println("path from northwest corner to southwest corner:");
        grid.setDistances(distances.pathTo(grid.get(grid.rowList().size() - 1, 0)).distances());
        System.out.println(grid);
    }
}
