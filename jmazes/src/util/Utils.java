package util;

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
}

