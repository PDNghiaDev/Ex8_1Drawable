package com.gmail.pdnghiadev.ex8_1drawable.model;


import com.gmail.pdnghiadev.ex8_1drawable.untils.OnNextListener;

/**
 * Created by PDNghiaDev on 11/26/2015.
 */
public interface SignUpStep1Interactor {

    public void next(String firstname, String lastname, String email, String phonenumber, Boolean gender, OnNextListener listener);
}
