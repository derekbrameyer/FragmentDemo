package com.doomonafireball.fragmentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewFragment extends ListFragment {
	boolean mDualPane;
	int mCurCheckPosition = 0;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Populate list with a static array of test data
		switch (getShownRace()) {
		case 0:
			// protoss
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, getActivity()
							.getResources().getStringArray(
									R.array.protoss_demo_data)));
			break;
		case 1:
			// terran
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, getActivity()
							.getResources().getStringArray(
									R.array.terran_demo_data)));
			break;
		case 2:
			// zerg
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, getActivity()
							.getResources().getStringArray(
									R.array.zerg_demo_data)));
			break;
		default:
			// protoss
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, getActivity()
							.getResources().getStringArray(
									R.array.protoss_demo_data)));
			break;
		}

		// Check to see if we have a frame in which to embed
		// DetailViewFragment directly
		View detailsFrame = getActivity().findViewById(R.id.FLDetailView);
		mDualPane = detailsFrame != null
				&& detailsFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			// Restore the last state for the checked position
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
		}

		if (mDualPane) {
			// In dual-pane mode, the list view highlights the selected item
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			// Make sure our UI is in the correct state
			showDetails(mCurCheckPosition, getShownRace());
		}
	}

	public int getShownRace() {
		Log.d("FragmentDemo", "My list race: "
				+ getArguments().getInt("race", 0));
		return getArguments().getInt("race", 0);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position, getShownRace());
	}

	void showDetails(int index, int race) {
		// Helper function to show details of a selected item
		// This will either display a fragment in place or start
		// a whole new activity
		mCurCheckPosition = index;

		if (mDualPane) {
			// We can display everything in-place with fragments
			// Update the list to highlight the selected item and show data
			getListView().setItemChecked(index, true);

			// Check what fragment is currently shown, replace if needed
			DetailViewFragment details = (DetailViewFragment) getFragmentManager()
					.findFragmentById(R.id.FLDetailView);
			if (details == null || details.getShownIndex() != index
					|| details.getShownRace() != race) {
				// Make new fragment to show this selection
				details = DetailViewFragment.newInstance(index);
				Bundle args = new Bundle();
				args.putInt("index", index);
				args.putInt("race", getShownRace());
				details.setArguments(args);
				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.FLDetailView, details);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		} else {
			// Otherwise we need to launch a new activity
			Intent intent = new Intent();
			intent.setClass(getActivity(), DetailViewActivity.class);
			intent.putExtra("index", index);
			intent.putExtra("race", getShownRace());
			startActivity(intent);
		}
	}
}
