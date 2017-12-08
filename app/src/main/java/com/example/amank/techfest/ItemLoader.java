package com.example.amank.techfest;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by amank on 5/12/17.
 */

public class ItemLoader extends AsyncTaskLoader<List<Item>> {
    private int i;
    public ItemLoader(Context context, int k) {
        super(context);
        i=k;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<Item> loadInBackground() {
        return QueryFutil.fetchData(i);
    }
}
