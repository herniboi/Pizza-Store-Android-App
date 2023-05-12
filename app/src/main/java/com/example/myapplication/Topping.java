package com.example.myapplication;

/**
 * creates the enum class toppings
 * @author Andy Li, Henry lin
 */
public enum Topping {
    Pineapple, Ham, Pepperoni, Sausage, Chicken, Beef, GreenPepper, Onion, Mushroom, Cheese, BlackOlives;

    /**
     * converts the size from enum to a string
     * @param topping
     * @return string form of the size
     */
    public String toString(Topping topping) {
        if (topping.equals(BlackOlives)){
            return "Black Olives";
        } else if (topping.equals(GreenPepper)) {
            return "Green Pepper";
        } else if (topping.equals(Pineapple)) {
            return "Pineapple";
        } else if (topping.equals(Ham)) {
            return "Ham";
        } else if (topping.equals(Pepperoni)) {
            return "Pepperoni";
        } else if (topping.equals(Sausage)) {
            return "Sausage";
        } else if (topping.equals(Chicken)) {
            return "Chicken";
        } else if (topping.equals(Beef)) {
            return "Beef";
        } else if (topping.equals(Onion)) {
            return "Onion";
        } else if (topping.equals(Cheese)) {
            return "Cheese";
        } else if (topping.equals(Mushroom)) {
            return "Mushroom";
        } else {
            return "";
        }
    }
}
