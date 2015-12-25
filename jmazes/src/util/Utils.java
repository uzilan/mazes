package util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {

    private Utils() {
        // utils class, do not instantiate
    }

    public static String stimes(String s, int times) {
        return IntStream.range(0, times)
                .mapToObj(i -> s)
                .collect(Collectors.joining());
    }

    public static <T> T sample(List<T> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}

