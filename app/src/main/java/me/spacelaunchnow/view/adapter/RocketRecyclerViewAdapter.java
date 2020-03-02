package me.spacelaunchnow.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.spacelaunchnow.R;
import me.spacelaunchnow.databinding.LayoutRocketsListItemBinding;
import me.spacelaunchnow.model.pojo.Result;
import me.spacelaunchnow.view.callback.OnRocketClickListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Result} and makes a call to the
 * specified {@link OnRocketClickListener}.
 */
public class RocketRecyclerViewAdapter extends RecyclerView.Adapter<RocketRecyclerViewAdapter.RocketViewHolder> {

    private final int VIEW_TYPE_ROCKET_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int lastPosition = -1;
    private List<Result> mResults = new ArrayList<>();
    private OnRocketClickListener mOnRocketClickListener;

    public RocketRecyclerViewAdapter(OnRocketClickListener listener) {
        mOnRocketClickListener = listener;
    }

    public void setResultList(List<Result> results) {
        mResults.addAll(results);
    }

    public List<Result> getResultList() {
        return mResults;
    }

    public void clearResults() {
        mResults.clear();
    }

    @Override
    public RocketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutRocketsListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_rockets_list_item,
                parent,
                false);
        if(mOnRocketClickListener != null) {
            binding.setListener(mOnRocketClickListener);
        }
        if(viewType == VIEW_TYPE_ROCKET_ITEM) {
            binding.setIsLoadingVisible(false);
        } else{
            binding.setIsLoadingVisible(true);
        }
        return new RocketViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RocketViewHolder rocketViewHolder, int position) {
        rocketViewHolder.mBinding.setResult(mResults.get(position));
        rocketViewHolder.mBinding.executePendingBindings();
        setAnimation(rocketViewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mResults.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ROCKET_ITEM;
    }

    static class RocketViewHolder extends RecyclerView.ViewHolder {
        private LayoutRocketsListItemBinding mBinding;

        public RocketViewHolder(LayoutRocketsListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private void setAnimation(View viewToAnimate, int position){
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
