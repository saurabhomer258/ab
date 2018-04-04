package com.example.saurabhomer.bornbhukkad.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.saurabhomer.bornbhukkad.Interface.ItemClickListerner;
import com.example.saurabhomer.bornbhukkad.R;

import org.w3c.dom.Text;

/**
 * Created by Anju on 29-03-2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtOrderId , txtOrderStatus,txtOrderPhone,txtOrderAddress;

    private ItemClickListerner itemClickListerner;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderAddress =(TextView) itemView.findViewById(R.id.order_address);
        txtOrderId =(TextView) itemView.findViewById(R.id.order_id);
        txtOrderStatus =(TextView) itemView.findViewById(R.id.order_status);
        txtOrderPhone =(TextView) itemView.findViewById(R.id.order_phone);

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
