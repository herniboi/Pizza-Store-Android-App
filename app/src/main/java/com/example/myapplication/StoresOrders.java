package com.example.myapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Object that creates a list of all the orders
 * @author Henry Lin, Andy Li
 */
public class StoresOrders implements Serializable {
    private ArrayList<Order> storeOrders = new ArrayList<>();

    public static final DecimalFormat FORMAT = new DecimalFormat( "#0.00" );

    /**
     * exports the text to a file
     * @param file to be exported to
     */
    public void Export(File file){
        if (file == null)
            return;
        if (file.exists()) {
            System.out.println("File already exists.");
            System.exit(1);
        }
        try {
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < storeOrders.size(); i++){
                pw.print("Phone number: " + storeOrders.get(i).getPNumber() + "\n");
                for (int j = 0; j < storeOrders.get(i).getSize(); j++) {
                    pw.print("\t" + storeOrders.get(i).getPizza(j) + "\n");
                }
                pw.print("Total Price: " + FORMAT.format(storeOrders.get(i).getTotal()) + "\n\n");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds an order to the store orders list
     * @param order to add
     */
    public void add(Order order) {
        storeOrders.add(order);
    }

    /**
     * gets the total amount of orders
     * @return amount of orders
     */
    public int getSize() {
        return storeOrders.size();
    }

    /**
     * gets the phone number of an order
     * @param index of the order to evaluate
     * @return the phone number
     */
    public String getPNumber(int index) {
        return storeOrders.get(index).getPNumber();
    }

    /**
     * removes an order from the store orders list
     * @param index of the order to remove
     */
    public void remove(int index){
        storeOrders.remove(index);
    }

    /**
     * get the price of a specific controller
     * @param index of the order to evaluate
     * @return price of order
     */
    public double getPrice(int index) {
        return storeOrders.get(index).getTotal();
    }

    /**
     * gets the amount of pizzas in a specific order
     * @param index of the order to evaluate
     * @return amount of pizzas
     */
    public int getOrderSize(int index) {
        return storeOrders.get(index).getSize();
    }

    /**
     * gets the string of a pizza to be used to display
     * @param index the index of the order in the store orders list
     * @param pizza the index of the pizza in the order
     * @return the pizza displayed in String form
     */
    public String getPizza(int index, int pizza) {
        return storeOrders.get(index).getPizza(pizza);
    }
}
