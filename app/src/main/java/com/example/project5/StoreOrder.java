package com.example.project5;


import java.util.ArrayList;

/**
 * This class defines an abstract data type StoreOrder that represents a
 * store's database of all orders. It can add and remove orders and export the orders to a txt file.
 *
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public class StoreOrder implements Customizable {

    private ArrayList<Order> orders;

    /**
     * Constructor creates a new StoreOrder object with orders set as a new
     * ArrayList.
     */
    public StoreOrder() {
        orders = new ArrayList<>();
    }

    /**
     * Returns an array list of orders.
     *
     * @return array list of orders.
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Adds an order to the store order database.
     *
     * @param obj Object representing the order to be added.
     * @return true if the object was added, false if otherwise (if obj is
     * already in orders or if obj is not an Order).
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Order) || orders.contains(obj)) {
            return false;
        }
        orders.add((Order) obj);
        return true;
    }

    /**
     * Removes an order from the store order database.
     *
     * @param obj Object representing the order to be removed.
     * @return true if the object was removed, false if otherwise (if obj is
     * not in orders).
     */
    @Override
    public boolean remove(Object obj) {
        if (!orders.contains(obj)) {
            return false;
        }
        orders.remove((Order) obj);
        return true;
    }

}
