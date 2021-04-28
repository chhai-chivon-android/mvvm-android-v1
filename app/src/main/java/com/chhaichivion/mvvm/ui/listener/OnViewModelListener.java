package com.chhaichivion.mvvm.ui.listener;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/28/21.
 * Position : Senior Application Development Officer
 */
public interface OnViewModelListener<T> {

    void onStarted();
    void onSuccess(T response);
    void onFailure(String message);
}
