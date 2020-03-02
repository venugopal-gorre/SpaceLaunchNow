package me.spacelaunchnow.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import me.spacelaunchnow.model.pojo.UpcomingRockets;
import me.spacelaunchnow.model.repository.SpaceLaunchNowRepository;
import me.spacelaunchnow.view.ui.RocketsListFragment;

public class RocketsListViewModel extends ViewModel {
    private LiveData<UpcomingRockets> mUpcomingResponseLiveData;
    public RocketsListViewModel(){
        mUpcomingResponseLiveData = SpaceLaunchNowRepository.getInstance()
                .getUpcomingRocketsList(SpaceLaunchNowRepository.Format.JSON.toString().toLowerCase());
    }
    public LiveData<UpcomingRockets> getRocketsListLiveData() {
        return mUpcomingResponseLiveData;
    }
    public LiveData<UpcomingRockets> getMoreRocketsListLiveData(int offset) {
        return SpaceLaunchNowRepository.getInstance().getMoreUpcomingRocketsList(
                SpaceLaunchNowRepository.Format.JSON.toString().toLowerCase(),
                String.valueOf(RocketsListFragment.LIMIT),
                String.valueOf(offset));
    }
}
