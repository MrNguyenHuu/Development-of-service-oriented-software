package com.example.musicapponline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicapponline.Activity.ListSongActivity;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<Category> categories;
    View view;

    public ListCategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_category_base,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Category category = categories.get(i);
        viewHolder.tv_category_item.setText(category.getName());
        Picasso.with(context).load(WebService.getImageUrl(category.getImage())).into(viewHolder.iv_category_item);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("category",category);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_category_item;
        TextView tv_category_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_category_item = itemView.findViewById(R.id.iv_list_category_item);
            tv_category_item = itemView.findViewById(R.id.tv_name_category_item);
        }
    }
}
