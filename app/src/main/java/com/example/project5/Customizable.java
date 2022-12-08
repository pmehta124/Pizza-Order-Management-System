package com.example.project5;

/**
 * Customizable interface that identifies different methods that will need to be implemented in subclasses
 *
 * @param <E> Generic type to accept any object
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public interface Customizable<E> {
    /**
     * Add the object to the list, implemented later
     *
     * @param obj object to be added
     * @return true if it is added correctly, false if it is not added
     */
    boolean add(Object obj);

    /**
     * Remove the object from the list, implemented later
     *
     * @param obj object to be removed
     * @return true if it is removed correctly, false if it is not removed
     */
    boolean remove(Object obj);
}

