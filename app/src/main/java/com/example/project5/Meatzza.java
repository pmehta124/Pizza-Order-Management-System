package com.example.project5;


import java.util.ArrayList;
import java.util.List;

/**
 * Meatzza Class that extends Pizza to create a specialized pizza
 *
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public class Meatzza extends Pizza {
    private double price = 0.0;
    private static final double smallPrice = 15.99;
    private static final double mediumPrice = 17.99;
    private static final double largePrice = 19.99;

    /**
     * Constructor for the Meatzza Pizza that sets the default values for the toppings, and crust
     *
     * @param crust Crust for the Meatzza pizza based on the style of the pizza
     */
    public Meatzza(Crust crust) {
        List<Topping> toppingsMeatzza = new ArrayList<Topping>();
        toppingsMeatzza.add(Topping.SAUSAGE);
        toppingsMeatzza.add(Topping.PEPPERONI);
        toppingsMeatzza.add(Topping.HAM);
        toppingsMeatzza.add(Topping.BEEF);
        setToppings(toppingsMeatzza);
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
