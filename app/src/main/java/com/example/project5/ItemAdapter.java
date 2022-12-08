package com.example.project5;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * RecyclingAdapter is the adapater for the recyclerView that helps communicate information between the views.
 * @author Srinidhi Ayalasomayajula, Palak Mehta
 */
class RecyclingAdapter extends RecyclerView.Adapter<RecyclingAdapter.RecyclingHolder> {
    private ArrayList<Order> orderList;
    private static int selectedPosition = -1;

    /**
     * Set the arraylist of orders to the arraylist provided
     * @param items items to be displayed in the recyclerView
     */
    public RecyclingAdapter( ArrayList<Order> items) {
        this.orderList = items;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     *
     * @param parent parent of ViewGroup type
     * @param viewType viewType, an int representing the type of view
     * @return RecyclingHolder to be set
     */
    @NonNull
    @Override
    public RecyclingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new RecyclingHolder(view);
    }

    /**
     * Find the selected items' position
     * @return the Order that was selected
     */
    public Order getSelectedElem(){
        if (selectedPosition ==-1){
            return null;
        }
        else {
            return orderList.get(selectedPosition);
        }
    }

    /**
     * Set the position back to -1 after finding the order
     */
    public void resetSelectedPos() {
        selectedPosition = -1;}

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     *
     * @param holder   the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclingHolder holder, int position) {
        holder.pizzaString.setText(orderList.get(position).toString());
        if(position== selectedPosition)
        {
            holder.pizzaString.setText("SELECTED: " + orderList.get(position).toString());
            holder.pizzaString.setTextColor(Color.BLUE);
        }
        else
        {
            holder.pizzaString.setText( orderList.get(position).toString());
            holder.pizzaString.setTextColor(Color.WHITE);
        }
    }

    /**
     * Get the number of items in the ArrayList.
     *
     * @return number of items in the list.
     */
    @Override
    public int getItemCount() {
        return orderList.size();
    }

    /**
     * Get the views from the row layout file, like the onCreate() method.
     */
    public class RecyclingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView pizzaString;
        private CardView cardView;
        private ConstraintLayout parentLayout;

        public RecyclingHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.rowLayout);
            cardView = itemView.findViewById(R.id.cardView);
            pizzaString = itemView.findViewById(R.id.pizzaString);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }

            notifyItemChanged(selectedPosition);
            selectedPosition = getAdapterPosition();
            notifyItemChanged(selectedPosition);
        }
    }
}

