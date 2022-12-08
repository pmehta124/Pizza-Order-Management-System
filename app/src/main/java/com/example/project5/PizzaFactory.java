package com.example.project5;


/**
 * PizzaFactory interface class describes the methods that need to be implemented by all the pizza classes
 *
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public interface PizzaFactory {
    /**
     * Create a deluxe pizza
     *
     * @return a deluxe pizza
     */
    Pizza createDeluxe();

    /**
     * Create a meatzza pizza
     *
     * @return a meatzza pizza
     */
    Pizza createMeatzza();

    /**
     * Create a BBQ Chicken pizza
     *
     * @return a BBQ Chicken pizza
     */
    Pizza createBBQChicken();

    /**
     * Create a build your own pizza
     *
     * @return a build your own pizza
     */
    Pizza createBuildYourOwn();
}


