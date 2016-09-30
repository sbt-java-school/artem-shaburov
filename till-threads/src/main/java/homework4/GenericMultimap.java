package homework4;

import java.util.List;

/**
 * Created by art
 */
public interface GenericMultimap<K, V> {
    List<V> get(K key);
    void put(K key, V value);
}
