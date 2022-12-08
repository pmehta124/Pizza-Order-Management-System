package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class outlining the controls for the
 * current Order.
 *
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */

public class CurrentOrderActivity extends AppCompatActivity {
    private Order order;
    private StoreOrder storeOrder;
    private ListView pizzaList;
    private EditText subtotalAmount;
    private EditText taxAmount;
    private EditText totalAmount;
    private TextView ordernum;
    private AlertDialog.Builder emptyOrderDialog;
    private AlertDialog.Builder noSelectionDialog;
    private AlertDialog.Builder emptySubmitDialog;
    private ArrayList<String> needToRemove;


    /**
     * Executes when the Activity is created.
     *
     * @param savedInstanceState previously held data about this Activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        initializeValues();
        setUpRemoveDialogs();
        setUpSubmitDialogs();
        updateValues();
    }

    /**
     * Initializes dialog builders to display errors with removing
     * items from the order.
     */
    public void setUpRemoveDialogs() {
        emptyOrderDialog = new AlertDialog.Builder(this);
        emptyOrderDialog.setTitle(R.string.RemoveError);
        emptyOrderDialog.setMessage(R.string.removeEmptyOrder);
        emptyOrderDialog.setIcon(R.drawable.error);
        emptyOrderDialog.setPositiveButton(R.string.OKButton, null);

        noSelectionDialog = new AlertDialog.Builder(this);
        noSelectionDialog.setTitle(R.string.RemoveError);
        noSelectionDialog.setMessage(R.string.removeFromNoSelection);
        noSelectionDialog.setIcon(R.drawable.error);
        noSelectionDialog.setPositiveButton(R.string.OKButton, null);
    }

    /**
     * Updates the subtotal, tax, and total displays based on the
     * items that are in the user's current order.
     */
    private void updateValues() {
        if (order.getItems().isEmpty()) {
            subtotalAmount.setText(getResources().getString(R.string.zeroDollar));
            taxAmount.setText(getResources().getString(R.string.zeroDollar));
            totalAmount.setText(getResources().getString(R.string.zeroDollar));
        }
        subtotalAmount.setText(formatDouble(order.getCostBeforeTax()));
        taxAmount.setText(formatDouble(order.getSalesTax()));
        totalAmount.setText(formatDouble(order.getCostAfterTax()));
    }

    /**
     * Formats input value to a standard price format ($A.BC).
     *
     * @param input value to be formatted.
     * @return a CharSequence of the form "$A.BC" representing the
     * input value.
     */
    private CharSequence formatDouble(double input) {
        if (input < 0) {
            input = 0;
        }
        DecimalFormat twoDecimalPlaces = new DecimalFormat(getResources().getString(R.string.zeroDollar));
        return twoDecimalPlaces.format(input);
    }

    /**
     * Handles the control logic for when the user presses the back
     * button on the Android Menu.
     */
    @Override
    public void onBackPressed() {
        MainActivity.order = this.order;
        MainActivity.storeOrder = this.storeOrder;
        finish();
    }

