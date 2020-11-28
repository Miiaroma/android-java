package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<Item> myDataset;
    public TextView textView;
    private AdapterView.OnItemClickListener mOnItemClickListener;



    // constructor
    public RecyclerAdapter(ArrayList<Item> myDataset) {
        this.context = context;
        this.myDataset = myDataset;
    }

    /*public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(TextView view) {
            super(view);
            textView = view;
        }
    }*/

    //Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView,this);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        {Item items = myDataset.get(position);
            holder.name.setText(items.getName());
            holder.businessId.setText("" + items.getName());
            holder.registrationDate.setText("" + items.getBusinessId()+ items.getName());
            holder.companyForm.setText("" + items.getRegistrationDate()+ items.getBusinessId()+ items.getName());
        }
    }
    // Replace the contents of a view (invoked by the layout manager)
   /* @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(myArray[position]);

    }*/

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    public Filter getFilter() {
       return new Filter() {
           FilterResults filterResults;
           @Override
           protected FilterResults performFiltering(CharSequence charSequence) {

               return filterResults;
           }
           @Override
           protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
           }
       };
   }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item here
        public TextView name, businessId, registrationDate, companyForm;
        public RecyclerAdapter recyclerAdapter;

        public ViewHolder(View v, RecyclerAdapter recyclerAdapter) {
            super(v);
            this.recyclerAdapter = recyclerAdapter;
            name = (TextView) itemView.findViewById(R.id.item_name);
            businessId = (TextView) itemView.findViewById(R.id.item_businessId);
            registrationDate = (TextView) itemView.findViewById(R.id.item_registrationDate);
            companyForm = (TextView) itemView.findViewById(R.id.item_companyForm);
            itemView.setOnClickListener(this);
        }

        //Called when a view has been clicked
        @Override
        public void onClick(View v) {
            //RecyclerAdapter.onItemHolderClick(this);
            //listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
        }
    }
}

