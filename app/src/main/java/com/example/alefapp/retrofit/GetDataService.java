package com.example.alefapp.retrofit;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("list.php")
    Call<ArrayList<String>> getALLImages();
}