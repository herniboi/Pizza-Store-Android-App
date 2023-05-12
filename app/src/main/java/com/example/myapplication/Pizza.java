package com.example.myapplication;

import java.util.ArrayList;


/**
 * Parent pizza class, containing abstract methods and protected.
 * @author Henry Lin, Andy Li
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    public abstract double price();
    public abstract void addToppings(Topping topping);
    public abstract void removeToppings(Topping topping);
    public abstract int checkType();
    public abstract String getSize();
    public abstract String getToppings();
    public abstract boolean equals(Pizza pizza);
}