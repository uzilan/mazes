package util;

public class TupleFactory {

    private TupleFactory() {
        // I'm a util class, please do not create me!
    }

    public static <X, Y> Tuple2 createTuple2(X x, Y y) {
        return new Tuple2Impl<>(x, y);
    }

    private static class Tuple2Impl<X, Y> implements Tuple2 {
        private final X x;
        private final Y y;

        protected Tuple2Impl(X x, Y y) {
            checkParameters(x, y);
            this.x = x;
            this.y = y;
        }

        @Override
        public X getFirst() {
            return x;
        }

        @Override
        public Y getSecond() {
            return y;
        }

        @Override
        public X _1() {
            return x;
        }

        @Override
        public Y _2() {
            return y;
        }

        @Override
        public String toString() {
            return String.format("[Tuple2<%s, %s>: (%s, %s)]", x.getClass().getSimpleName(), y.getClass().getSimpleName(), x, y);
        }

        private void checkParameters(X xToCheck, Y yToCheck) {
            if (xToCheck == null || yToCheck == null) {
                throw new IllegalArgumentException("Null Values are not allowed");
            }
        }
    }

    public interface Tuple2<X, Y> {
        X getFirst();

        Y getSecond();

        X _1();

        Y _2();

        @Override
        String toString();
    }
}
