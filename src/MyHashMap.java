public class MyHashMap {
    private class HashKeyValuePair {
        int hashKey;
        Object value;

        public HashKeyValuePair(Object key, Object value){
            this.hashKey = key.hashCode();
            this.value = value;
        }
        public boolean isSameKey(Object key){
            return hashKey == key.hashCode();
        }
    }

    MyArrayList<HashKeyValuePair> list;

    private HashKeyValuePair getKVP(Object key){
        for (int i = 0; i < list.size(); i++) {
            HashKeyValuePair keyValuePair = list.get(i);
            if(keyValuePair.isSameKey(key))
                return keyValuePair;
        }
        return null;
    }
    public boolean keyInTable(Object key){
        boolean in = false;
        for (int i = 0; i < list.size(); i++) {
            HashKeyValuePair keyValuePair = list.get(i);
            in = in || keyValuePair.isSameKey(key);
        }
        return in;
    }

    public void put(Object key, Object value){
        if(!keyInTable(key)){
            list.add(new HashKeyValuePair(key, value));
        } else {
            getKVP(key).value = value;
        }
    }
    public void remove(Object key){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).isSameKey(key))
                list.remove(i);
        }
    }
    public void clear(){
        list.clear();
    }
    public int size(){
        return list.size();
    }
    public Object get(Object key){
        if(!keyInTable(key)) return null;
        return getKVP(key).value;
    }
}
