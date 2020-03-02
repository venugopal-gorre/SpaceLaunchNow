package me.spacelaunchnow.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import me.spacelaunchnow.model.pojo.RocketDetails;
import me.spacelaunchnow.model.repository.SpaceLaunchNowRepository;

public class RocketDetailsViewModel extends ViewModel {
//    private LiveData<RocketDetails> mRocketDetailsLiveData;

    // Below code doesn't help to retrieve the RocketDetails for every selected Rocket on two pane
    // screens as this ViewModel will never be recreated as RocketDetailsFragment will never be
    // recreated for every Rocket Selection but it works fine for single pane screens.
//    public RocketDetailsViewModel(String rocketConfigId) {
//        mRocketDetailsLiveData = SpaceLaunchNowRepository.getInstance().getRocketDetails(rocketConfigId);
//    }
//
//    public LiveData<RocketDetails> getRocketDetailsLiveData() {
//        return mRocketDetailsLiveData;
//    }
//
//    public static class Factory extends ViewModelProvider.NewInstanceFactory {
//        private final String mRocketConfigId;
//
//        public Factory(String rocketConfigId) {
//            mRocketConfigId = rocketConfigId;
//        }
//
//        @Override
//        public <T extends ViewModel> T create(Class<T> modelClass) {
//            return (T) new RocketDetailsViewModel(mRocketConfigId);
//        }
//    }

    // Below method works fine for both single and two pane screens
    public LiveData<RocketDetails> getRocketDetailsLiveData(String rocketConfigId) {
        return SpaceLaunchNowRepository.getInstance().getRocketDetails(rocketConfigId);
    }
}
