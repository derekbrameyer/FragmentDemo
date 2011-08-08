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
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final ActionBar ab = getSupportActionBar();
		ab.setTitle("Demo Items");
		ab.setDisplayHomeAsUpEnabled(false);
		ab.setDisplayShowHomeEnabled(true);
		ab.setDisplayUseLogoEnabled(false);
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the Fragments for these tabs
		Fragment protossFragment = new ListViewFragment();
		ab.addTab(ab.newTab().setText(R.string.action_bar_protoss_tab)
				.setTabListener(new TabListener(protossFragment)));

		Fragment terranFragment = new ListViewFragment();
		ab.addTab(ab.newTab().setText(R.string.action_bar_terran_tab)
				.setTabListener(new TabListener(terranFragment)));

		Fragment zergFragment = new ListViewFragment();
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
			// TODO Auto-generated method stub
			//ft.add(R.id.FLTabContent, mFragment);
			/*FragmentManager fragMgr = getSupportFragmentManager();
			FragmentTransaction xaction = fragMgr.beginTransaction();
			ft.replace(R.id.FRAGListView, mFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			xaction.commit();*/
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// Auto-generated method stub
			//ft.remove(mFragment);
			FragmentManager fragMgr = getSupportFragmentManager();
			FragmentTransaction xaction = fragMgr.beginTransaction();
			// removeFragments(fragMgr, xaction);
			xaction.commit();
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// Auto-generated method stub

		}
	}
}