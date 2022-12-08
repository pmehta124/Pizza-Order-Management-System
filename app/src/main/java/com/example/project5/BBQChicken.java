package com.example.project5;

import java.util.ArrayList;
import java.util.List;

/**
 * BBQ Chicken Class that extends Pizza to create a specialized pizza
 *
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public class BBQChicken extends Pizza {
    private double price = 0.0;
    private static final double smallPrice = 13.99;
    private static final double mediumPrice = 15.99;
    private static final double largePrice = 17.99;

    /**
     * Constructor for the BBQ Chicken Pizza that sets the default values for the toppings, and crust
     *
     * @param crust Crust for the BBQ Chicken pizza based on the style of the pizza
     */
    public BBQChicken(Crust crust) {
        List<Topping> toppingsBBQChicken = new ArrayList<Topping>();
        toppingsBBQChicken = new ArrayList<Topping>();
        toppingsBBQChicken.add(Topping.BBQCHICKEN);
        toppingsBBQChicken.add(Topping.GREENPEPPER);
        toppingsBBQChicken.add(Topping.PROVOLONE);
        toppingsBBQChicken.add(Topping.CHEDDAR);
        setToppings(toppingsBBQChicken);
        setCrust(crust);
    }

    /**
     * evaluates the new price of the pizza based on the price
     *
     * @return the price of the pizza based on the size the user inputted
     */
    @Override
    public double price() {
        if (Size.SMALL.equals(getSize())) {
            price = smallPrice;
        } else if (Size.MEDIUM.equals(getSize())) {
            price = mediumPrice;
        } else if (Size.LARGE.equals(getSize())) {
            price = largePrice;
        }
        return price;
    }
}

