package com.example.jay.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 2;
    boolean chocolateAdded=false,creamAdded=false;

    public void composeEmail(String name, String message) {
        String subject = "Coffee Order Details For "+name;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_BCC, "jvj.co.in@gmail.com");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void submitOrder(View view) {

        if(isNameGiven()==false)
            return;

        Toast.makeText(MainActivity.this, "Thank You!", Toast.LENGTH_SHORT).show();
        int price = quantity*5;
        if(creamAdded) price+=quantity*1;
        if(chocolateAdded) price+=quantity*2;

        String priceMessage = "";
        String name =((EditText)findViewById(R.id.name_input_view)).getText().toString();
        priceMessage = "Name: ";
        priceMessage += name + "\n";

        if (creamAdded)
            priceMessage += "Add Whipped Cream \n";

        if (chocolateAdded)
            priceMessage += "Add Chocolate \n";

        priceMessage += "Quantity: ";
        priceMessage += quantity + "\n";
        priceMessage += "Total: $";
        priceMessage += price + "\n";

        displayMessage(priceMessage);

        composeEmail(name,priceMessage);
    }

    public boolean isNameGiven(){
        EditText name=(EditText)findViewById(R.id.name_input_view);
        if(name.getText().toString().matches("")){
            Toast.makeText(this,"You didn't Entered Name",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void whippedCreamClickEvent(View view){
        if(((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked()) {
            Toast.makeText(this, "$1 added for each coffee!", Toast.LENGTH_SHORT).show();
            creamAdded=true;
        }
        else {
            Toast.makeText(this, "$1 removed for each coffee!", Toast.LENGTH_SHORT).show();
            creamAdded=false;
        }

        int price = quantity*5;
        if(creamAdded) price+=quantity*1;
        if(chocolateAdded) price+=quantity*2;
        displayMessage("$ "+price);
    }

    public void chocolateClickEvent(View view){
        if(((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked()) {
            Toast.makeText(this, "$2 added for each coffee!", Toast.LENGTH_SHORT).show();
            chocolateAdded=true;
        }
        else {
            Toast.makeText(this, "$2 removed for each coffee!", Toast.LENGTH_SHORT).show();
            chocolateAdded=false;
        }

        int price = quantity*5;
        if(creamAdded) price+=quantity*1;
        if(chocolateAdded) price+=quantity*2;
        displayMessage("$ "+price);
    }

    public void increment(View view) {
//        Toast.makeText(getApplicationContext(),"+1",Toast.LENGTH_SHORT).show();
        quantity++;
        if(quantity%10==0)
            Toast.makeText(this,"Kitna Lega Be! :P",Toast.LENGTH_SHORT).show();

        displayQuantity();

        int price = quantity*5;
        if(creamAdded) price+=quantity*1;
        if(chocolateAdded) price+=quantity*2;
        displayMessage("$ "+price);
    }

    public void decrement(View view) {
//        Toast.makeText(MainActivity.this,"-1",Toast.LENGTH_SHORT).show();
        quantity--;
        if (quantity <= 0) {
            quantity = 1;
            Toast.makeText(this,"Value Can\'t be <1",Toast.LENGTH_SHORT).show();
        }
        displayQuantity();

        int price = quantity*5;
        if(creamAdded) price+=quantity*1;
        if(chocolateAdded) price+=quantity*2;
        displayMessage("$ "+price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    private void displayMessage(String message) {
        TextView orderTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderTextView.setText(message);
    }

    public void reset(View view) {
        quantity=2;
//        Reset Name
        EditText name=(EditText)findViewById(R.id.name_input_view);
        name.setText("");

//        Reset Check Boxes
        ((CheckBox) findViewById(R.id.whipped_cream_checkbox)).setChecked(false);
        ((CheckBox) findViewById(R.id.chocolate_checkbox)).setChecked(false);

//        price reset and qnty
        displayMessage("$ 10");
        quantity=2; chocolateAdded=false; creamAdded=false;
        displayQuantity();

        Toast.makeText(MainActivity.this,"Resetted!",Toast.LENGTH_SHORT).show();
    }
}