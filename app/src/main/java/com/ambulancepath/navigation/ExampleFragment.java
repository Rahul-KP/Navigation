package com.ambulancepath.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ExampleFragment extends Fragment implements View.OnClickListener {

    private Button location;

    public ExampleFragment() {
        super(R.layout.example_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.example_fragment, container, false);
        location = (Button) view.findViewById(R.id.button4);
        location.setOnClickListener(this);
        Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "Hello, go away", Toast.LENGTH_SHORT).show();
        location.setText("HI");
    }
}
