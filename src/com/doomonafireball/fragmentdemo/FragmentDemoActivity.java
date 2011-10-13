package com.doomonafireball.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDemoActivity extends FragmentActivity {
	private FragmentManager mFragmentManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mFragmentManager = getSupportFragmentManager();

		final ActionBar ab = getSupportActionBar();
		ab.setTitle("Demo Items");
		ab.setDisplayHomeAsUpEnabled(false);
		ab.setDisplayShowHomeEnabled(true);
		ab.setDisplayUseLogoEnabled(false);
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the Fragments for these tabs
		Fragment protossFragment = new ListViewFragment();
		Bundle protossArgs = new Bundle();
		protossArgs.putInt("race", 0);
		protossFragment.setArguments(protossArgs);
		ab.addTab(ab.newTab().setText(R.string.action_bar_protoss_tab)
				.setTabListener(new TabListener(protossFragment)));

		Fragment terranFragment = new ListViewFragment();
		Bundle terranArgs = new Bundle();
		terranArgs.putInt("race", 1);
		terranFragment.setArguments(terranArgs);
		ab.addTab(ab.newTab().setText(R.string.action_bar_terran_tab)
				.setTabListener(new TabListener(terranFragment)));

		Fragment zergFragment = new ListViewFragment();
		Bundle zergArgs = new Bundle();
		zergArgs.putInt("race", 2);
		zergFragment.setArguments(zergArgs);
		ab.addTab(ab.newTab().setText(R.string.action_bar_zerg_tab)
				.setTabListener(new TabListener(zergFragment)));
	}

	private class TabListener implements ActionBar.TabListener {
		private Fragment mFragment;

		// Called to create an instance of the listener when adding a new tab
		public TabListener(Fragment fragment) {
			mFragment = fragment;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Show the specific tab selected
			ListViewFragment list = (ListViewFragment) mFragmentManager
					.findFragmentById(R.id.FLTabContent);
			if (list == null
					|| list.getShownRace() != ((ListViewFragment) mFragment)
							.getShownRace()) {
				// Make a new fragment to show this list
				FragmentTransaction ft2 = mFragmentManager.beginTransaction();
				ft2.replace(R.id.FLTabContent, mFragment);
				ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft2.commit();
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// Auto-generated method stub
			// ft.remove(mFragment);
			/*FragmentTransaction xaction = mFragmentManager.beginTransaction();
			xaction.remove(mFragment);
			xaction.commit();*/
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// Auto-generated method stub

		}
	}
}