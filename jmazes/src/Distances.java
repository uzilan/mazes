import java.util.*;

public class Distances {

    private Cell root;

    private Map<Cell, Integer> cells;

    public Distances(Cell root) {
        this.root = root;
        cells = new HashMap<>();
        cells.put(root, 0);
    }

    public Map<Cell, Integer> distances() {
        return cells;
    }

    public void setDistance(Cell cell, int distance) {
        cells.put(cell, distance);
    }

    public Set<Cell> cells() {
        return cells.keySet();
    }

    public Distances pathTo(Cell goal) {
        Cell current = goal;

        Distances breadcrumbs = new Distances(root);
        breadcrumbs.setDistance(current, cells.get(current));

        while (current != root) {

            for (Cell neighbor : current.getLinks()) {
                if (cells.get(neighbor) < cells.get(current)) {
                    breadcrumbs.setDistance(neighbor, cells.get(neighbor));
                    current = neighbor;
                    break;
                }
            }
        }

        return breadcrumbs;
    }

    public List<Object> max() {
        int maxDistance = 0;
        Cell maxCell = root;

        for (Map.Entry<Cell, Integer> entry : cells.entrySet()) {
            Cell cell = entry.getKey();
            Integer distance = entry.getValue();

            if (distance > maxDistance) {
                maxCell = cell;
                maxDistance = distance;
            }
        }
        return Arrays.asList(maxCell, maxDistance);
    }
}
