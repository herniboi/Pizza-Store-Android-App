package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Main activity class for activity_main.fxml
 * @author Henry Lin, Andy Li

 */
public class MainActivity extends AppCompatActivity{
    private EditText pNum;
    private TextView test;

    public static final String EXTRA_MESSAGE = "com.example.android.myApplication.extra.message";
    public static final String ORDER_MESSAGE = "com.example.android.myApplication.order.message";
    public static final String ALL_ORDER_MESSAGE = "com.example.android.myApplication.all.order.message";
    private static final int DELUXE_TYPE = 1;
    private static final int HAWAIIAN_TYPE = 2;
    private static final int PEPPERONI_TYPE = 3;
    private static final int PHONENUMBER_LENGTH = 10;
    private static final int PIZZA_ACTIVITY_REQUEST_CODE = 1;

    private int buttonClicked = -1;
    protected static Order order = new Order();
    protected static StoresOrders allOrders = new StoresOrders();

    /**
     * Creates a bundle that will store the phone numbers and the orders attached to it
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pNum = findViewById(R.id.editTextPhone);

        test = findViewById(R.id.textView);
    }

    /**
     * stores button that was clicked
     * @param view
     */
    public void pepperoni(View view){
        buttonClicked = PEPPERONI_TYPE;
        goToSecondActivity();
    }
    /**
     * stores button that was clicked
     * @param view
     */
    public void hawaiian(View view){
        buttonClicked = HAWAIIAN_TYPE;
        goToSecondActivity();
    }
    /**
     * stores button that was clicked
     * @param view
     */
    public void deluxe(View view){
        buttonClicked = DELUXE_TYPE;
        goToSecondActivity();
    }

    /**
     * Determines if phone num ber was entered
     * will return a Toast display to indicate if it was not entered
     */
    private void goToSecondActivity(){
        if (!order.checkNumber()) {
            Context context = getApplicationContext();
            CharSequence text = "No phone number was entered.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(EXTRA_MESSAGE, Integer.toString(buttonClicked));
        startActivity(intent);
    }

    /**
     * opens the store Orders
     * @param view
     */
    public void storesOrders(View view) {
        Intent intent = new Intent(this, StoreActivity.class);
        startActivity(intent);
    }

    /**
     * opens the view Orders
     * @param view
     */
    public void launchViewOrders(View view){
        for (int i = 0; i < allOrders.getSize(); i++){
            if(order.getPNumber().equals(allOrders.getPNumber(i))) {
                Toast toast = Toast.makeText(getApplicationContext(), "This number already has a number.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        }

        if (!order.checkNumber()) {
            Context context = getApplicationContext();
            CharSequence text = "No phone number was entered.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    /**
     * checks if phone number was incorrectly inputed
     * checks if order has started
     * @param view
     */
    public void register(View view){
        if (!pNum.getText().toString().matches("[0-9]+")){
            Context context = getApplicationContext();
            CharSequence text = "A non numeric input was entered.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        if(pNum.length() != PHONENUMBER_LENGTH) {
            Context context = getApplicationContext();
            CharSequence text = "Phone number is of incorrect length.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        for (int i = 0; i < allOrders.getSize(); i++ ) {
            if (allOrders.getPNumber(i).equals(pNum.getText().toString())) {
                Context context = getApplicationContext();
                CharSequence text = "This number already has an order.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
        }

        order = new Order(pNum.getText().toString());
        Context context = getApplicationContext();
        CharSequence text = "Order Successfully Started";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}