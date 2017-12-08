package com.example.amank.techfest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by amank on 3/12/17.
 */

public class BlankAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public BlankAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    private String tabTitle[]  = new String[] {"COMPETETION","TECHNOHOLIX","IDEATE","WORKSHOPS","INITIATIVES","OZONE","SUMMIT","LECTURES","EXHIBITIONS","WORLD MUN","CYCLOTHON","SPONSERS"};
    @Override
    public CharSequence getPageTitle(int position) { return tabTitle[position]; }
    @Override
    public Fragment getItem(int position) {

      return BlankFragment.newInstance(position+1,tabTitle[position]);

    }

    @Override
    public int getCount() {
        return 12;
    }
}


