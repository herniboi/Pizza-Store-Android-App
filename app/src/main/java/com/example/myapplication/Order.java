package com.example.myapplication;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.io.Serializable;

import static com.example.myapplication.PizzaMaker.createPizza;

/**
 * Order class that stores the phone number, price,
 * and array list of all the pizzas.
 * @author Henry Lin, Andy Li
 */
public class Order implements Serializable{
    private String pNumber;
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private double salesTax;
    private double subTotal;
    private double total;

    private static final double SALES_TAX = .06625;
    private static final int DELUXE_TYPE = 1;
    private static final int HAWAIIAN_TYPE = 2;
    private static final int NOTHING = 0;
    private static final int DELUXE_TOPPINGS = 5;
    private static final int HAWAIIAN_TOPPINGS = 2;
    private static final int PEPPORONI_TOPPINGS = 1;
    private static final int SKIPPED_PARAMETERS = 3;
    private static final int ADDITIONAL = 1;
    public static final DecimalFormat FORMAT = new DecimalFormat( "#0.00" );

    /**
     * default constructor
     */
    public Order(){
    }

    /**
     * Constructor when given a phone number
     * @param pNumber
     */
    public Order(String pNumber) {
        this.pNumber = pNumber;
    }

    /**
     * check the phone number to see if it exists.
     * @return whether the phone number was initialized
     */
    public boolean checkNumber(){
        if (pNumber == null){
            return false;
        } return true;
    }

    /**
     * returns the phone number
     * @return the phone number
     */
    public String getPNumber() {
        return pNumber;
    }

    /**
     * adds a pizza to the order
     * @param pizza
     */
    public void addToOrder(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * finds the subtotal of the order
     * @return the subtotal
     */
    public double getSubTotal(){
        subTotal = 0;
        for (int i = 0; i < pizzas.size(); i++){
            subTotal += pizzas.get(i).price();
        }
        return subTotal;
    }

    /**
     * finds the sales tax
     * @return the sales tax
     */
    public double getSalesTax(){
        salesTax = subTotal * SALES_TAX;
        return salesTax;
    }

    /**
     * finds the total price (subtotal + tax)
     * @return the total price
     */
    public double getTotal(){
        total = salesTax + subTotal;
        return total;
    }

    /**
     * finds the amount of pizzas in the order
     * @return the amount of pizzas
     */
    public int getSize() {
        return pizzas.size();
    }

    /**
     * finds the pizza string format for a specific pizza
     * @param index
     * @return a pizza in string format
     */
    public String getPizza(int index){
        String string = new String();
        if(pizzas.get(index).checkType() == DELUXE_TYPE) {
            string = "Deluxe pizza, ";
        } else if(pizzas.get(index).checkType() == HAWAIIAN_TYPE) {
            string = "Hawaiian pizza, ";
        } else{
            string = "Pepperoni pizza, ";
        }
        string = string.concat(pizzas.get(index).getToppings());
        string = string.concat(pizzas.get(index).getSize());
        string = string.concat(FORMAT.format(pizzas.get(index).price()));
        return string;
    }

    /**
     * removes a pizza from the order
     * @param text
     */
    public void removePizza(String text) {
        Pizza compareTo;
        for (int i = 0; i < pizzas.size(); i++) {
            StringTokenizer st1 = new StringTokenizer(text, ", ");
            int total = NOTHING;
            while(st1.hasMoreTokens()) {
                st1.nextToken();
                total++;
            }
            st1 = new StringTokenizer(text, ", ");

            StringTokenizer st2 = new StringTokenizer(st1.nextToken(), " ");
            String flavor = st2.nextToken();
            compareTo = createPizza(flavor);
            int toppings;
            if (flavor.equals("Deluxe")) {
                toppings = DELUXE_TOPPINGS;
            } else if (flavor.equals("Hawaiian")) {
                toppings = HAWAIIAN_TOPPINGS;
            } else {
                toppings = PEPPORONI_TOPPINGS;
            }
            for (int j = 0; j < toppings + ADDITIONAL; j++) {
                st1.nextToken();
            }
            for (int j = toppings + ADDITIONAL; j < total - SKIPPED_PARAMETERS; j++) {
                compareTo.addToppings(toTopping(st1.nextToken()));
            }

            String size = st1.nextToken();
            if (size.equals("Small")) {
                compareTo.size = Size.Small;
            } else if (size.equals("Medium")) {
                compareTo.size = Size.Medium;
            } else {
                compareTo.size = Size.Large;
            }

            if (pizzas.get(i).equals(compareTo)) {
                pizzas.remove(i);
                return;
            }
        }
    }

    /**
     * converts a topping from string to enum form
     * @param topping
     * @return enum form of a topping
     */
    private Topping toTopping(String topping){
        if (topping.equals("BlackOlives")){
            return Topping.BlackOlives;
        } else if (topping.equals("GreenPepper")) {
            return Topping.GreenPepper;
        } else if (topping.equals("Pineapple")) {
            return Topping.Pineapple;
        } else if (topping.equals("Ham")) {
            return Topping.Ham;
        } else if (topping.equals("Pepperoni")) {
            return Topping.Pepperoni;
        } else if (topping.equals("Sausage")) {
            return Topping.Sausage;
        } else if (topping.equals("Chicken")) {
            return Topping.Chicken;
        } else if (topping.equals("Beef")) {
            return Topping.Beef;
        } else if (topping.equals("Onion")) {
            return Topping.Onion;
        } else if (topping.equals("Cheese")) {
            return Topping.Cheese;
        } else {
            return Topping.Mushroom;
        }
    }
}
