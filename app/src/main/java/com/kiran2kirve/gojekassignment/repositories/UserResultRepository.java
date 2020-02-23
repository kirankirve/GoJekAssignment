package com.kiran2kirve.gojekassignment.repositories;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.kiran2kirve.gojekassignment.models.AuthorList;
import com.kiran2kirve.gojekassignment.network.NetworkClient;
import com.kiran2kirve.gojekassignment.network.NetworkInterface;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserResultRepository {
    private static UserResultRepository instance;
    private List<AuthorList> dataSet = new ArrayList<>();

    private NetworkInterface userResultApi;

    public static UserResultRepository getInstance(Context cxt) {
        if (instance == null) {
            instance = new UserResultRepository();
        }
        return instance;
    }

    public UserResultRepository() {
        userResultApi = NetworkClient.createService(NetworkInterface.class);
    }
    MutableLiveData<List<AuthorList>> userResultsData = new MutableLiveData<>();
    public MutableLiveData<List<AuthorList>> getUserResultList() {


        userResultApi.fetchUserListRequestApi().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call,
                                   Response<JsonArray> response) {

                try {
                    if (response.isSuccessful()) {
                        String getAuthorListFromJson = response.body().toString();
                        Type listType = new TypeToken<List<AuthorList>>() {
                        }.getType();
                        dataSet = getAuthorListFromJson(getAuthorListFromJson, listType);
                        userResultsData.setValue((List<AuthorList>) dataSet);
                        Log.e("", "" + getAuthorListFromJson);
                    }
                }catch (Exception e)
                {

                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                userResultsData.setValue(null);

                Log.e("","'");
            }
        });
        return userResultsData;

    }



    public static <T> List<T> getAuthorListFromJson(String jsonString, Type type) {
        if (!isValid(jsonString)) {
            return null;
        }
        return new Gson().fromJson(jsonString, type);
    }

    public static boolean isValid(String json) {
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonSyntaxException jse) {
            return false;
        }
    }
}
