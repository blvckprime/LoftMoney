package com.example.loftmoney.remote;

import com.google.gson.annotations.SerializedName;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class AuthResponse {
    @SerializedName("id")
    private String userId;

    @SerializedName("auth_token")
    private String authToken;

    @SerializedName("status")
    private String status;

    public String getUserId() {
        return userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getStatus() {
        return status;
    }
}
