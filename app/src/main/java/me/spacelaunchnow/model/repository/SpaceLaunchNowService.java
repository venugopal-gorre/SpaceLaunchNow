package me.spacelaunchnow.model.repository;

import me.spacelaunchnow.model.pojo.RocketDetails;
import me.spacelaunchnow.model.pojo.UpcomingRockets;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpaceLaunchNowService {
    @GET("launch/upcoming")
    Call<UpcomingRockets> getUpcomingRockets(@Query("format") String format);

    @GET("launch/upcoming")
    Call<UpcomingRockets> getMoreUpcomingRockets(@Query("format") String format, @Query("limit") String limit, @Query("offset") String offset);

    @GET("config/launcher/{rocket_config_id}")
    Call<RocketDetails> getRocketDetails(@Path("rocket_config_id") String rocketConfigId);

}
