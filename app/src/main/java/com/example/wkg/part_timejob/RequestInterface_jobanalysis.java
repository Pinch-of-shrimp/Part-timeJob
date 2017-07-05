package com.example.wkg.part_timejob;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/7/5.
 */

public interface RequestInterface_jobanalysis  {
    @POST("PTJ-Server/index.php/JobAnalysis")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
