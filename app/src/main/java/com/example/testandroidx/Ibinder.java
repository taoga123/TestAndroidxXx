package com.example.testandroidx;


import android.os.Binder;



public class Ibinder extends Binder{
    BoundService  mBoundService;


    public BoundService getService(){

        mBoundService.music();
        return mBoundService;
    }

}