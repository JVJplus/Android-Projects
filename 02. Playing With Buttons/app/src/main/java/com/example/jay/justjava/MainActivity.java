package com.example.jay.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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
	int count=2;
	public void submitOrder(View view) {
	    int price= 5*count;
		String priceMessage = "Total: $"+price;
		priceMessage += "\nThank You!";
		displayMessage(priceMessage);
	}
	
	public void increment(View view) {
		count++;
		display(count);
	}
	
	public void decrement(View view) {
		count--;
		if(count<0)
			count=0;
		display(count);
	}
	
	/**
	 * This method displays the given quantity value on the screen.
	 */
	private void display(int number) {
		TextView quantityTextView = (TextView) findViewById(
				R.id.quantity_text_view);
		quantityTextView.setText("" + count);
	}
	
	private void displayPrice(int number){
		TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
		priceTextView.setText(java.text.NumberFormat.getCurrencyInstance().format(number));
	}

	private void displayMessage(String message){
		TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
		priceTextView.setText(message);
	}
}