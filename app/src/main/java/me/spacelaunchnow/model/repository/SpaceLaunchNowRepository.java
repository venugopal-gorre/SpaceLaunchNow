package me.spacelaunchnow.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import me.spacelaunchnow.BuildConfig;
import me.spacelaunchnow.model.pojo.RocketDetails;
import me.spacelaunchnow.model.pojo.UpcomingRockets;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpaceLaunchNowRepository {
    private SpaceLaunchNowService spaceLaunchNowService;
    private static SpaceLaunchNowRepository spaceLaunchNowRepository;

    private SpaceLaunchNowRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        spaceLaunchNowService = retrofit.create(SpaceLaunchNowService.class);
    }

    public synchronized static SpaceLaunchNowRepository getInstance() {
        if (spaceLaunchNowRepository == null) {
            spaceLaunchNowRepository = new SpaceLaunchNowRepository();
        }
        return spaceLaunchNowRepository;
    }

    public LiveData<UpcomingRockets> getUpcomingRocketsList(String format) {
        final MutableLiveData<UpcomingRockets> data = new MutableLiveData<>();
        spaceLaunchNowService.getUpcomingRockets(format).enqueue(new Callback<UpcomingRockets>() {
            @Override
            public void onResponse(Call<UpcomingRockets> call, Response<UpcomingRockets> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UpcomingRockets> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<UpcomingRockets> getMoreUpcomingRocketsList(String format, String limit, String offset) {
        final MutableLiveData<UpcomingRockets> data = new MutableLiveData<>();
        spaceLaunchNowService.getMoreUpcomingRockets(format, limit, offset).enqueue(new Callback<UpcomingRockets>() {
            @Override
            public void onResponse(Call<UpcomingRockets> call, Response<UpcomingRockets> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UpcomingRockets> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<RocketDetails> getRocketDetails(String rocketConfigId) {
        final MutableLiveData<RocketDetails> data = new MutableLiveData<>();

        spaceLaunchNowService.getRocketDetails(rocketConfigId).enqueue(new Callback<RocketDetails>() {
            @Override
            public void onResponse(Call<RocketDetails> call, Response<RocketDetails> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RocketDetails> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public enum Format {
        JSON
    }
}
