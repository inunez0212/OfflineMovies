package com.example.offlinemovies.data.retrofit;

import com.example.offlinemovies.common.Constantes;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl newUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Constantes.API_KEY)
                .build();
        return chain.proceed(originalRequest.newBuilder().url(newUrl).build());
    }
}
