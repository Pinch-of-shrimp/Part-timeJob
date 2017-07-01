package com.example.wkg.part_timejob;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/6/27.
 */

public interface RequestInterface{
    @POST("PTJ-Server/index.php/user")
    Call<ServerResponse> operation(@Body ServerRequest request);
}