package com.example.HeadlineHaven.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darksunTechnologies.HeadlineHaven.R;
import com.example.HeadlineHaven.Models.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    private final ArrayList<Category> Categories;
    private final categoryClickInterface categoryClickInterface;

    public CategoryAdapter(ArrayList<Category> Categories, CategoryAdapter.categoryClickInterface categoryClickInterface) {
        this.Categories = Categories;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_category,parent,false);
        return new CategoryAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        Category Category = Categories.get(position);
        holder.categoryTV.setText(Category.getCategory());
        Picasso.get().load(Category.getCategoryImageUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Categories.size();
    }

    public interface categoryClickInterface{
        void onCategoryClick(int position);
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private final TextView categoryTV;
        private final ImageView categoryIV;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.CategoryText_ID);
            categoryIV = itemView.findViewById(R.id.CategoryImage_ID);

        }
    }
}
