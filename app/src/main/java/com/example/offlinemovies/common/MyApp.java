package com.example.offlinemovies.common;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static MyApp instancia;

    public static MyApp getInstancia(){
        return instancia;
    }

    public static Context getContexto(){
        return instancia;
    }

    @Override
    public void onCreate() {
        instancia = this;
        super.onCreate();
    }
}
