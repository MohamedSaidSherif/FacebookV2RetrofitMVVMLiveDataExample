package com.sherif.retrofitmvvmlivedataexample.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sherif.retrofitmvvmlivedataexample.R;
import com.sherif.retrofitmvvmlivedataexample.models.PostModel;

public class PostAdapter extends ListAdapter<PostModel, PostAdapter.PostViewHolder>
{
    private static final DiffUtil.ItemCallback<PostModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<PostModel>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull PostModel oldItem, @NonNull PostModel newItem)
        {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PostModel oldItem, @NonNull PostModel newItem)
        {
            return oldItem.getUserId() == newItem.getUserId() &&
                    oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getBody().equals(newItem.getBody());
        }
    };

    public PostAdapter()
    {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent,false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position)
    {
        PostModel currentPostModel = getItem(position);
        holder.userIdTextView.setText(String.valueOf(currentPostModel.getUserId()));
        holder.titleTextView.setText(currentPostModel.getTitle());
        holder.bodyTextView.setText(currentPostModel.getBody());
    }

    class PostViewHolder extends RecyclerView.ViewHolder
    {
        private TextView userIdTextView;
        private TextView titleTextView;
        private TextView bodyTextView;

        public PostViewHolder(@NonNull View itemView)
        {
            super(itemView);
            userIdTextView = itemView.findViewById(R.id.post_item_user_id_tv);
            titleTextView = itemView.findViewById(R.id.post_item_title_tv);
            bodyTextView = itemView.findViewById(R.id.post_item_body_tv);
        }
    }
}
