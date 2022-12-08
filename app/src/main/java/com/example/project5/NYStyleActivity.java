package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NY Style Activity class that controls the GUI for the NY Style
 * Pizza Window in the application
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */

public class NYStyleActivity extends AppCompatActivity {

    private StoreOrder StoreOrder;
    private Order Order;
    private ListView toppingsAvailable;
    private ListView toppingsSelected;
    private PizzaFactory pizzaFactory = new NYStylePizzaFactory();
    private DecimalFormat df = new DecimalFormat("#.##");
    private ImageView image;
    private RadioGroup sizes;
    private RadioButton smallSize;
    private Topping[] toppings = {Topping.GREENPEPPER, Topping.ONION, Topping.MUSHROOM, Topping.PINEAPPLE,
            Topping.BLACKOLIVES, Topping.SAUSAGE, Topping.BBQCHICKEN, Topping.PROVOLONE,
            Topping.CHEDDAR, Topping.BEEF, Topping.HAM, Topping.PEPPERONI,  Topping.SPINACH};
    private Button removeButton;
    private Button addButton;
    private Button addOrder;
    private Spinner spinner;
    private TextView price;
    private TextView crust;
    private String [] names = {"Build Your Own", "Deluxe", "Meatzza", "BBQ Chicken"};
    private List<String> typesOfPizza = Arrays.asList(names);
    private List<Topping> availableToppings  = new ArrayList<>(Arrays.asList(toppings));
    private List<Topping> selectedToppings = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<Topping> toppingAvailableAdapter;
    private ArrayAdapter<Topping> toppingSelectedAdapter;
    private static Pizza pizza;
    private static Topping selected;
    private static Topping selectedToRemove;
    private static Size selectedSize = Size.SMALL;

    /**
     * Calls the proper functions to initialize the window with all the correct fields and
     * sets the build your own pizza as a default
     * @param savedInstanceState a Bundle that needs to be set to the super class's onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny_style);
        initializeListViews();
        initializeValues();
        initializeSpinner();
        changeSize();
        changeValues();
        addTopping();
        removeTopping();
        addToOrder();
    }

    /**
     * Sets up the screen of the NY Style Pizza
     */
    private void initializeValues(){
        this.Order = MainActivity.order;
        this.StoreOrder = MainActivity.storeOrder;
        image = (ImageView) findViewById(R.id.nyImage);

        price = (TextView) (findViewById(R.id.price2NY));
        crust = (TextView) (findViewById(R.id.crust2NY));

        smallSize = (RadioButton) (findViewById(R.id.radioButtonSmallNY)) ;
        smallSize.setChecked(true);

        pizza = pizzaFactory.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        price.setText(""+df.format(pizza.price()));
        crust.setText(pizza.getCrust().toString());
    }

    /**
     * Initializes the Spinner to house the four styles of pizzas
     */
    private void initializeSpinner(){
        spinner = (Spinner) findViewById(R.id.spinner3NY);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typesOfPizza);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * Initializes the options for the toppings in the build your own pizza
     */
    private void initializeListViews(){
        toppingsAvailable = (ListView) findViewById(R.id.listView2NY);
        toppingsAvailable.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        toppingsAvailable.setSelector(new ColorDrawable(Color.BLUE));
        availableToppings  = new ArrayList<>(Arrays.asList(toppings));
        toppingAvailableAdapter = new ArrayAdapter<Topping>(this, android.R.layout.simple_list_item_1,  availableToppings);
        toppingsAvailable.setAdapter(toppingAvailableAdapter);

        toppingsSelected = (ListView) findViewById(R.id.listViewNY);
        toppingsSelected.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        toppingsSelected.setSelector(new ColorDrawable(Color.BLUE));
        selectedToppings = new ArrayList<>();
        toppingSelectedAdapter = new ArrayAdapter<Topping>(this, android.R.layout.simple_list_item_1,  selectedToppings);
        toppingsSelected.setAdapter(toppingSelectedAdapter);
    }

