import java.util.NoSuchElementException;
import java.util.Objects;

public class MyHashMap<K, V> {
    
    private class HashKeyValuePair<K, V> {
        final K key;
        V value;
        HashKeyValuePair<K, V> next;

        public HashKeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean isSameKey(K otherKey) {
            return Objects.equals(this.key, otherKey);
        }
    }

    private HashKeyValuePair<K, V>[] elementData;
    private int size;
    private final double loadFactor = 0.75;

    public MyHashMap() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        this.elementData = new HashKeyValuePair[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public void doubleSize() {
        HashKeyValuePair<K, V>[] oldElementData = elementData;
        elementData = new HashKeyValuePair[oldElementData.length * 2];

        for (HashKeyValuePair<K, V> headNode : oldElementData) {
            HashKeyValuePair<K, V> current = headNode;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    private int getIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode()) % elementData.length;
    }

    private HashKeyValuePair<K, V> getKVPNoExc(K key) {
        int index = getIndex(key);
        HashKeyValuePair<K, V> current = elementData[index];
        
        while (current != null) {
            if (current.isSameKey(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private HashKeyValuePair<K, V> getKVP(K key) {
        HashKeyValuePair<K, V> node = getKVPNoExc(key);
        if (node == null) {
            throw new NoSuchElementException("Key not found: " + key);
        }
        return node;
    }

    public boolean keyInTable(K key) {
        return getKVPNoExc(key) != null;
    }

    public void put(K key, V value) {
        if (size >= elementData.length * loadFactor) {
            doubleSize();
        }

        int index = getIndex(key);
        HashKeyValuePair<K, V> existingNode = getKVPNoExc(key);

        if (existingNode != null) {
            existingNode.value = value;
        } else {
            HashKeyValuePair<K, V> newNode = new HashKeyValuePair<>(key, value);
            newNode.next = elementData[index];
            elementData[index] = newNode;
            size++;
        }
    }

    public void remove(K key) {
        int index = getIndex(key);
        HashKeyValuePair<K, V> current = elementData[index];
        HashKeyValuePair<K, V> previous = null;

        while (current != null) {
            if (current.isSameKey(key)) {
                if (previous == null) {
                    elementData[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
        throw new NoSuchElementException("Key not found for removal: " + key);
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        elementData = new HashKeyValuePair[elementData.length];
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        return getKVP(key).value;
    }
}
