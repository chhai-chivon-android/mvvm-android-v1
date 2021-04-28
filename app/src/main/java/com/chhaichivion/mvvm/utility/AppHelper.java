package com.chhaichivion.mvvm.utility;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/28/21.
 * Position : Senior Application Development Officer
 */
public class AppHelper extends Application {

    private static AppHelper INSTANCE = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static AppHelper getInstance()  {
        if(Objects.isNull(INSTANCE)) {
            INSTANCE = new AppHelper();
        }
        return INSTANCE;
    }

    public Boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
