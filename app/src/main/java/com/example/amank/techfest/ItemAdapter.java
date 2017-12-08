package com.example.amank.techfest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by amank on 2/12/17.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Activity context, ArrayList<Item> news) {

        super(context,0,news);
    }



    @Override
    public View getView(int position , View currentView , ViewGroup parent) {

        View listItemView = currentView;
        if (listItemView== null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }
        final LinearLayout ll = (LinearLayout) listItemView.findViewById(R.id.main_view);
        Item currentFile = getItem(position);
        //   ImageView iv1 = (ImageView)listItemView.findViewById(R.id.iv1);
        //   Picasso.with(getContext()).load(currentFile.getImageResource()).placeholder(R.drawable.unnamed).resize(80,80).centerCrop().into(iv1);
        Picasso.with(getContext()).load(currentFile.getImageResource()).placeholder(R.drawable.unnamed).into(new Target(){


            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                ll.setBackground(new BitmapDrawable(getContext().getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }

        });

        TextView tv1 =  (TextView)listItemView.findViewById(R.id.tv1);
        tv1.setText(currentFile.getEvent_name());
        TextView tv2 = (TextView) listItemView.findViewById(R.id.tv2);
        tv2.setText(currentFile.getTime_venue());
        return listItemView;
    }
}
