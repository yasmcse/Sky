package com.example.yasirnazir.sky.models;

/**
 * Created by yasirnazir on 3/18/18.
 */

import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("code")
    private int mCode;

    @SerializedName("message")
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

}