    /**
     * Controls the size radioGroup and updates the price based on the size selected
     */
    private void changeSize(){
        sizes = (RadioGroup) findViewById(R.id.radioGroupNY);
        sizes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int selectedRadio)
            {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(selectedRadio);
                boolean checkedButton = checkedRadioButton.isChecked();
                if (checkedButton)
                {
                    selectedSize = Size.valueOf(checkedRadioButton.getText().toString().toUpperCase());
                    pizza.setSize(selectedSize);
                    price.setText(""+df.format(pizza.price()));
                }
            }
        });
    }

    /**
     * Listener for the spinner. If a different pizza is selected, then it set the new pizza's fields correctly
     */
    private void changeValues(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                addButton.setEnabled(false);
                removeButton.setEnabled(false);
                String selectedPizzaType = parentView.getSelectedItem().toString();
                if (selectedPizzaType.equals("Deluxe")){
                    pizza = pizzaFactory.createDeluxe();
                    image.setImageResource(R.drawable.nydeluxe);
                }
                else if (selectedPizzaType.equals("Meatzza")){
                    pizza = pizzaFactory.createMeatzza();
                    image.setImageResource(R.drawable.nymeatzza);
                }
                else if (selectedPizzaType.equals("BBQ Chicken")){
                    pizza = pizzaFactory.createBBQChicken();
                    image.setImageResource(R.drawable.nybbq);
                }
                else {
                    pizza = pizzaFactory.createBuildYourOwn();
                    image.setImageResource(R.drawable.nybyo);
                    addButton.setEnabled(true);
                    removeButton.setEnabled(true);
                }
                selectedToppings.clear();
                if (!(selectedPizzaType.equals("Build Your Own"))) {
                    selectedToppings.addAll(pizza.getToppings());
                }
                toppingSelectedAdapter.notifyDataSetChanged();
                pizza.setSize(selectedSize);
                price.setText(""+df.format(pizza.price()));
                crust.setText(pizza.getCrust().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    /**
     * Establishes functionality for the add topping button and checks if the min and max requirements are met
     */
    private void addTopping() {
        addButton = (Button) findViewById(R.id.addButtonNY);
        toppingsAvailable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                selected = (Topping) adapter.getItemAtPosition(position);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected == null) {
                    CharSequence text = getResources().getString(R.string.zeroToppingsSelected);
                    int timeLimit = Toast.LENGTH_LONG;
                    Toast successToast = Toast.makeText(NYStyleActivity.this, text, timeLimit);
                    successToast.show();
                } else if (selectedToppings.size() == 7) {
                    CharSequence text = getResources().getString(R.string.noMoreToppings);
                    int timeLimit = Toast.LENGTH_LONG;
                    Toast successToast = Toast.makeText(NYStyleActivity.this, text, timeLimit);
                    successToast.show();
                } else {

                    selectedToppings.add(selected);
                    toppingSelectedAdapter.notifyDataSetChanged();
                    availableToppings.remove(selected);
                    toppingAvailableAdapter.notifyDataSetChanged();
                    pizza.add(selected);
                    selected = null;
                    price.setText(""+df.format(pizza.price()));
                }
            }
        });
    }

    /**
     * Establishes functionality for the remove topping button and checks if the min and max requirements are met
     */
    private void removeTopping() {
        removeButton = (Button) findViewById(R.id.removeButtonNY);
        toppingsSelected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                selectedToRemove = (Topping) adapter.getItemAtPosition(position);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedToRemove == null) {
                    CharSequence text = getResources().getString(R.string.zeroSelected);
                    int timeLimit = Toast.LENGTH_LONG;
                    Toast successToast = Toast.makeText(NYStyleActivity.this, text, timeLimit);
                    successToast.show();
                }
                else {
                    availableToppings.add(selectedToRemove);
                    toppingAvailableAdapter.notifyDataSetChanged();
                    selectedToppings.remove(selectedToRemove);
                    toppingSelectedAdapter.notifyDataSetChanged();
                    pizza.remove(selectedToRemove);
                    selectedToRemove= null;
                    price.setText(""+df.format(pizza.price()));
                }
            }
        });
    }


    /**
     * Adds the pizza to the current order window and resets the window back to the starting window format
     */
    private void addToOrder(){
        addOrder = (Button) findViewById(R.id.addOrderButtonNY);
        addOrder.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (selectedToppings.size() == 0) {
                    CharSequence text = getResources().getString(R.string.zeroToppingsForPizza);
                    int timeLimit = Toast.LENGTH_LONG;
                    Toast successToast = Toast.makeText(NYStyleActivity.this, text, timeLimit);
                    successToast.show();
                } else {
                    Order.add(pizza);
                    Order.addToStringArray(pizza, "NY Style");

                    CharSequence text = getResources().getString(R.string.addPizzaToOrder);
                    int timeLimit = Toast.LENGTH_LONG;
                    Toast successToast = Toast.makeText(NYStyleActivity.this, text, timeLimit);
                    successToast.show();
                    initializeSpinner();
                    initializeValues();
                    initializeListViews();
                }
            }
        });
    }

}