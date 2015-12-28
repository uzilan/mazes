package com.landsmann.mazes.demos;

import com.landsmann.mazes.algorithms.*;
import com.landsmann.mazes.core.Grid;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DeadendCount {

    public static void main(String[] args) {
        List<Class<?>> algorithms = Arrays.asList(BinaryTree.class, Sidewinder.class,
                AldousBroder.class, Wilsons.class, HuntAndKill.class, RecursiveBackTracker.class);

        int tries = 100;
        int size = 20;

        Map<Class, Integer> averages = new HashMap<>();
        algorithms.forEach(algorithm -> {
            System.out.println("running " + algorithm.getSimpleName());

            List<Integer> deadendCounts = new ArrayList<Integer>();

            for (int i = 0; i < tries; i++) {
                Grid grid = new Grid(size, size);
                try {
                    Method on = algorithm.getMethod("on", Grid.class);
                    on.invoke(null, grid);
                    deadendCounts.add(grid.deadends().size());
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            int totalDeadends = deadendCounts.stream()
                    .mapToInt(i -> i)
                    .sum();
            averages.put(algorithm, totalDeadends / deadendCounts.size());
        });

        int totalCells = size * size;
        System.out.println();
        System.out.println("Average dead-end per " + size + "x" + size + " maze (" + totalCells + ") cells:");
        System.out.println();

        algorithms.sort((a1, a2) -> averages.get(a2).compareTo(averages.get(a1)));

        algorithms.forEach(algorithm -> {
            int percentage = (int) (averages.get(algorithm) * 100f / (size * size));
            System.out.println(
                    String.format("%1$s : %2$d/%3$d (%4$d%%)",
                            algorithm.getSimpleName(),
                            averages.get(algorithm),
                            totalCells,
                            percentage));
        });
    }
}
