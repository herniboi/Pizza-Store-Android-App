package com.example.myapplication;

/**
 * Deluxe pizza class with methods specific to this type of pizza.
 * @author Henry Lin, Andy Li
 */
public class Deluxe extends Pizza {
    /**
     * Constructor method that adds the 5 essential Deluxe toppings.
     */
    public Deluxe() {
        toppings.add(Topping.Sausage);
        toppings.add(Topping.Onion);
        toppings.add(Topping.Pepperoni);
        toppings.add(Topping.Mushroom);
        toppings.add(Topping.GreenPepper);
    }

    private static final double SMALL = 12.99;
    private static final double MEDIUM = 14.99;
    private static final double LARGE = 16.99;
    private static final double TOPPING_PRICE = 1.49;
    private static final int MIN_TOPPING = 5;
    private static final int DELUXE_TYPE = 1;

    /**
     * Calculates the price of the Deluxe pizza.
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        double price;
        if (size.equals(Size.Small)) {
            price = SMALL;
        } else if (size.equals(Size.Medium)) {
            price = MEDIUM;
        } else {
            price = LARGE;
        }
        return price + (toppings.size() - MIN_TOPPING) * TOPPING_PRICE;
    }

    /**
     * Adds a topping to the pizza.
     * @param topping to be added to the pizza.
     */
    @Override
    public void addToppings(Topping topping){
        toppings.add(topping);
    }

    /**
     * Removes a topping to the pizza.
     * @param topping to be added to the pizza.
     */
    @Override
    public void removeToppings(Topping topping){
        toppings.remove(topping);
    }

    /**
     * Checks the type of the pizza
     * @return the type of pizza
     */
    @Override
    public int checkType(){
        return DELUXE_TYPE;
    }

    /**
     * Gets all the toppings and lists them in a String for printing
     * @return the string of the toppings.
     */
    @Override
    public String getToppings(){
        String getToppings = new String();
        for (int i = 0 ; i < toppings.size(); i++){
            getToppings = getToppings.concat(toppings.get(i).toString() + ", ");
        }
        return getToppings;
    }

    /**
     * Gets the size and turns it into a strings.
     * @return the string of the size.
     */
    @Override
    public String getSize(){
        String getSize = new String();
        getSize = getSize.concat(size.toString() + ", ");
        return getSize;
    }

    /**
     * Checks if two Deluxe pizzas are the same.
     * @param pizza to be compared to
     * @return whether or not the two pizzas are equal.
     */
    @Override
    public boolean equals(Pizza pizza) {
        if (this.size != pizza.size){
            return false;
        } else if (pizza.checkType() != DELUXE_TYPE) {
            return false;
        } else if (this.toppings.size() != pizza.toppings.size()) {
            return false;
        }
        for (int i = 0; i < pizza.toppings.size(); i++) {
            if (!this.toppings.get(i).equals(pizza.toppings.get(i))){
                return false;
            }
        }
        return true;
    }
}
