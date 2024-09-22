package com.devabhay.kitchenrecepie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devabhay.kitchenrecepie.R;
import com.devabhay.kitchenrecepie.models.ExtendedIngredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredentsViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public IngredentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredentsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull IngredentsViewHolder holder, int position) {
        holder.textView_ingredients_name.setText(list.get(position).name);
        holder.textView_ingredients_name.setSelected(true);
        holder.textView_ingredients_quantity.setText(list.get(position).original);
        holder.textView_ingredients_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageView_ingredients_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredentsViewHolder extends RecyclerView.ViewHolder {

    TextView textView_ingredients_name, textView_ingredients_quantity;
    ImageView imageView_ingredients_image;
    public IngredentsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_ingredients_image = itemView.findViewById(R.id.imageView_ingredients_image);
        textView_ingredients_name = itemView.findViewById(R.id.textView_ingredients_name);
        textView_ingredients_quantity = itemView.findViewById(R.id.textView_ingredients_quantity);
    }
}
