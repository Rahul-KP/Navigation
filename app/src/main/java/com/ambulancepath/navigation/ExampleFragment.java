package com.ambulancepath.navigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ExampleFragment extends Fragment implements View.OnClickListener {

    private MapActivity activity;

    public ExampleFragment() {
        super(R.layout.example_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.example_fragment, container, false);
        this.activity = (MapActivity) getActivity();
        ImageButton gps = (ImageButton) view.findViewById(R.id.gps_btn);
        gps.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        activity.requestCamLocChange();
    }
}
