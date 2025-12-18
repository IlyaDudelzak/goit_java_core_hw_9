import java.util.NoSuchElementException;

public class MyHashMap<K, V> {
    private class HashKeyValuePair<K, V> {
        int hashKey;
//        final K key;
        V value;

        HashKeyValuePair<K, V> next;

        public HashKeyValuePair(K key, V value){
            this.hashKey = key.hashCode();
//            this.key = key;
            this.value = value;
        }
        public boolean isSameKey(K key){
            return hashKey == key.hashCode();
        }
    }

    private HashKeyValuePair[] elementData;
    private int size;

    public MyHashMap(){
        this(16);
    }

    public MyHashMap(int size){
        elementData = new HashKeyValuePair[size];
        size = 0;
    }

    private int getIndex(K key){
        return key.hashCode() & (elementData.length - 1);
    }

    private HashKeyValuePair<K, V> getKVPNoExc(K key){
        HashKeyValuePair<K, V> node = elementData[getIndex(key)];
        if(node == null){
            return null;
        }
        while(true){
            if (node.isSameKey(key)) {
                return node;
            }
            if(node.next == null) {
                return null;
            }
            node = node.next;
        }
    }

    private HashKeyValuePair<K, V> getKVP(K key){
        HashKeyValuePair<K, V> node = getKVPNoExc(key);
        if(node == null){
            throw new NoSuchElementException();
        }
        return null;
    }

    public boolean keyInTable(K key){
        return getKVPNoExc(key) != null;
    }

    public void put(K key, V value){
        int index = getIndex(key);
        HashKeyValuePair<K, V> node = getKVPNoExc(key);
        HashKeyValuePair<K, V> newNode = new HashKeyValuePair<K, V>(key, value);
        if(node == null){
            if(elementData[index] == null) {
                elementData[index] = newNode;
            } else {
                node = elementData[index];
                while(true){
                    if(node.next == null) {
                        node.next = newNode;
                    }
                    node = node.next;
                }
            }
        } else {
            node.value = value;
        }
        size++;
    }
    public void remove(K key) {
        int index = getIndex(key);
        if(elementData[index] == null) { throw new NoSuchElementException(); }
        size--;
        if(elementData[index].next == null) { elementData[index] = null; return; }
        HashKeyValuePair<K, V> node = getKVPNoExc(key);
        while(true){
            if(node.next == null) { throw new NoSuchElementException(); }
            if(node.next.isSameKey(key)){
                if(node.next.next == null){
                    node.next = null;
                } else {
                    node.next = node.next.next;
                }
            }
        }
    }
    public void clear() {
        elementData = new HashKeyValuePair[elementData.length];
        size = 0;
    }
    public int size() {
        return size;
    }
    public V get(K key){
        return getKVP(key).value;
    }
}
