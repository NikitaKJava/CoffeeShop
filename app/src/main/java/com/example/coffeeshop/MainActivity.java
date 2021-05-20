package com.example.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int priceCoffee = 5;
    int priceCream = 1;
    int priceChoco = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        // Find the user's name
//        EditText text = (EditText) findViewById(R.id.name_field);
//        String value = text.getText().toString();
//        Log.v("MainActivity", "Name: " + value);
//
//        // Figure out if the user wants whipped cream topping
//        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
//        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
//
//        // Figure out if the user wants chocolate topping
//        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
//        boolean hasChocolate = chocolateCheckBox.isChecked();
//
//        // Calculate the price
//        int price = calculatePrice(hasWhippedCream, hasChocolate);
//
//        // Display the order summary on the screen
//        String message = createOrderSummary(value, price, hasWhippedCream, hasChocolate);
//        displayMessage(message);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6, -122.3"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        intent.putExtra(Intent.EXTRA_STREAM, attachment);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

    }

    /**
     * Create summary of the order.
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order based on the current quantity.
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = priceCoffee;

        if(addWhippedCream){
            basePrice += priceCream;
        }

        if(addChocolate){
            basePrice += priceChoco;
        }
        return (quantity * basePrice);
    }

    public void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {

        if(quantity == 10){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 10 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity += 1;
        displayQuantity(this.quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {

        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity -= 1;
        displayQuantity(this.quantity); // expression
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffee) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffee);
    }
}