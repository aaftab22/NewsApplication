package com.example.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.viewHolder> {

    private ArrayList<categoryModel> categoryModels;
    private Context context;
    private categoryClickInterface categoryClickInterface;

    public categoryAdapter(ArrayList<categoryModel> categoryModels, Context context, categoryAdapter.categoryClickInterface categoryClickInterface) {
        this.categoryModels = categoryModels;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public categoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_category,parent,false);
        return new categoryAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.viewHolder holder, int position) {
        categoryModel categoryModel = categoryModels.get(position);
        holder.categoryTV.setText(categoryModel.getCategory());
        Picasso.get().load(categoryModel.getCategoryImageUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public interface categoryClickInterface{
        void onCategoryClick(int position);
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private ImageView categoryIV;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.CategoryText_ID);
            categoryIV = itemView.findViewById(R.id.CategoryImage_ID);

        }
    }
}
