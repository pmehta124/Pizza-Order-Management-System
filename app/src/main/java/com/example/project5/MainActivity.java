package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * MainActivity that sets up the main view of the application
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public class MainActivity extends AppCompatActivity {

    ImageButton chicagoPizza;
    ImageButton nyPizza;
    ImageButton StoreOrder;
    ImageButton Order;
    public static StoreOrder storeOrder = new StoreOrder();
    public static Order order = new Order();
    public static int updateCounter = 0;

    /**
     * sets up the view of the application
     * @param savedInstanceState a Bundle that needs to be set to the super class's onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chicagoPizza = findViewById(R.id.chicagoPizza);
        nyPizza = findViewById(R.id.nyPizza);
        StoreOrder = findViewById(R.id.storeOrder);
        Order = findViewById(R.id.order);

        chicagoPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChicagoStyleActivity.class);
                startActivity(intent);
            }
        });

        nyPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NYStyleActivity.class);
                startActivity(intent);
            }
        });

        StoreOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StoreOrderActivity.class);
                startActivity(intent);
            }
        });

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
                startActivity(intent);
            }
        });

    }
}