package net.cranix.mvvmtest;

import com.google.gson.Gson;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class C {

    private static ApiService api;

    public static ApiService getApi() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl("http://192.168.43.87:3000/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build()
                    .create(ApiService.class);
        }
        return api;
    }
}
