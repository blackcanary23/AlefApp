package com.example.alefapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements ListFragmentAdapter.ImageClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DetailedFragment detailedFragment;
        FragmentManager fMan = getSupportFragmentManager();

        ListFragment listFragment = new ListFragment();
        fMan.beginTransaction()
                .add(R.id.container, listFragment, "listFrag")
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onImageClicked(String image) {

    }
}