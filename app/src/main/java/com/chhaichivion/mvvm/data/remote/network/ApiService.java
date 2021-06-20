package com.chhaichivion.mvvm.data.remote.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/22/21.
 * Position : Senior Application Development Officer
 */
public class ApiService {

    private static final String BASE_URL = "https://loabe.prasac.com.kh/";
    private static Retrofit sInstance = null;
    private Context context;

    public static Retrofit getInstance(Context context){
        context = context;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return sInstance;
    }

    private static OkHttpClient okHttpClient(Context context){
        try {
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            List<TrustManager> trustAllCerts = new ArrayList<>();
            trustAllCerts.add(trustManager);
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new X509TrustManager[]{(X509TrustManager) trustAllCerts.get(0)}, new SecureRandom());
            SSLSocketFactory sslSocketFactory  = sslContext.getSocketFactory();
            return new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory,(X509TrustManager) trustAllCerts.get(0))
                    .addInterceptor(new HeaderInterceptor(context))
                    .addInterceptor(httpLoggingInterceptor())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
}
