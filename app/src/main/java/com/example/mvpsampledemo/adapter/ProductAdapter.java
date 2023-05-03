package com.example.mvpsampledemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvpsampledemo.R;
import com.example.mvpsampledemo.roomdatabase.entity.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final ArrayList<Product> productList;
    Context context;
    public ProductAdapter(ArrayList<Product> productList, Context context, DeleteClickListener mListener) {
        this.productList = productList;
        this.context = context;
        this.mListener = mListener;
    }

    private final DeleteClickListener mListener;

    public interface DeleteClickListener {
        void onDeleteButton(int position);
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(productList.get(position).name);
        if (productList.size() < 2){ holder.view.setVisibility(View.GONE); }
        holder.tvName.setText(productList.get(position).name);
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatTextView tvName;
        View view;
        AppCompatImageView ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            view = itemView.findViewById(R.id.view);
            ivDelete.setOnClickListener(this);
            itemView.setOnLongClickListener(v->{
                mListener.onDeleteButton(getAdapterPosition());
                return true;
            });
        }
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.ivDelete) {
                mListener.onDeleteButton(getAdapterPosition());
            }
        }
    }

}
