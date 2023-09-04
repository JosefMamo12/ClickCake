package com.example.clickcake.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickcake.R;
import com.example.clickcake.database.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierHolder> {
    private List<Supplier> suppliers = new ArrayList<>();

    @NonNull
    @Override
    public SupplierHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.supplier_item, parent, false);
        return new SupplierHolder(itemView);
    }

    /**
     * Where we binding between the data that's come
     * from the api into the layout.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull SupplierHolder holder, int position) {
        Supplier currSupplier = suppliers.get(position);
        holder.textViewSupplierName.setText(currSupplier.getSupplierName());
        holder.textViewSupplierDescription.setText(currSupplier.getDescription());
        holder.textViewSupplierLocation.setText(currSupplier.getLocation());

    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }
    public void setSuppliers(List<Supplier> suppliers){
        this.suppliers = suppliers;
        notifyDataSetChanged();
    }

    class SupplierHolder extends RecyclerView.ViewHolder {
        private TextView textViewSupplierName;
        private TextView textViewSupplierDescription;
        private TextView textViewSupplierLocation;

        public SupplierHolder(@NonNull View itemView) {
            super(itemView);
            textViewSupplierName = itemView.findViewById(R.id.text_view_supplier_name);
            textViewSupplierDescription = itemView.findViewById(R.id.text_view_supplier_description);
            textViewSupplierLocation = itemView.findViewById(R.id.text_view_supplier_location);
        }
    }
}
