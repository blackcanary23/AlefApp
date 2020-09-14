package com.example.alefapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class ListFragmentAdapter extends RecyclerView.Adapter<ListFragmentAdapter.ViewHolder> {

    private ArrayList<String> imageList;
    private ImageClicked activity;
    private int dpMetrics;

    public interface ImageClicked {

        void onImageClicked(String image);
    }

    public ListFragmentAdapter(Context context, ArrayList<String> imageList, int dpMetrics) {

        this.imageList = imageList;
        activity = (ImageClicked) context;
        this.dpMetrics = dpMetrics;
    }

    @NonNull
    @Override
    public ListFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list, parent, false);

        return new ListFragmentAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListFragmentAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(imageList.get(position));

        Picasso.get().load(imageList.get(position))
                .resize(dpMetrics, dpMetrics)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {

        return imageList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        ViewHolder(@NonNull View itemView) {

            super(itemView);

            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    activity.onImageClicked(imageList.get(getAdapterPosition()));

                }
            });

        }
    }
}
