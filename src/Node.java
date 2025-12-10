class Node {
    Node previous, next;
    Object value;

    public Node (Object value) {
        this(null, value);
    }
    public Node (Node previous, Object value) {
        this.previous = previous;
        this.value = value;
    }
    public Node newNode(Object value) {
        Node node = new Node(this, value);
        next = node;
        return node;
    }
    public Node getNext() {
        return next;
    }
    public Node getPrevious()   {
        return previous;
    }
    public Object getValue()   {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public void remove() {
        if(previous != null && next != null){
            previous.next = next;
            next.previous = previous;
            return;
        }
        if(previous != null){
            previous.next = null;
        }
    }
    public void removeAll() {
        remove();
        if(previous != null) previous.removeAll();
        if(next != null) next.removeAll();
    }
}