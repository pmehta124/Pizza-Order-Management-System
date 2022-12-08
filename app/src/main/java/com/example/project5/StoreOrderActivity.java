package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This class is responsible for outlining the control logic for the
 * Store Activity.
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * StoreOrderActivity establishes the control for the store order view
 *
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
public class StoreOrderActivity extends AppCompatActivity {
    private StoreOrder storeOrder;
    private ArrayList<Order> cancelingThisOrder;
    private AlertDialog.Builder emptyStoreDialog;
    private AlertDialog.Builder selectionDialog;
    private ArrayList<Item> items = new ArrayList<>();
    private RecyclingAdapter adapter;
    private RecyclerView recyclerView;

    /**
     * Manages the view and display if the user presses the back button on the
     * Android Application.
     */
    @Override
    public void onBackPressed() {
        MainActivity.storeOrder = this.storeOrder;
        finish();
    }

    /**
     * Clears all the orders from the store order when button is clicked.
     *
     * @return a View.OnClickListener for the clear all button.
     */
    private View.OnClickListener cancelListenerAll() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (storeOrder.getOrders().isEmpty()) {
                    AlertDialog alert = emptyStoreDialog.create();
                    alert.show();
                    return;
                }
                storeOrder = new StoreOrder();
                Context currentContext = getApplicationContext();
                CharSequence text = getResources().getString(R.string.cancelAllOrderMessage);
                int timeLimit = Toast.LENGTH_SHORT;
                Toast successToast = Toast.makeText(currentContext, text, timeLimit);
                successToast.show();
                recyclerView.setAdapter(null);
            }
        };
    }

    /**
     * Executes when the Activity is created.
     *
     * @param savedInstanceState previously held data about this Activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        initializeValues();
        setUpCancelDialogs();
        initializeRecyclerView();
    }

    /**
     * Initializes the various instance variables to their respective values.
     */
    private void initializeValues() {
        cancelingThisOrder = new ArrayList<>();
        this.storeOrder = MainActivity.storeOrder;
        Button cancelOrders = (Button) (findViewById(R.id.cancelOrders));
        cancelOrders.setOnClickListener(cancelListener());

        Button cancelAllOrders = (Button) (findViewById(R.id.cancelAllOrders));
        cancelAllOrders.setOnClickListener(cancelListenerAll());
    }

    /**
     * Establishes a listener that organizes the orders that adds and removes
     * items from the list called cancelingThisOrder based on what was selected.
     *
     * @return an AdapterView.OnItemClickListener for the order list.
     */
    private AdapterView.OnItemClickListener selectItemsListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order itemChosen = (Order) (adapterView.getItemAtPosition(i));
                if (recyclerView.isSelected()) {
                    cancelingThisOrder.add(itemChosen);
                } else {
                    cancelingThisOrder.remove(itemChosen);
                }
            }
        };
    }

    /**
     * Creates an adapter for the list view of orders.
     *
     * @return ArrayAdapter that is used for list of Order items.
     */
    private ArrayAdapter<Order> listViewAdapter() {
        return new ArrayAdapter<Order>(StoreOrderActivity.this,
                android.R.layout.simple_list_item_single_choice,
                storeOrder.getOrders()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                view.setLayoutParams(layoutParams);
                return view;
            }
        };
    }

    /**
     * Manages the view and display if the user presses the back button on
     * the Store Activity.
     *
     * @param itemChosen The android.view.MenuItem that was selected
     * @return a true value if the button that the user pressed is the back
     * button. Else, return false.
     */
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem itemChosen) {
        if (itemChosen.getItemId() == android.R.id.home) {
            MainActivity.storeOrder = this.storeOrder;
            finish();
            return true;
        }
        return super.onOptionsItemSelected(itemChosen);
    }

    /**
     * Display respective errors if an order is cancelled in correctly.
     */
    public void setUpCancelDialogs() {
        emptyStoreDialog = new AlertDialog.Builder(this);
        emptyStoreDialog.setTitle(R.string.cancelError);
        emptyStoreDialog.setMessage(R.string.cancelEmptyStoreOrders);
        emptyStoreDialog.setIcon(R.drawable.error);
        emptyStoreDialog.setPositiveButton(R.string.OKButton, null);

        selectionDialog = new AlertDialog.Builder(this);
        selectionDialog.setTitle(R.string.cancelError);
        selectionDialog.setMessage(R.string.cancelNoSelection);
        selectionDialog.setIcon(R.drawable.error);
        selectionDialog.setPositiveButton(R.string.OKButton, null);
    }

    /**
     * Initializes the RecyclerView
     */
    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclingAdapter(this.storeOrder.getOrders()); //create the adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Establishes a listener for when the Cancel Order button is clicked. First
     * checks if the total orders and the selected orders are not empty, and then
     * removed the orders that need to be removed.
     *
     * @return a View.OnClickListener for the cancel button.
     */
    private View.OnClickListener cancelListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (storeOrder.getOrders().isEmpty()) {
                    AlertDialog alert = emptyStoreDialog.create();
                    alert.show();
                    return;
                }
                if (adapter.getSelectedElem() == null) {
                    AlertDialog alert = selectionDialog.create();
                    alert.show();
                    return;
                } else {
                    storeOrder.getOrders().remove(adapter.getSelectedElem());
                    RecyclingAdapter adapter = new RecyclingAdapter(storeOrder.getOrders());
                    recyclerView.setAdapter(adapter);
                    Context currentContext = getApplicationContext();
                    CharSequence text = getResources().getString(R.string.cancelOrderMessage);
                    int timeLimit = Toast.LENGTH_SHORT;
                    Toast successToast = Toast.makeText(currentContext, text, timeLimit);
                    successToast.show();
                    cancelingThisOrder.clear();
                    adapter.resetSelectedPos();
                }
            }
        };
    }
}
