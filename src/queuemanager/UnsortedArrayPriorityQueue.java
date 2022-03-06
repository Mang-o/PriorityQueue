/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    private List<Object> list;

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

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            int i = tailIndex;
            storage[i] = new PriorityItem<>(item, priority);

        }
    }

    /**
     * Compares the priority of two given items.
     * 
     * Method inspired by the Collections class, which compares
     * two given items.
     */
    private int comparePriority(Object i, Object j) throws IllegalArgumentException{

        if (i == null || j == null) {
            throw new IllegalArgumentException();
        } else {
             return ((PriorityItem<T>) i).getPriority() > ((PriorityItem<T>) j).getPriority() ? ((PriorityItem<T>) i).getPriority() : ((PriorityItem<T>) i).getPriority() == ((PriorityItem<T>) j).getPriority() ? 0 : ((PriorityItem<T>) j).getPriority();
        }  
    }


    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
        
            list = Arrays.asList(storage);
            int priority = 0;
            int highestPriority = 0;
            Object lastPriority = 0;
            Object nextPriority = 0;           
            System.out.println("List size: " + storage.length);

            int i = 0;
            while (i < storage.length - 1) {
                for(int j = 1; j < storage.length - 1; j++) {

                    System.out.println("Last Priority: " + list.get(i));
                    lastPriority = list.get(i);
                    System.out.println("Next Priority: " + list.get(j));
                    nextPriority = list.get(j);

                    if (j == storage.length - 1) {
                        System.out.println("Running if");
                        return ((PriorityItem<T>) storage[highestPriority]).getItem();
                    } else {
                        System.out.println("Running else");
                        System.out.println("j = " + j);
                        priority = comparePriority(lastPriority, nextPriority);

                        if(priority >= highestPriority) {
                            highestPriority = priority;
                        }

                        System.out.println("Highest priority: " + highestPriority);
                        i++;
                     }
                }
            }
            return ((PriorityItem<T>) storage[priority]).getItem();
        }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        int i, head = 0;

        if (isEmpty()) {

        } else {
            // Find highest priority
            
            

            for (i = 0; i < tailIndex; i++) {
                storage[i] = storage[i + 1];
            }
            tailIndex = tailIndex - 1;
        }

 

    }

    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }
}
