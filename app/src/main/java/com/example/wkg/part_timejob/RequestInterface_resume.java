package com.example.wkg.part_timejob;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/7/4.
 */

public interface RequestInterface_resume  {
    @POST("PTJ-Server/index.php/resume")
    Call<ServerResponse> operation(@Body ServerRequest request);
}