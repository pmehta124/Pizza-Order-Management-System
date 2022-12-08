package com.example.project5;


import java.util.ArrayList;
import java.util.List;

/**
 * Deluxe Class that extends Pizza to create a specialized pizza
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public class Deluxe extends Pizza {
    private double price = 0.0;
    private static final double smallPrice = 14.99;
    private static final double mediumPrice = 16.99;
    private static final double largePrice = 18.99;

    /**
     * Constructor for the Deluxe Pizza that sets the default values for the toppings, and crust
     * @param crust Crust for the Deluxe pizza based on the style of the pizza
     */
    public Deluxe(Crust crust) {
        List<Topping> toppingsDeluxe = new ArrayList<Topping>();
        toppingsDeluxe.add(Topping.SAUSAGE);
        toppingsDeluxe.add(Topping.PEPPERONI);
        toppingsDeluxe.add(Topping.GREENPEPPER);
        toppingsDeluxe.add(Topping.ONION);
        toppingsDeluxe.add(Topping.MUSHROOM);
        setToppings(toppingsDeluxe);
        setCrust(crust);
    }

    /**
     * evaluates the new price of the pizza based on the price
     * @return the price of the pizza based on the size the user inputted
     */
    @Override
    public double price() {
        if(Size.SMALL.equals(getSize())) {
            price = smallPrice;
        } else if(Size.MEDIUM.equals(getSize())) {
            price = mediumPrice;
        } else if(Size.LARGE.equals(getSize())) {
            price = largePrice;
        }

        return price;
    }
}
