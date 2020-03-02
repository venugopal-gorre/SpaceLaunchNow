package me.spacelaunchnow.view.ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import me.spacelaunchnow.R;
import me.spacelaunchnow.databinding.ActivityRocketDetailsBinding;
import me.spacelaunchnow.model.pojo.Result;
import me.spacelaunchnow.model.pojo.RocketDetails;
import me.spacelaunchnow.view.callback.OnRocketDetailsAvailableListener;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RocketDetailsActivity}.
 */
public class RocketDetailsActivity extends AppCompatActivity implements OnRocketDetailsAvailableListener {

    private ActivityRocketDetailsBinding mActivityRocketDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityRocketDetailsBinding = ActivityRocketDetailsBinding.inflate(getLayoutInflater());
        setContentView(mActivityRocketDetailsBinding.getRoot());
        setSupportActionBar(mActivityRocketDetailsBinding.toolbarFragmentDetails);

        Result result = getIntent().getParcelableExtra(RocketDetailsFragment.ARG_RESULT);
        boolean isTwoPane = getIntent().getBooleanExtra(RocketDetailsFragment.ARG_IS_TWO_PANE, false);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(result.getRocket().getConfiguration().getName());
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create fragment and give it an argument for the selected rocket
            RocketDetailsFragment rocketDetailsFragment = new RocketDetailsFragment();
            Bundle args = new Bundle();
            args.putParcelable(RocketDetailsFragment.ARG_RESULT, result);
            args.putBoolean(RocketDetailsFragment.ARG_IS_TWO_PANE, isTwoPane);
            rocketDetailsFragment.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_details_container, rocketDetailsFragment, null)
                    .commit();
        }
    }

    @Override
    public void onRocketDetailsAvailable(RocketDetails rocketDetails) {
        if(rocketDetails != null) {
            Glide.with(this)
                    .load(rocketDetails.getImageUrl())
                    .placeholder(R.drawable.rocket_256)
                    .useAnimationPool(true)
                    .into(mActivityRocketDetailsBinding.ivTitlebarRocketDetailsImage);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
