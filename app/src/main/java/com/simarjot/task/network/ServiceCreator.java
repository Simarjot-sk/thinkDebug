package com.simarjot.task.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {
    private static final Retrofit retrofit;

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.studyadda.com/service_app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T createService(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}
