package com.example.project5;

import java.io.Serializable;

/**
 * Item defines the data structure of an item to be displayed in the RecyclerView
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public class Item implements Serializable {
    private String pizzaText;

    /**
     * Parameterized constructor that sets the correct pizza string.
     * @param pizzaText String that represents the pizza
     */
    public Item(String pizzaText) {
        this.pizzaText = pizzaText;
    }

}
