import java.util.NoSuchElementException;

public class MyLinkedList<T> {
    private Node first, last;
    private int size;

    public MyLinkedList(){
        first = null;
        last = null;
        size = 0;
    }

    private Node getNode(int index) {
        if(index >= size || index < -size)
            throw new IndexOutOfBoundsException();
        if(index == 0)
            return first;
        if(index > 0){
            return getNode(first, index - 1);
        } else {
            if(index == -1)
                return last;
            return getNode(last, index + 1);
        }
    }
    private Node getNode(Node node, int index) {
        if(index == 0) return node;
        if(index > 0) return getNode(node.next, index - 1);
        return getNode(node.previous, index + 1);
    }

    public void add(T value) {
        size++;
        if(first == null) {
            Node node = new Node(value);
            first = node;
            last = node;
            return;
        }
        last = last.newNode(value);
    }
    public void remove(int index) {
        Node node = getNode(index);
        if(node != null)
            node.remove();
    }
    public int  size() {
        return size;
    }
    public void clear() {
        first.removeAll();
        first = null;
        last = null;
        size = 0;
    }
    public T get(int index) {
        Node node = getNode(index);
        return node != null ? (T)node.value : null;
    }
}
