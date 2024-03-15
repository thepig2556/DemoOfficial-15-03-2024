package com.example.demo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private Activity mContext;
    List<Model> modelList;
    public ListAdapter(Activity mContext,List<Model> modelList){
        super(mContext,R.layout.list_items,modelList);
        this.mContext=mContext;
        this.modelList=modelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_items,null,true);
        TextView tvName = listItemView.findViewById(R.id.tvName);
        ImageView tvImage = listItemView.findViewById(R.id.tvImage);
        TextView tvAuthor = listItemView.findViewById(R.id.tvAuthor);

        Model manga = modelList.get(position);
        tvName.setText(manga.getTitle());
        Picasso.get().load(manga.getImage()).into(tvImage);
        tvAuthor.setText(manga.getAuthor());
        return listItemView;
    }
}
