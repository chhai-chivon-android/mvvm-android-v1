package com.chhaichivion.mvvm.data.remote.network;

import com.chhaichivion.mvvm.data.remote.response.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/22/21.
 * Position : Senior Application Development Officer
 */
public interface PostApi {

    @GET("posts")
    Call<List<Post>> getPostsFromServer();
}
