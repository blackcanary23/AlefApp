package com.example.alefapp;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.alefapp.retrofit.GetDataService;
import com.example.alefapp.retrofit.RetrofitClientInstance;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {

    private View view;
    private int dpWidth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list_adapter, container, false);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<String>> call = service.getALLImages();
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call,
                                   Response<ArrayList<String>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {

            }
        });

        return view;
    }

    /*void detectDeviceType() {

        TelephonyManager manager = (TelephonyManager)getActivity()
                .getSystemService(Context.TELEPHONY_SERVICE);

        assert manager != null;
        if (manager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }
    }*/

    void generateDataList(ArrayList<String> imageList) {

        ListFragmentAdapter flAdapter;
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        getDisplayMetrics();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            flAdapter = new ListFragmentAdapter(getActivity(), imageList, dpWidth);
        else
            flAdapter = new ListFragmentAdapter(getActivity(), imageList, dpWidth);

        recyclerView.setAdapter(flAdapter);

        final int spacing = getResources().getDimensionPixelSize(R.dimen.recycler_spacing) / 2;

        recyclerView.setPadding(spacing, spacing, spacing, spacing);
        recyclerView.setClipToPadding(false);
        recyclerView.setClipChildren(false);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(spacing, spacing, spacing, spacing);
            }
        });
    }

    private void getDisplayMetrics() {

        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        dpWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);
    }
}
