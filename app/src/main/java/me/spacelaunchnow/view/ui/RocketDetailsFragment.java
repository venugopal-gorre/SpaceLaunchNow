package me.spacelaunchnow.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import me.spacelaunchnow.R;
import me.spacelaunchnow.databinding.FragmentRocketDetailsBinding;
import me.spacelaunchnow.model.pojo.Result;
import me.spacelaunchnow.view.callback.OnRocketDetailsAvailableListener;
import me.spacelaunchnow.view.util.Util;
import me.spacelaunchnow.viewmodel.RocketDetailsViewModel;

public class RocketDetailsFragment extends Fragment {
    public static final String ARG_RESULT = "result";
    public static final String ARG_IS_TWO_PANE = "is_two_pane";
    private FragmentRocketDetailsBinding mBinding;
    private OnRocketDetailsAvailableListener mListener;
    private Result mResult;
    private boolean mIsTwoPane;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // it works for single pane screens
        if(context instanceof OnRocketDetailsAvailableListener) {
            mListener = (OnRocketDetailsAvailableListener) context;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // If activity recreated (such as from screen rotate), restore
        // the previous rocket selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mResult = savedInstanceState.getParcelable(ARG_RESULT);
            mIsTwoPane = savedInstanceState.getBoolean(ARG_IS_TWO_PANE);
        }
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_details, container, false);
        mBinding.setRocketDetails(null);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            mResult = args.getParcelable(ARG_RESULT);
            mIsTwoPane = args.getBoolean(ARG_IS_TWO_PANE);
            fetchRocketDetails();
        }
    }

    public void updateRocketDetails(Result result, boolean isTwoPane) {
        mResult = result;
        mIsTwoPane = isTwoPane;
        fetchRocketDetails();
    }

    public void fetchRocketDetails() {
        if(mResult != null) {
            // Below code doesn't help to retrieve the RocketDetails for every selected Rocket on two pane
            // screens as this ViewModel will never be recreated as RocketDetailsFragment will never be
            // recreated for every Rocket Selection but it works fine for single pane screens.
//            RocketDetailsViewModel.Factory factory = new RocketDetailsViewModel.Factory(String.valueOf(mResult.getRocket().getConfiguration().getId()));
//            RocketDetailsViewModel rocketDetailsViewModel = new ViewModelProvider(this, factory).get(RocketDetailsViewModel.class);
            RocketDetailsViewModel rocketDetailsViewModel = new ViewModelProvider(this).get(RocketDetailsViewModel.class);
            rocketDetailsViewModel.getRocketDetailsLiveData(String.valueOf(mResult.getRocket().getConfiguration().getId())).observe(this, rocketDetails -> {
                if(rocketDetails == null) {
                    //mBinding.tvRocketDetailsError.setVisibility(View.VISIBLE); // No need to show this error here as we already have Rocket details from Result
                } else {
                    mBinding.setRocketDetails(rocketDetails);
                    // Send RocketDetails back to RocketDetailsActivity to load large Rocket image in the Toolbar
                    if(mListener != null) {
                        mListener.onRocketDetailsAvailable(rocketDetails);
                    }
                }
            });
            mBinding.setRocketName(mResult.getName());
            mBinding.setRocketDateTime(Util.convertUtcToCurrentTime(mResult.getNet()));
            mBinding.setRocketDescription(mResult.getMission() != null ? mResult.getMission().getDescription() : "No Description");
            mBinding.setRocketLocation(mResult.getPad().getLocation().getName());
            mBinding.setRocketStatus(mResult.getStatus().getName());
        }else {
            mBinding.tvRocketDetailsError.setVisibility(View.VISIBLE);
        }
        // show Rocket ImageView if it is Two Pane screen as we already shows it under Toolbar for single pane screens
        if(mIsTwoPane) {
            mBinding.ivRocketDetailsImage.setVisibility(View.VISIBLE);
        }
    }

}
