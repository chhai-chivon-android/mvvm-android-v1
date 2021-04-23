package com.chhaichivion.mvvm.utility;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Copyright (c) PRASAC MFI, Ltd. All rights reserved. (https://www.prasac.com.kh/)
 * Author	: Chhai Chivon (chivon.chhai@prasac.com.kh) on 4/23/21.
 * Position : Senior Application Development Officer
 */
public class Utility {

    public static void displayFragment(Fragment fragment, FragmentManager fragmentManager, @IdRes int fragmentContainer) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

}
