package com.example.myapplication;

/**
 * Pepperoni pizza class with methods specific to this type of pizza.
 * @author Henry Lin, Andy Li
 */
public class Pepperoni extends Pizza {
    /**
     * Constructor method that adds the 2 essential Pepperoni toppings.
     */
    public Pepperoni() {
        toppings.add(Topping.Pepperoni);
    }

    private static final double SMALL = 8.99;
    private static final double MEDIUM = 10.99;
    private static final double LARGE = 12.99;
    private static final double TOPPING_PRICE = 1.49;
    private static final int MIN_TOPPING = 1;
    private static final int PEPPERONI_TYPE = 3;

    /**
     * Calculates the price of the Hawaiian pizza.
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
        return PEPPERONI_TYPE;
    }

    /**
     * Gets all the toppings and lists them in a String for printing
     * @return the string of the toppings.
     */
    @Override
    public String getToppings(){
        String getTopping = new String();
        for (int i = 0 ; i < toppings.size(); i++){
            getTopping = getTopping.concat(toppings.get(i).toString() + ", ");
        }
        return getTopping;
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
     * Checks if two Hawaiian pizzas are the same.
     * @param pizza to be compared to
     * @return whether or not the two pizzas are equal.
     */
    @Override
    public boolean equals(Pizza pizza) {
        if (this.size != pizza.size){
            return false;
        } else if (pizza.checkType() != PEPPERONI_TYPE) {
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
