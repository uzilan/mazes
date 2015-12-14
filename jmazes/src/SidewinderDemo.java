public class SidewinderDemo {

    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        Sidewinder.on(grid);

        System.out.println(grid);
    }
}
