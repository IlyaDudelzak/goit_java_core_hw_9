import jdk.internal.util.ArraysSupport;

import java.util.AbstractList;
import java.util.Arrays;

public class MyArrayList<E> extends AbstractList<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size;

    public MyArrayList(final int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    private Object[] grow(final int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    public void add (int index, E element) {
        if(size == elementData.length){
            grow();
        }
        if(index < 0) { throw new IllegalArgumentException("Index " + index + " less than zero."); }
        if(index > size) { throw new IndexOutOfBoundsException(); }
        if(index != size) {
            for (int i = size; i > index; i--) {
                elementData[i] = elementData[i - 1];
            }
        }
        elementData[index] = element;
        size++;
    }
    public E remove (int index) {
        if(index >= size){ throw new IndexOutOfBoundsException(); }
        Object element = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;

        return (E)element;
    }
    public void clear() {
        modCount++;
        final Object[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++) {
            es[i] = null;
        }
    }
    public int size() {
        return size;
    }
    public E get (int index){
        if(index >= 0 && index < size) {
            return (E)elementData[index];
        }
        throw new IndexOutOfBoundsException();
    }
}
