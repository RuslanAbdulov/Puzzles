package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) {

        System.out.println(quarterly(100.0, 0.1));

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");

        Stream<String> source = list.stream();

        Stream<Integer> target = aggregate(source, Objects::equals).map(List::size);

        target.forEach(System.out::println);
    }


    /**
     * Aggregates items from source stream into list of items while supplied predicate is true when evaluated on previous and current item.
     * Can be seen as streaming alternative to Collectors.groupingBy when source stream is sorted by key.
     * @param source - source stream
     * @param predicate - predicate specifying boundary between groups of items
     * @param <T> The type over which the stream streams.
     * @return Stream of List&lt;T&gt; aggregated according to predicate
     */
    public static <T> Stream<List<T>> aggregate(Stream<T> source, BiPredicate<T, T> predicate) {
        return StreamSupport.stream(new AggregatingSpliterator<>(source.spliterator(),
                (a, e) -> a.isEmpty() || predicate.test(a.get(a.size() - 1), e)), false)
                .onClose(source::close);
    }


    static double quarterly(double amount, double rate) {
        for (int i = 0; i < 4; i++) {
            amount += amount * rate;
        }
        return amount;
    }
}
