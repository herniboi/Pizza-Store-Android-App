package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Activity classes for activity_store.xml
 * @author Henry Lin, Andy Li
 */
public class StoreActivity extends AppCompatActivity {
    protected Order order = MainActivity.order;
    protected StoresOrders allOrder = MainActivity.allOrders;

    private Spinner pNumSpinner;
    private ListView pizzaList;
    private TextView total;

    public static final DecimalFormat FORMAT = new DecimalFormat( "#0.00" );

    private ArrayList<String> pNums = new ArrayList<>();
    private ArrayAdapter spinAdapter;

    /**
     * populates the spinner for the phone number, TextView for the total and pizzaList
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        pNumSpinner = findViewById(R.id.phoneNums);
        total = findViewById(R.id.orderTotal);
        pizzaList = findViewById(R.id.pizzaDisp);

        for (int i = 0; i < allOrder.getSize(); i++) {
            pNums.add(allOrder.getPNumber(i));
        }
        spinAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, pNums);
        pNumSpinner.setAdapter(spinAdapter);
        pNumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                display();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });
        display();
    }

    /**
     * removes an order from the list of all orders
     *
     * @param view
     */
    public void removeOrder(View view) {
        for (int i = 0; i < allOrder.getSize(); i++) {
            if (allOrder.getPNumber(i).equals(pNumSpinner.getSelectedItem().toString())) {
                allOrder.remove(i);
            }
        }
        pNums.remove(pNumSpinner.getSelectedItem());
        spinAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, pNums);
        pNumSpinner.setAdapter(spinAdapter);
        pizzaList.setAdapter(null);
        total.setText("Order Total:");
        display();
    }

    /**
     * displays all items of the currently selected order
     */
    private void display() {
        ArrayList<String> pizzas = new ArrayList<>();

        for (int i = 0; i < allOrder.getSize(); i++) {
            if(allOrder.getPNumber(i).equals(pNumSpinner.getSelectedItem().toString())) {
                total.setText("Order Total: " + FORMAT.format(allOrder.getPrice(i)));
                for (int j = 0; j < allOrder.getOrderSize(i); j++) {
                    pizzas.add(allOrder.getPizza(i, j));
                    ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, pizzas);
                    pizzaList.setAdapter(listAdapter);
                }
            }
        }
    }
}