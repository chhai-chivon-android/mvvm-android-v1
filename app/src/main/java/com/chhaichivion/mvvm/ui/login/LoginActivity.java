package com.chhaichivion.mvvm.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.chhaichivion.mvvm.R;
import com.chhaichivion.mvvm.data.remote.network.ApiService;
import com.chhaichivion.mvvm.data.remote.network.AuthApi;
import com.chhaichivion.mvvm.data.remote.response.AuthRes;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AuthApi request = ApiService.getInstance(getApplicationContext()).create(AuthApi.class);
        Call<AuthRes> call = request.login(loginPayload());
        call.enqueue(new Callback<AuthRes>() {
            @Override
            public void onResponse(Call<AuthRes> call, Response<AuthRes> response) {
                AuthRes authRes = response.body();
                Log.d("Data", new Gson().toJson(authRes));
                //mLoginListener.onSuccess(response.message(),new AuthRes());
            }
            @Override
            public void onFailure(Call<AuthRes> call, Throwable t) {
                Log.v("Error ",t.getMessage());
                //mLoginListener.onFailure(t.getMessage());
            }
        });
    }

    private Map<String,String> loginPayload(){
        Map<String,String> loginPayload = new HashMap<String,String>();
        loginPayload.put("grant_type","password");
        loginPayload.put("client_id","LOA_CLIENT_ID");
        loginPayload.put("client_secret","LOA_CLIENT_SECRET");
        loginPayload.put("username","chivon.chhai");
        loginPayload.put("password","$Hello123");
        loginPayload.put("isForceLogin","true");
        loginPayload.put("latitude","0.3432");
        loginPayload.put("longitude","0.3432");
        loginPayload.put("macAddress","vjvjd24222");
        loginPayload.put("device","ANDROID");
        loginPayload.put("deviceName","Chivon-Android");
        return loginPayload;
    }
}