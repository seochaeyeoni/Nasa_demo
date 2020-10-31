package com.example.nasa.src.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nasa.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TechTransferAdapter extends RecyclerView.Adapter<TechTransferAdapter.CustomViewHolders> {
    private ArrayList titleArrayList;
    private ArrayList descriptionArrayList;
    private ArrayList imageArrayList;


    public TechTransferAdapter(ArrayList titleArrayList, ArrayList descriptionArrayList, ArrayList imageArrayList) {
        this.titleArrayList = titleArrayList;
        this.descriptionArrayList = descriptionArrayList;
        this.imageArrayList = imageArrayList;

    }

    @NonNull
    @Override
    public TechTransferAdapter.CustomViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tech_transfer, parent, false);
        CustomViewHolders holders = new CustomViewHolders(view);
        Log.d("tech", "onCreateViewHolder");
        return holders;
    }

    @Override
    public void onBindViewHolder(@NonNull TechTransferAdapter.CustomViewHolders holder, int position) {
        Glide.with(holder.itemView.getContext()).load(imageArrayList.get(position)).into(holder.iv_image);
        holder.tv_title.setText((CharSequence) titleArrayList.get(position));
        holder.tv_description.setText((CharSequence) descriptionArrayList.get(position));
        Log.d("tech", String.valueOf(titleArrayList.get(position)));

    }

    @Override
    public int getItemCount() {
        return titleArrayList.size();
    }

    public class CustomViewHolders extends RecyclerView.ViewHolder{

        protected ImageView iv_image;
        protected TextView tv_title;
        protected TextView tv_description;

        public CustomViewHolders(@NonNull View itemView) {
            super(itemView);
            this.iv_image = (ImageView) itemView.findViewById(R.id.item_tech_transfer_iv);
            this.tv_title = (TextView) itemView.findViewById(R.id.item_tech_transfer_tv_title);
            this.tv_description = (TextView) itemView.findViewById(R.id.item_tech_transfer_tv_description);
        }
    }
}
