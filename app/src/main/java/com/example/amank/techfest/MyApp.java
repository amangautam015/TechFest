package com.example.amank.techfest;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by amank on 5/12/17.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        /* EXTRACTING INFORMATION FROM DATABASE */


        ArrayList<Item> item  =  new ArrayList<Item>();

        final ArrayList<ArrayList<String>> name = new ArrayList<ArrayList<String>>();
        final ArrayList<ArrayList<String>> link = new ArrayList<ArrayList<String>>();
        final ArrayList<ArrayList<String>> imglink = new ArrayList<ArrayList<String>>();
        final ArrayList<ArrayList<String>> intro = new ArrayList<ArrayList<String>>();
        final ArrayList<ArrayList<String>> time = new ArrayList<ArrayList<String>>();

        final String[] super_events = new String[]{"competitions","technoholix","ideate","workshops","initiatives","ozone","summit","lectures","exhibitions","World_Mun","cyclothon","sponsers"};



        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                int j = 0;
                for(int i = 0;i<12;i++) {
                    String value;
                    name.add(new ArrayList<String>());
                    link.add(new ArrayList<String>());
                    intro.add(new ArrayList<String>());
                    imglink.add( new ArrayList<String>());
                    time.add(new ArrayList<String>());
                    for  (DataSnapshot postSnap : dataSnapshot.child(super_events[i]).getChildren()) {

                        value = postSnap.child("name").getValue(String.class);
                        name.get(i).add(value);

                        //    Log.e("MyApp",""+i);
                        //   Log.e("MyApp",EventActivity.Data_super.loff.get(i).getMName().get(0));
                        value = postSnap.child("link").getValue(String.class);
                        link.get(i).add(value);

                        //     Log.e("MyApp",name.get(j));
                        value = postSnap.child("imglink").getValue(String.class);
                        imglink.get(i).add(value);

                        value = postSnap.child("intro").getValue(String.class);
                        intro.get(i).add(value);

                        value = postSnap.child("time").getValue(String.class);
                        time.get(i).add(value);
                        //     Log.e("MyApp",""+j);
                        ++j;
                    }
                    Log.e("MyApp",name.toString());

                    EventActivity.Data_super.loff.add(new data(name.get(i),link.get(i),imglink.get(i),intro.get(i),time.get(i)));

                    //   Log.e("MyApp",""+i);
                    Log.e("MyApp",EventActivity.Data_super.loff.get(i).getMName().get(0));
                    //   Log.e("MyApp",EventActivity.Data_super.loff.get(i).getMName().size()+"");

                }




                //   Log.d(TAG, "Value is: " + kvalue.get(0));

            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("BlankNew", "Failed to read value.", error.toException());
            }
        });







         /* EXTRACTING INFORMATION FROM DATABASE */

    }


}
