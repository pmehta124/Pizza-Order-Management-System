package com.example.project5;


import java.util.List;

/**
 * Pizza class is an abstract class that implements the Customizable
 * interface houses getters and setters for its instance variables, an abstract
 * price method to be implemented by each type of pizza class, and the add and remove toppings methods.
 *
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public abstract class Pizza implements Customizable {
    private List<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Mutator method for the list of toppings
     *
     * @param toppings toppings list to be set
     */
    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Accessor method to get the list of toppings
     *
     * @return list of toppings
     */
    public List<Topping> getToppings() {
        return toppings;
    }

    /**
     * Mutator method for the type of crust
     *
     * @param crust crust type to be set
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Accessor method to get the type of the crust
     *
     * @return crust of the pizza
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Mutator method for the size of the pizza
     *
     * @param size size selected by the user to be set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Accessor method to get the size of the pizza
     *
     * @return size of the pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * Abstract price method that is implemented by each subclass
     *
     * @return the price of the pizza
     */
    public abstract double price();

    /**
     * Add topping to the list of topping
     *
     * @param obj object to be added
     * @return true if toppings added
     */
    @Override
    public boolean add(Object obj) {
        return true;
    }

    /**
     * Remove topping from the list of topping
     *
     * @param obj object to be removed
     * @return true if toppings removed
     */
    @Override
    public boolean remove(Object obj) {
        return true;
    }

}
