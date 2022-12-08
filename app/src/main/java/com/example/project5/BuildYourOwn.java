package com.example.project5;

import java.util.ArrayList;
import java.util.List;

/**
 * Build Your Own Pizza Class that extends Pizza to create a specialized pizza
 *
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public class BuildYourOwn extends Pizza implements Customizable {
    private double price = 0.0;
    private static final double smallPrice = 8.99;
    private static final double mediumPrice = 10.99;
    private static final double largePrice = 12.99;
    private static final double toppingMultiple = 1.59;

    /**
     * Sets the crust of the build your own pizza based on the defined style crust
     *
     * @param crust Crust for the Build Your Own pizza based on the style of the pizza
     */
    public BuildYourOwn(Crust crust) {
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
        if (getToppings() != null) {
            price += toppingMultiple * getToppings().size();
        }
        return price;
    }

    /**
     * @param obj the obj that needs to be added to the arraylist
     * @return true if the obj was added to the arraylist
     */
    @Override
    public boolean add(Object obj) {
        List<Topping> toppingsBuildYourOwn = getToppings();
        if (toppingsBuildYourOwn == null) {
            toppingsBuildYourOwn = new ArrayList<Topping>();
        }
        toppingsBuildYourOwn.add((Topping) obj);
        setToppings(toppingsBuildYourOwn);
        return true;
    }

    /**
     * @param obj the obj that needs to be removed to the arraylist
     * @return true if the obj was removed to the arraylist
     */
    @Override
    public boolean remove(Object obj) {
        List<Topping> toppingsBuildYourOwn = getToppings();
        if (toppingsBuildYourOwn == null) {
            return true;
        }
        toppingsBuildYourOwn.remove((Topping) obj);
        setToppings(toppingsBuildYourOwn);
        return true;
    }

}
