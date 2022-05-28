package utils;

import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.rangeClosed;

/**
 * @author - wally55077@gmail.com
 */
public class StreamUtil {

    private StreamUtil() {
    }

    public static <T, R> List<R> mapToList(T[] array, Function<? super T, ? extends R> mapper) {
        return stream(array)
                .map(mapper)
                .collect(toList());
    }

    public static <T, R> List<R> mapToList(Collection<T> collection, Function<? super T, ? extends R> mapper) {
        return collection.stream()
                .map(mapper)
                .collect(toList());
    }

    public static <T> List<T> filterToList(T[] array, Predicate<? super T> predicate) {
        return filterToList(asList(array), predicate);
    }

    public static <T> List<T> filterToList(Collection<T> collection, Predicate<? super T> predicate) {
        return collection.stream()
                .filter(predicate)
                .collect(toList());
    }

    public static <T, R> List<R> flatMapToList(T[] array, Function<? super T, ? extends Stream<? extends R>> mapper) {
        return stream(array)
                .flatMap(mapper)
                .collect(toList());
    }

    public static <T, R> Stack<R> flatMapToStack(T[] array, Function<? super T, ? extends Stream<? extends R>> mapper) {
        return stream(array)
                .flatMap(mapper)
                .collect(toCollection(Stack::new));
    }

    public static <T> List<T> generate(int count, IntFunction<? extends T> mapper) {
        return rangeClosed(1, count)
                .mapToObj(mapper)
                .collect(toList());
    }

    public static String generate(int count, IntFunction<? extends String> mapper, CharSequence delimiter) {
        return rangeClosed(1, count)
                .mapToObj(mapper)
                .collect(joining(delimiter));
    }

    public static <T> String generate(Collection<T> collection, CharSequence delimiter) {
        return collection.stream()
                .map(String::valueOf)
                .collect(joining(delimiter));
    }

    public static <T, R> boolean isEmpty(Collection<T> collection, Function<? super T, ? extends Collection<R>> mapper) {
        return collection.stream()
                .map(mapper)
                .allMatch(Collection::isEmpty);
    }

    public static <T> long intersectionCount(Collection<T> collection, Collection<T> otherCollection) {
        return collection.stream()
                .filter(otherCollection::contains)
                .count();
    }
}
