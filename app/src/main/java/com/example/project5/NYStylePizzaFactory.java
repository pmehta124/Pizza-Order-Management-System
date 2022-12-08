package com.example.project5;

/**
 * NY Pizza Factory class is a Pizza factory that creates different pizza based on the style
 *
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public class NYStylePizzaFactory implements PizzaFactory {
    /**
     * Create a deluxe style pizza and set the crust to the arbitrary type
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN);
    }

    /**
     * Create a Meatzza style pizza and set the crust to the arbitrary type
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HANDTOSSED);
    }

    /**
     * Create a BBQChicken style pizza and set the crust to the arbitrary type
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN);
    }

    /**
     * Create a Build Your Own style pizza and set the crust to the arbitrary type
     *
     * @return the pizza that was created
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HANDTOSSED);
    }
}