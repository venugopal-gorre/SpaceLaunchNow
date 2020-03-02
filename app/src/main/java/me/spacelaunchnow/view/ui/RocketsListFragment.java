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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.spacelaunchnow.R;
import me.spacelaunchnow.databinding.FragmentRocketsListBinding;
import me.spacelaunchnow.model.pojo.UpcomingRockets;
import me.spacelaunchnow.view.adapter.RocketRecyclerViewAdapter;
import me.spacelaunchnow.view.callback.OnRocketClickListener;
import me.spacelaunchnow.view.util.Util;
import me.spacelaunchnow.viewmodel.RocketsListViewModel;

/**
 * A fragment representing a list of Rockets.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnRocketClickListener}
 * interface.
 */
public class RocketsListFragment extends Fragment {
    public static final String TAG = "RocketsListFragment";
    public static final int LIMIT = 10;
    private OnRocketClickListener mListener;
    private RocketRecyclerViewAdapter mAdapter;
    private RocketsListViewModel mViewModel;
    private FragmentRocketsListBinding mBinding;
    private boolean mIsLoading;
    private int mOffset;
    private boolean mIsNextAvailable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRocketClickListener) {
            mListener = (OnRocketClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_rockets_list, container, false);
        mAdapter = new RocketRecyclerViewAdapter(mListener);
        mBinding.recyclerViewRockets.setAdapter(mAdapter);
        mBinding.recyclerViewRockets.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerViewRockets.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (mIsNextAvailable && !mIsLoading && linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == mAdapter.getResultList().size() - 1) {
                    loadMoreRockets();
                }
            }
        });
        mBinding.swipeRefreshRockets.setColorSchemeResources(R.color.colorPrimary);
        mBinding.swipeRefreshRockets.setOnRefreshListener(() ->{
            if(Util.checkInternetConnection(getActivity())) {
                // clear existing data as you are refreshing the list that starts from the beginning
                mAdapter.clearResults();
                loadInitialRockets();
            } else{
                mBinding.swipeRefreshRockets.setRefreshing(false);
                mBinding.loadingProjects.setText(R.string.no_internet);
            }
        });
        mBinding.setIsLoadingVisible(true);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadInitialRockets();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void handleUpcomingResponse(UpcomingRockets upcomingResponse) {
        mBinding.setIsLoadingVisible(false);
        if(upcomingResponse == null || upcomingResponse.getResults() == null) {
            mBinding.loadingProjects.setText(R.string.error_rocket_details);
        } else {
            if(upcomingResponse.getNext()==null) {
                mIsNextAvailable = false;
            } else{
                mIsNextAvailable = true;
            }
            mAdapter.setResultList(upcomingResponse.getResults());
            mAdapter.notifyDataSetChanged();
        }
        mIsLoading = false;
    }

    private void loadInitialRockets() {
        if(Util.checkInternetConnection(getActivity())) {
            mViewModel = new ViewModelProvider(this).get(RocketsListViewModel.class);
            mViewModel.getRocketsListLiveData().observe(getViewLifecycleOwner(), upcomingResponse-> {
                mBinding.swipeRefreshRockets.setRefreshing(false);
                handleUpcomingResponse(upcomingResponse);
            });
        } else{
            mBinding.loadingProjects.setText(R.string.no_internet);
        }
    }
    private void loadMoreRockets() {
        mIsLoading = true;
        // increase offset value by LIMIT to load limited data from server
        mOffset += LIMIT;
        // add an empty item to show 'loading' item at bottom the list
        mAdapter.getResultList().add(null);
        mAdapter.notifyItemInserted(mAdapter.getResultList().size()- 1);
        mViewModel.getMoreRocketsListLiveData(mOffset).observe(getViewLifecycleOwner(), upcomingResponse -> {
            // remove 'loading' item from the list as we got the response from server
            mAdapter.getResultList().remove(mAdapter.getResultList().size()- 1);
            mAdapter.notifyItemRemoved(mAdapter.getResultList().size());
            handleUpcomingResponse(upcomingResponse);
        });
    }

}
