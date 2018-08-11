package net.cranix.mvvmtest.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import net.cranix.mvvmtest.R;
import net.cranix.mvvmtest.model.Board;

class MainViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleView;

    public MainViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false));

        titleView = itemView.findViewById(R.id.text_title);
    }

    public void update(Board board) {
        titleView.setText(board.title);
    }
}
