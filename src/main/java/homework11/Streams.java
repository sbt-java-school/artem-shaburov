package homework11;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Дз 11 (по моим подсчетам)
 * Собственная реализация стрима
 *
 * @author artem
 */
public class Streams<T> {
    /** original collection */
    private List<T> collection;

    public Streams(List<T> collection) {
        this.collection = collection;
    }

    public static <T> Streams<T> of(List<T> collection) {
        return new Streams<>(collection);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        // работаем с копией коллекции
        List<T> copiedCollection = new ArrayList<>(collection);
        Iterator<T> iterator = copiedCollection.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (!predicate.test(t)) {
                iterator.remove();
            }
        }
        return new Streams<>(copiedCollection);
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> function) {
        List<R> copiedCollection = new ArrayList<>(collection.size());
        for (T t : collection) {
            R object = function.apply(t); // function result
            copiedCollection.add(object);
        }
        return new Streams<>(copiedCollection);
    }

    public <R, S> Map<R, S> toMap(Function<? super T, R> function1, Function<? super T, S> function2) {
        Map<R, S> map = new HashMap<>(collection.size());
        for (T t : collection) {
            R key = function1.apply(t);
            S value = function2.apply(t);
            map.put(key, value);
        }
        return map;
    }

    public List<T> getCollection() {
        return collection;
    }
}
