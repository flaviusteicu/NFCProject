package com.example.flavius.nfcproject;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
  /*  private Context mContext;
    private CardView mCardView;*/


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView Accesfor, Username, Password;

        public ViewHolder(View view) {
            super(view);
            Accesfor = (TextView) view.findViewById(R.id.textViewCardAccessFieldID);
            Username = (TextView) view.findViewById(R.id.textViewCardUsernameFieldID);
            Password = (TextView) view.findViewById(R.id.textViewCardPasswordFieldID);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)

    List<CardViewData> cardsData;
    MyAdapter(List<CardViewData> cardsData){
        this.cardsData = cardsData;
    }

   /* public MyAdapter(Context mContext, CardView mCardView) {
        this.mContext = mContext;
        this.mCardView = mCardView;
    }*/

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout,parent,false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder myViewHolder = new ViewHolder(itemView);
        return myViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.Accesfor.setText(cardsData.get(i).accessfor);
        holder.Username.setText(cardsData.get(i).username);
        holder.Password.setText(cardsData.get(i).password);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cardsData.size();
    }
}