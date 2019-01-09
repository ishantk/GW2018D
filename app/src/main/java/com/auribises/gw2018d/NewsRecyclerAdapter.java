package com.auribises.gw2018d;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>{

    Context context;
    int resource;
    ArrayList<News> objects;

    public NewsRecyclerAdapter(Context context, int resource, ArrayList<News> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        // view is pointing to list item
        View view = inflater.inflate(resource, parent, false);

        ViewHolder holder = new ViewHolder(view);
        //holder.setYourOwnListener();

        return holder;
    }


    //onBindViewHolder will be executed n number of times
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = objects.get(position);
        holder.imageView.setBackgroundResource(news.image);
        holder.txtTitle.setText(news.title);
        holder.txtURL.setText(news.url);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    // Inner Class or Nested Class
    // ViewHolder is holding views of list item
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtTitle;
        TextView txtURL;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtTitle = itemView.findViewById(R.id.textViewNewsTitle);
            txtURL = itemView.findViewById(R.id.textViewURL);

        }
    }

}
