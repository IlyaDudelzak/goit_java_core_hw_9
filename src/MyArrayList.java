public class MyArrayList<T> {
    private static final int defaultLength = 10;
    private static final int defaultExpandLength = 10;
    private Object[] list;
    private int size;

    public  MyArrayList (                  ) {
        list = new Object[defaultLength];
        size = 0;
    }
    private void expand (                  ){
        expand(defaultExpandLength);
    }
    private void expand ( int expandLength ) {
        Object[] newList = new Object[list.length + expandLength];
        System.arraycopy(list, 0, newList, 0, list.length);
    }
    public  void add    ( T value          ) {
        if(size == list.length){
            expand();
        }
        list[size] = value;
        size++;
    }
    public  void remove ( int index        ) {
        if(index >= size){
            return;
        }
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;
    }
    public  void clear  (                  ) {
        list = new Object[defaultLength];
        size = 0;
    }
    public  int  size   (                  ){
        return size;
    }
    public  T    get    ( int index        ){
        return index < size ? (T)list[index] : null;
    }
}