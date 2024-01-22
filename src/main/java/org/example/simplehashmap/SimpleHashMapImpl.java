package org.example.simplehashmap;

import java.util.LinkedList;

/**
 * Класс представляет упрощенную реализацию HashMap
 * @param <K> ключ по которому храниться соответствующее значение
 * @param <V> значение соответствующее заданному ключу
 */
public class SimpleHashMapImpl<K, V> implements SimpleHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private LinkedList<Entry<K, V>>[] buckets;
    private int size = 0;
    private int capacity;
    private final float loadFactor;

    /**
     * Конструктор без параметров,
     * задающий дефолтные значения:
     * capacity = 16
     * loadFactor = 0.75
     */
    public SimpleHashMapImpl() {
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Конструктор с параметрами
     * @param capacity емкость массива баккетов
     * @param loadFactor задаёт отношение кол-ва объектов, хранящихся в SimpleHashMap,
     *                  к емкости (capacity), при превышении которого емкость увеличивается вдвое (х2)
     */
    public SimpleHashMapImpl(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public V get(K key) {
        int index = hashFunction(key, capacity);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public void remove(K key) {
        int index = hashFunction(key, capacity);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }

    @Override
    public void put(K key, V value) {

        if ((float) (size + 1) / capacity > loadFactor) {
            resize();
        }

        int index = hashFunction(key, capacity);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        LinkedList<Entry<K, V>>[] newBuckets = new LinkedList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new LinkedList<>();
        }

        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                int newIndex = hashFunction(entry.getKey(), newCapacity);
                newBuckets[newIndex].add(entry);
            }
        }

        this.buckets = newBuckets;
        this.capacity = newCapacity;
    }

    private int hashFunction(K key, int capacity) {
        return key.hashCode() % capacity;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
