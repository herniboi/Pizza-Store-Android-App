package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Activity class for the activity_view.fxml
 * @author Henry Lin, Andy Li
 */
public class ViewActivity extends AppCompatActivity {
    private TextView pNum, subTotal, tax, total;
    private ListView pizzaList;
    protected Order order = MainActivity.order;
    protected StoresOrders allOrder = MainActivity.allOrders;

    private ArrayList<String> pizzas = new ArrayList<>();
    private int listNum;
    ArrayAdapter arrayAdapter;
    public static final DecimalFormat FORMAT = new DecimalFormat( "#0.00" );
    /**
     * populates the phoneNumber TextView, subTotal TextView, total TextView, pizzaList list view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        pNum = findViewById(R.id.phoneNumberOrder);
        subTotal = findViewById(R.id.subtotal);
        tax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);
        pizzaList = findViewById(R.id.pizzas);

        subTotal.setText("Subtotal: " + FORMAT.format(order.getSubTotal()));
        tax.setText("Tax: " + FORMAT.format(order.getSalesTax()));
        total.setText("Total: " + FORMAT.format(order.getTotal()));
        pNum.setText("Phone Number: " + order.getPNumber());

        for (int i = 0; i < order.getSize(); i++){
            pizzas.add(order.getPizza(i));
        }
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, pizzas);
        pizzaList.setAdapter(arrayAdapter);

        pizzaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                listNum = arg2;
            }
        });
    }

    /**
     * Adds order of all pizzas added
     * @param view
     */
    public void addToOrder(View view) {
        if (order.getSize() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "This order is empty.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        for (int i = 0; i < allOrder.getSize(); i++){
            if(order.getPNumber().equals(allOrder.getPNumber(i))) {
                Toast toast = Toast.makeText(getApplicationContext(), "This number already has a number.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        }
        allOrder.add(order);
        Toast toast = Toast.makeText(getApplicationContext(), "Your order was placed.", Toast.LENGTH_SHORT);
        toast.show();
        subTotal.setText("Subtotal:");
        tax.setText("Tax:");
        total.setText("Total:");
        pizzaList.setAdapter(null);
    }

    /**
     * removes order of all pizzas added
     * @param view
     */
    public void removePizza(View view) {
        if (order.getSize() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "There is no pizza to remove.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        order.removePizza(pizzas.get(listNum));
        pizzas.remove(listNum);
        pizzaList.setAdapter(arrayAdapter);
        subTotal.setText("Subtotal: " + FORMAT.format(order.getSubTotal()));
        tax.setText("Tax: " + FORMAT.format(order.getSalesTax()));
        total.setText("Total: " + FORMAT.format(order.getTotal()));
    }
}