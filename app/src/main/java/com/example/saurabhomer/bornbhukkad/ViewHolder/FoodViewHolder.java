package com.example.saurabhomer.bornbhukkad.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saurabhomer.bornbhukkad.Interface.ItemClickListerner;
import com.example.saurabhomer.bornbhukkad.R;

/**
 * Created by Anju on 28-03-2018.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView food_name;
    public ImageView food_image;

    private ItemClickListerner itemClickListerner;


    public void setFood_name(TextView food_name) {
        this.food_name = food_name;
    }

    public void setFood_image(ImageView food_image) {
        this.food_image = food_image;
    }

    public void setItemClickListerner(ItemClickListerner itemClickListerner) {
        this.itemClickListerner = itemClickListerner;


    }

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_name= (TextView) itemView.findViewById(R.id.food_name);
        food_image =(ImageView ) itemView.findViewById(R.id.food_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListerner.onClick(view,getAdapterPosition(),false);

    }
}
