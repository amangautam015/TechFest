package com.example.amank.techfest;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment extends Fragment implements LoaderCallbacks<List<Item>> {
    private ItemAdapter adapter;
    private String Title;
    private int mPage;
    final private String TAG = "BlankFragment";
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String TITLE = "ARG_TITLE";

    LinearLayout emptyView;
    ArrayList<Item> item = new ArrayList<Item>();


    public static BlankFragment newInstance(int page, String title) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(TITLE, title);

        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPage = getArguments().getInt(ARG_PAGE);
        Title = getArguments().getString(TITLE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.activity_event, container, false);
        emptyView = (LinearLayout) rootView.findViewById(R.id.empty_state);
        final View bottomSheet = rootView.findViewById(R.id.design_bottom_sheet);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setPeekHeight(0);
//        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                switch (newState) {
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_DRAGGING");
//                        break;
//                    case BottomSheetBehavior.STATE_SETTLING:
//                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_SETTLING");
//                        break;
//                    case BottomSheetBehavior.STATE_EXPANDED:
//                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_EXPANDED");
//                        break;
//                    case BottomSheetBehavior.STATE_COLLAPSED:
//                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_COLLAPSED");
//                        break;
//                    case BottomSheetBehavior.STATE_HIDDEN:
//                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_HIDDEN");
//                        break;
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                Log.i("BottomSheetCallback", "slideOffset: " + slideOffset);
//
//
//            }
//        });


        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Title);


        // item.add(new Item("Opera Hat","https://techfest.org/img/workshop/nvidia.jpg","23:00 | LH101"));
        // item.add(new Item("Gamers","https://techfest.org/img/workshop/9.jpg","17:00 | SOM WELL"));
        // item.add(new Item("Android","https://techfest.org/img/workshop/14G.jpg","02:30 | LA201"));
        adapter = new ItemAdapter(getActivity(), item);
        ListView lv = (ListView) rootView.findViewById(R.id.list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, final long l) {
                final Item currentItem = adapter.getItem(i);

                String event_name = currentItem.getEvent_name();
                //  String description = getString(R.string.sample_description);
                //   final String link = "https://www.techfest.org";

                TextView eventVenue = (TextView) rootView.findViewById(R.id.event_venue);

                TextView eventDescription = (TextView) rootView.findViewById(R.id.event_description);
                Button know_more = (Button) rootView.findViewById(R.id.km);

                ImageView eventImage = (ImageView) rootView.findViewById(R.id.event_image);
                TextView toolbar = (TextView) rootView.findViewById(R.id.toolbar_title_1);
                toolbar.setText(event_name);
                eventDescription.setText(currentItem.getDescR());

                eventVenue.setText(currentItem.getTime_venue());
                Picasso.with(getActivity()).load(currentItem.getImageResource()).placeholder(R.drawable.unnamed).into(eventImage);
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    //bottomSheet.setLayoutParams(new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT));
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                know_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri tt = Uri.parse(currentItem.getIntroR());
                        // Create a new intent to view the news URI
                        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, tt);

                        // Send the intent to launch a new activity.
                        startActivity(websiteIntent);
                    }
                });


            }

        });
        TextView no_net = (TextView) rootView.findViewById(R.id.no_net);
        no_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getActivity().finish();
            }
        });
        ImageView close = (ImageView) rootView.findViewById(R.id.kclose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        getLoaderManager().initLoader(1, null, this);
        return rootView;

    }


    @Override
    public Loader<List<Item>> onCreateLoader(int id, Bundle args) {
        return new ItemLoader(getActivity(), mPage);
    }

    @Override
    public void onLoadFinished(Loader<List<Item>> loader, List<Item> data) {
        adapter.clear();

        // Ifl there is a valid list of {@link news}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.

        if (data != null && !data.isEmpty()) {
            emptyView.setVisibility(View.GONE);
            adapter.addAll(data);
        }


    }


    @Override
    public void onLoaderReset(Loader<List<Item>> loader) {
        adapter.clear();
    }



}