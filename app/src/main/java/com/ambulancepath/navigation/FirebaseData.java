package com.ambulancepath.navigation;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import timber.log.Timber;

public class FirebaseData {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance("https://navigation-dd80b-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private final DatabaseReference ref = database.getReference("date");

    public FirebaseData() {
        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Timber.d("Value is: %s", value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Timber.w(error.toException(), "Failed to read value.");
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
