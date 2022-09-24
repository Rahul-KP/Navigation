package com.ambulancepath.navigation;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class FirebaseData {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance("https://navigation-dd80b-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private final DatabaseReference ref = database.getReference("date");

    public FirebaseData() {
        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void sendData() {
        String data = new Date().toString();
        // put ur firebase code here to send data to firebase
        // Write a message to the database
        ref.setValue(data);
    }
}
