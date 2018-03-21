package com.example.yasirnazir.sky.models;

import java.util.List;

/**
 * Created by yasirnazir on 3/14/18.
 */


public class Response extends GenericModel {

    private List<Data> data;

    public Response(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo [data = " + data + "]";
    }


}