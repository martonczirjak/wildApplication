package com.plixapp.czirjak.wildjavaapplication.wilds.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.plixapp.czirjak.wildjavaapplication.R;
import com.plixapp.czirjak.wildjavaapplication.common.WildManager;
import com.plixapp.czirjak.wildjavaapplication.databinding.WildItemBinding;
import com.plixapp.czirjak.wildjavaapplication.wilds.model.Wild;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WildsAdapter extends RecyclerView.Adapter<WildsAdapter.ViewHolder> {

    private List<Wild> items;
    private WildManager wildManager = new WildManager();

    public WildsAdapter(List<Wild> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WildItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.wild_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Wild item = items.get(position);

        Picasso.get().load(wildManager.getDrawableFromName(items.get(position).getId())).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ViewHolder(WildItemBinding binding) {
            super(binding.getRoot());
            image = binding.imgWild;
        }
    }
}
