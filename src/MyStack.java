public class MyStack<T> {
    private Node first, last;
    private int size;

    public MyStack(){
        first = null;
        last = null;
        size = 0;
    }

    private Node getNode( int index            ) {
        if(index >= size || index < -size)
            return null;
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
    private Node getNode( Node node, int index ) {
        if(index == 0) return node;
        if(index > 0) return getNode(node.next, index - 1);
        return getNode(node.previous, index + 1);
    }

    public void push   ( T value   ) {
        size++;
        if(first == null) {
            Node node = new Node(value);
            first = node;
            last = node;
            return;
        }
        last = last.newNode(value);
    }
    public void remove ( int index ) {
        Node node = getNode(index);
        if(node != null)
            node.remove();
    }
    public void clear  (           ) {
        first.removeAll();
        first = null;
        last = null;
        size = 0;
    }
    public int  size   (           ) {
        return size;
    }
    public T    peek   (           ) {
        return first != null ? (T)first.value : null;
    }
    public T    pop    (           ) {
        if(last == null) return null;
        Object value = last.value;
        if(last.previous != null){
            Node preLast = last.previous;
            last.remove();
            last = preLast;
        }
        return (T)value;
    }
}
