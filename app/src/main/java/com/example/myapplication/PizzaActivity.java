package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.myapplication.PizzaMaker.createPizza;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Activity class for activty_pizza.fxml
 *  @author Andy Li, Henry lin
 */
public class PizzaActivity extends AppCompatActivity {
    private ListView selectToppings, addedToppings;
    private TextView price, type;
    private Spinner size;

    protected Order order = MainActivity.order;
    private Pizza pizza;
    private String buttonClicked, currSize;
    private final ArrayList<String> select = new ArrayList<>();
    private final ArrayList<String> added = new ArrayList<>();

    private static final int MAX_TOPPINGS = 7;
    private static final int DELUXE_TYPE = 1;
    private static final int HAWAIIAN_TYPE = 2;
    private static final int PEPPERONI_TYPE = 3;
    public static final DecimalFormat FORMAT = new DecimalFormat( "#0.00" );

    /**
     * Creates a spinner drop down for the user to choose the size
     * of the pizza
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        price = findViewById(R.id.price);
        type = findViewById(R.id.type);
        size = findViewById(R.id.sizeSpinner);
        String sizes[] = {"small", "medium", "large"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sizes);
        size.setAdapter(adapter);
        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             *Obtains the size selected by the user
             * @param parentView
             * @param selectedItemView
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String size = parentView.getItemAtPosition(position).toString();
                currSize = size;
                if (size.equals("small")) {
                    pizza.size = Size.Small;
                } else if (size.equals("medium")) {
                    pizza.size = Size.Medium;
                } else {
                    pizza.size = Size.Large;
                }
                price.setText("Price: " + FORMAT.format(pizza.price()));
            }

            /**
             *Obtains the size selected by the user if nothing was selectedf
             * @param parentView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });

        Intent intent = getIntent();
        buttonClicked = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        if (Integer.parseInt(buttonClicked) == PEPPERONI_TYPE){
            createPepperoni();
            pizza = createPizza("Pepperoni");
            pizza.size = Size.Small;
        } else if (Integer.parseInt(buttonClicked) == HAWAIIAN_TYPE) {
            createHawaiian();
            pizza = createPizza("Hawaiian");
            pizza.size = Size.Small;
        } else {
            createDeluxe();
            pizza = createPizza("Deluxe");
            pizza.size = Size.Small;
        }
        price.setText("Price: " + FORMAT.format(pizza.price()));

        selectToppings = findViewById(R.id.selectList);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, select);
        selectToppings.setAdapter(arrayAdapter);

        addedToppings = findViewById(R.id.addedList);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, added);
        addedToppings.setAdapter(arrayAdapter2);

        selectToppings.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            /**
             *displays a toast display message if user tries to add more than 7 toppings
             * @param arg0
             * @param arg1
             * @param arg2
             * @param arg3
             */
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (pizza.toppings.size() >= MAX_TOPPINGS) {
                    Toast toast = Toast.makeText(getApplicationContext(), "You may only add up to 7 toppings.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                added.add(select.get(arg2));
                addedToppings.setAdapter(arrayAdapter2);

                pizza.toppings.add(toTopping(select.get(arg2)));

                select.remove(arg2);
                selectToppings.setAdapter(arrayAdapter);
                price.setText("Price: " + FORMAT.format(pizza.price()));
            }
        });

        addedToppings.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            /**
             *displays a message if user tries to remove a base topping of the pizza
             * @param arg0
             * @param arg1
             * @param arg2
             * @param arg3
             */
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (checkEssential(toTopping(added.get(arg2)))) {
                    Toast toast = Toast.makeText(getApplicationContext(), "You may not remove an essential topping.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                select.add(added.get(arg2));
                selectToppings.setAdapter(arrayAdapter);

                pizza.toppings.remove(toTopping(added.get(arg2)));

                added.remove(arg2);
                addedToppings.setAdapter(arrayAdapter2);
                price.setText("Price: " + FORMAT.format(pizza.price()));
            }
        });
    }

    /**
     *determines the base toppings of each respective pizza that cannot be removed
     * @param topping
     * @return
     */
    private boolean checkEssential(Topping topping) {
        if (Integer.parseInt(buttonClicked) == PEPPERONI_TYPE && topping.equals(Topping.Pepperoni))
            return true;
        else if (Integer.parseInt(buttonClicked) == HAWAIIAN_TYPE && (topping.equals(Topping.Pineapple) || topping.equals(Topping.Ham)))
            return true;
        else if(Integer.parseInt(buttonClicked) == DELUXE_TYPE) {
            if (topping.equals(Topping.GreenPepper) || topping.equals(Topping.Pepperoni) || topping.equals(Topping.Mushroom)
                    || topping.equals(Topping.Sausage) || topping.equals(Topping.Onion)) {
                return true;
            }
        }
        return false;
    }

    /**
     * creates Deluxe pizza
     */
    private void createDeluxe() {
        type.setText("Deluxe");
        for (com.example.myapplication.Topping Topping : Topping.values()) {
            if (Topping == com.example.myapplication.Topping.Pepperoni || Topping == com.example.myapplication.Topping.Sausage || Topping == com.example.myapplication.Topping.Onion ||
                    Topping == com.example.myapplication.Topping.Mushroom || Topping == com.example.myapplication.Topping.GreenPepper)
                added.add(Topping.toString(Topping));
            else
                select.add(Topping.toString(Topping));
        }
    }

    /**
     * creates a Hawaiian pizza
     */
    private void createHawaiian() {
        type.setText("Hawaiian");
        for (com.example.myapplication.Topping Topping : Topping.values()) {
            if (Topping == com.example.myapplication.Topping.Ham || Topping == com.example.myapplication.Topping.Pineapple)
                added.add(Topping.toString(Topping));
            else
                select.add(Topping.toString(Topping));
        }
    }

    /**
     * creates a pepperoni pizza
     */
    private void createPepperoni() {
        type.setText("Pepperoni");
        for (com.example.myapplication.Topping Topping : Topping.values()) {
            if (Topping == com.example.myapplication.Topping.Pepperoni)
                added.add(Topping.toString(Topping));
            else
                select.add(Topping.toString(Topping));
        }
    }

    /**
     * determines what toppings a pizza has
     * @param topping
     * @return
     */
    private Topping toTopping(String topping){
        if (topping.equals("Black Olives")){
            return Topping.BlackOlives;
        } else if (topping.equals("Green Pepper")) {
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

    /**
     * adds Pizza with respective toppings to order
     * @param view
     */
    public void addToOrder(View view) {
        order.addToOrder(pizza);
        Pizza temp = pizza;
        if (Integer.parseInt(buttonClicked) == DELUXE_TYPE) {
            pizza = createPizza("Deluxe");
        } else if (Integer.parseInt(buttonClicked) == HAWAIIAN_TYPE) {
            pizza = createPizza("Hawaiian");
        } else {
            pizza = createPizza("Pepperoni");
        }

        if (currSize.equals("small")){
            pizza.size = Size.Small;
        } else if (currSize.equals("medium")){
            pizza.size = Size.Medium;
        } else{
            pizza.size = Size.Large;
        }

        for(int i = pizza.toppings.size(); i < temp.toppings.size(); i++) {
            pizza.addToppings(temp.toppings.get(i));
        }

        Toast toast = Toast.makeText(getApplicationContext(), "Pizza was added to the order.", Toast.LENGTH_SHORT);
        toast.show();
    }

}