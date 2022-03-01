/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

/**
 *
 * @author Peter
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T> {
    
     /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The size of the storage array.
     */
    private final int capacity;

    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
     */
    private int tailIndex;
    
      /**
     * Create a new empty queue of the given size.
     *
     * @param size
     */
    public UnsortedArrayPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }
    
    public int findIndex(Object[] storage, int t) {
        if (storage == null) {
            return -1;
        }
        int len = storage.length;
        int i = 0;
        while (i < len) {
            if (storage[i] == (Integer) t) {
                return i;
            }
            else i = i + 1;
        }
       return -1; 
    }
    
    
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Scan backwards looking for insertion point */
            int i = tailIndex;
            while (i > 0 && ((PriorityItem<T>) storage[i - 1]).getPriority() < priority) {
                storage[i] = storage[i - 1];
                i = i - 1;
            }
            storage[i] = new PriorityItem<>(item, priority);
        }
    }
    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            int head_index = 1;
            int i = 0;
            while (i < storage.length) {
                if (storage[i] == head_index) {
                    System.out.println("Top of Queue is: " + storage[head_index].toString());
                } else {
                    
                }
            }
            return ((PriorityItem<T>) storage[head_index]).getItem();
        }
    }
    @Override
    public void remove() throws QueueUnderflowException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
