package me.spacelaunchnow.view.adapter;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import me.spacelaunchnow.R;
import me.spacelaunchnow.model.pojo.Result;
import me.spacelaunchnow.model.pojo.RocketDetails;

public class CustomBindingAdapter {
    @BindingAdapter("visibility")
    public static void setVisibility(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("countryIcon")
    public static void setCountrIcon(AppCompatImageView imageView, Result result) {
        if(result == null || result.getPad() == null) return;
        imageView.setVisibility(View.VISIBLE);
        switch (result.getPad().getLocation().getCountryCode()) {
            case "RUS":
                imageView.setImageResource(R.drawable.ic_russia);
                break;
            case "CHN":
                imageView.setImageResource(R.drawable.ic_china);
                break;
            case "UNK":
                imageView.setImageResource(R.drawable.ic_unknown);
                break;
            default:
                imageView.setVisibility(View.GONE);
                imageView.setImageDrawable(null);
                break;
        }
    }

    @BindingAdapter("rocketIcon")
    public static void setRocketIcon(AppCompatImageView imageView, Result result) {
        // Don't progress if Result is null
        if(result == null) return;
        String imageUrl = result.getImageUrl() == null ? null : result.getImageUrl().toString();
        // Don't progress if imageUrl is null
        if(imageUrl == null) return;
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .into(imageView);
    }

    @BindingAdapter("loadImage")
    public static void setRocketLargeImage(AppCompatImageView imageView, RocketDetails rocketDetails) {
        // Don't progress if Result is null
        if(rocketDetails == null) return;
        String imageUrl = rocketDetails.getImageUrl() == null ? null : rocketDetails.getImageUrl().toString();
        // Don't progress if imageUrl is null
        if(imageUrl == null) return;
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.rocket_256)
                .into(imageView);
    }
}