    /**
     * Creates an adapter for the list view of pizza items.
     *
     * @return ArrayAdapter for list of MenuItem objects.
     */
    private ArrayAdapter<String> listViewAdapter() {
        return new ArrayAdapter<String>(CurrentOrderActivity.this,
                android.R.layout.simple_list_item_single_choice, order.getPizzaToString()) {
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
     * Initializes instance variables, sets display of items in the
     * current order.
     */
    private void initializeValues() {
        subtotalAmount = (EditText) (findViewById(R.id.subtotalAmount));
        taxAmount = (EditText) (findViewById(R.id.taxAmount));
        totalAmount = (EditText) (findViewById(R.id.totalAmount));

        needToRemove = new ArrayList<>();
        this.order = MainActivity.order;
        this.storeOrder = MainActivity.storeOrder;


        pizzaList = (ListView) (findViewById(R.id.basketDisplayList));
        pizzaList.setAdapter(listViewAdapter());
        pizzaList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        pizzaList.setOnItemClickListener(selectItemsListener());
        Button removeFromBasket = (Button) (findViewById(R.id.removeFromBasket));
        removeFromBasket.setOnClickListener(removeListener());
        Button submitOrder = (Button) (findViewById(R.id.submitOrder));
        submitOrder.setOnClickListener(submitListener());
        Button clearFromBasket = (Button) (findViewById(R.id.clearButton));
        clearFromBasket.setOnClickListener(clearListener());

        ordernum = (TextView) (findViewById(R.id.textView12));
        ordernum.setText(Integer.toString(order.getOrderId()));


        updateValues();
    }

    /**
     * Initializes dialog builders to display errors with submitting
     * an order.
     */
    public void setUpSubmitDialogs() {
        emptySubmitDialog = new AlertDialog.Builder(this);
        emptySubmitDialog.setTitle(R.string.submitError);
        emptySubmitDialog.setMessage(R.string.submitEmptyOrder);
        emptySubmitDialog.setIcon(R.drawable.error);
        emptySubmitDialog.setPositiveButton(R.string.OKButton, null);
    }

    /**
     * Creates a listener for the list of pizza items which adds and
     * removes items from the toBeRemoved list based on their
     * selection status.
     *
     * @return an AdapterView.OnItemClickListener for the order.
     */
    private AdapterView.OnItemClickListener selectItemsListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemChosen = (String) adapterView.getItemAtPosition(i);
                if (pizzaList.isItemChecked(i)) {
                    needToRemove.add(itemChosen);
                } else {
                    needToRemove.remove(itemChosen);
                }
            }

        };

    }

    /**
     * Creates a listener for the Remove From order button.
     *
     * @return a View.OnClickListener for the remove button.
     */
    private View.OnClickListener clearListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (order.getItems().isEmpty()) {
                    AlertDialog alert = emptyOrderDialog.create();
                    alert.show();
                    return;
                }
                order.getPizzaToString().clear();
                order.getItems().clear();
                order.setCostBeforeTax(0);
                MainActivity.storeOrder = storeOrder;
                MainActivity.order = order;
                MainActivity.updateCounter--;
                pizzaList.setAdapter(null);
                ordernum.setText(Integer.toString(order.getOrderId()));
                CharSequence text = getResources().getString(R.string.clearItems);
                int timeLimit = Toast.LENGTH_SHORT;
                Toast successToast = Toast.makeText(CurrentOrderActivity.this, text, timeLimit);
                successToast.show();

                updateValues();
            }
        };
    }

    /**
     * Creates a listener for the Remove From order button.
     *
     * @return a View.OnClickListener for the remove button.
     */
    private View.OnClickListener removeListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (order.getItems().isEmpty()) {
                    AlertDialog alert = emptyOrderDialog.create();
                    alert.show();
                    return;
                }

                if (needToRemove.isEmpty()) {
                    AlertDialog alert = noSelectionDialog.create();
                    alert.show();
                    return;
                }
                for (int i = 0; i < needToRemove.size(); i++) {
                    int index = order.getPizzaToString().indexOf(needToRemove.get(i));
                    Pizza toRemove = order.getItems().get(index);
                    order.remove(toRemove);
                    order.getPizzaToString().remove(index);
                }


                pizzaList.setAdapter(listViewAdapter());
                CharSequence text = getResources().getString(R.string.removeItemMessage);
                int timeLimit = Toast.LENGTH_SHORT;
                Toast successToast = Toast.makeText(CurrentOrderActivity.this, text, timeLimit);
                successToast.show();
                needToRemove.clear();
                updateValues();
            }
        };
    }

    /**
     * Creates a listener for the Submit Order button.
     *
     * @return a View.OnClickListener for the submit button.
     */
    private View.OnClickListener submitListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (order.getItems().isEmpty()) {
                    AlertDialog alert = emptySubmitDialog.create();
                    alert.show();
                    return;
                }
                storeOrder.add(order);
                pizzaList.setAdapter(null);
                MainActivity.updateCounter++;
                ordernum.setText(Integer.toString(MainActivity.updateCounter));
                order = new Order();
                MainActivity.order = order;
                MainActivity.storeOrder = storeOrder;
                order.setOrderID(MainActivity.updateCounter);
                CharSequence text = getResources().getString(R.string.submitOrderMessage);
                int timeLimit = Toast.LENGTH_SHORT;
                Toast successToast = Toast.makeText(CurrentOrderActivity.this, text, timeLimit);
                successToast.show();


                updateValues();

            }
        };
    }

}
