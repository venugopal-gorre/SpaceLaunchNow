package me.spacelaunchnow.view.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import me.spacelaunchnow.R;
import me.spacelaunchnow.model.pojo.Result;
import me.spacelaunchnow.view.callback.OnRocketClickListener;

public class MainActivity extends AppCompatActivity implements OnRocketClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we are in single must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            RocketsListFragment rocketsListFragment = new RocketsListFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, rocketsListFragment, RocketsListFragment.TAG)
                    .commit();
        }

    }

    @Override
    public void onRocketClick(Result result) {
        RocketDetailsFragment rocketDetailsFragment = (RocketDetailsFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_rocket_details);

        if (rocketDetailsFragment != null) {
            // If rocketdetails fragment is available, we're in two-pane layout...

            // Call a method in the RocketDetailsFragment to update its content
            rocketDetailsFragment.updateRocketDetails(result, true);

        } else {
            // If the fragment is not available, we're in the one-pane layout
            Intent intent = new Intent(this, RocketDetailsActivity.class);
            intent.putExtra(RocketDetailsFragment.ARG_RESULT, result);
            intent.putExtra(RocketDetailsFragment.ARG_IS_TWO_PANE, false);
            startActivity(intent);
        }
    }
}
