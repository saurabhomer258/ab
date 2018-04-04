package com.example.saurabhomer.bornbhukkad.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saurabhomer.bornbhukkad.Interface.ItemClickListerner;
import com.example.saurabhomer.bornbhukkad.R;

/**
 * Created by saurabh omer on 28-Mar-18.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtMenuName;
    public ImageView imageView;
    private ItemClickListerner itemClickListerner;
    public MenuViewHolder(View itemView) {
        super(itemView);
        txtMenuName = (TextView) itemView.findViewById(R.id.menu_name);
        imageView =(ImageView ) itemView.findViewById(R.id.menu_image);
        itemView.setOnClickListener(this);
    }



    public void setItemClickListerner(ItemClickListerner itemClickListerner) {
        this.itemClickListerner = itemClickListerner;
    }
    @Override
    public void onClick(View view) {
        itemClickListerner.onClick(view,getAdapterPosition(),false);
    }
}
