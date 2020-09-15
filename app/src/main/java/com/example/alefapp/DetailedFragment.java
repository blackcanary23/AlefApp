package com.example.alefapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class DetailedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detailed, container, false);

        Bundle bundle = getArguments();
        assert bundle != null;
        String url = (String) bundle.getSerializable("image");

        ImageView imageView = view.findViewById(R.id.fullscreenimage);

        Picasso.get().load(url)
                .into(imageView);

        return view;
    }
}