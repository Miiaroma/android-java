package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private ArrayList<Item> myDataset;
    private ArrayList<Item> myDatasetFiltered;


    // constructor
    public RecyclerAdapter(ArrayList<Item> myDataset) {

        this.myDataset = myDataset;
        this.myDatasetFiltered = myDataset;
    }

    //Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item items = myDataset.get(position);
            holder.name.setText(items.getName());
            holder.businessId.setText("Company Id:" +items.getBusinessId());
            holder.companyForm.setText("Company Form: "+items.getCompanyForm());
            holder.registrationDate.setText("Registration Date: "+items.getRegistrationDate());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    public Filter getFilter() {
       return new Filter() {
           @Override
           protected FilterResults performFiltering(CharSequence charSequence) {
               String charString = charSequence.toString();
               if (charString.isEmpty()) {
                   myDatasetFiltered.addAll(myDataset);
               }
                else {
                   ArrayList<Item> myDataset = new ArrayList<>();
                   for (Item row : myDataset) {
                       if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                           myDatasetFiltered.add(row);
                       }
                   }
                   myDatasetFiltered = myDataset;
               }

               FilterResults filterResults = new FilterResults();
               filterResults.values = myDatasetFiltered;
               return filterResults;
           }

           @Override
           protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
               myDatasetFiltered = (ArrayList<Item>) filterResults.values;
               notifyDataSetChanged();
           }
       };
   }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item here
        public TextView name, businessId, registrationDate, companyForm;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.item_name);
            businessId = (TextView) v.findViewById(R.id.item_businessId);
            registrationDate = (TextView) v.findViewById(R.id.item_registrationDate);
            companyForm = (TextView) v.findViewById(R.id.item_companyForm);
            businessId.setVisibility(View.GONE);
            registrationDate.setVisibility(View.GONE);
            companyForm.setVisibility(View.GONE);
            name.setOnClickListener(this);
            v.setOnClickListener(this);
        }

        //Called when a view has been clicked
        @Override
        public void onClick(View v) {
             if (v.getId() == R.id.item_name) {
                    businessId.setVisibility(View.VISIBLE);
                    registrationDate.setVisibility(View.VISIBLE);
                    companyForm.setVisibility(View.VISIBLE);
             }
        }
    }
}


