package com.kiran2kirve.gojekassignment.network;

//import com.kiran2kirve.riteshsirassignment.models.UserResult;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkInterface {

//working only one field
    @GET("repositories?since=daily&")
    Call<JsonArray> fetchUserListRequestApi();

}
