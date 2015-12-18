package com.example.student.hockeygame.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student.hockeygame.R;
import com.example.student.hockeygame.entity.ListItem;

import java.util.List;

public class HockeyGameListAdapter extends RecyclerView.Adapter<HockeyGameListAdapter.ViewHolder> {
    List<? extends ListItem> listItems;


    public HockeyGameListAdapter(List<? extends ListItem> listItems) {
        this.listItems = listItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hockey_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.getTextViewItemTitle().setText(listItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewItemTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewItemTitle = (TextView) itemView.findViewById(R.id.textViewItemTitle);
        }
        public TextView getTextViewItemTitle() {
            return textViewItemTitle;
        }
    }
}
