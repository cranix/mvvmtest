package net.cranix.mvvmtest;

import net.cranix.mvvmtest.model.Board;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("board")
    Single<List<Board>> getBoards();

    @POST("board")
    @FormUrlEncoded
    Single<Board> save(@Field("title") String title);
}
