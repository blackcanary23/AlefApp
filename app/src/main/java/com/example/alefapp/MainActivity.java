package com.example.alefapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements ListFragmentAdapter.ImageClicked {

    private FragmentManager fMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fMan = getSupportFragmentManager();
        ListFragment listFragment = new ListFragment();

        fMan.beginTransaction()
                .add(R.id.container, listFragment, "listFrag")
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onImageClicked(String url) {

        DetailedFragment detailedFragment = new DetailedFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("image", url);
        detailedFragment.setArguments(bundle);

        fMan.beginTransaction()
                .replace(R.id.container, detailedFragment, "detailFrag")
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}