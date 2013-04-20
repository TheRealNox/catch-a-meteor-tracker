//
// International Space Apps Challenge
//
// Catch A Meteor
// 		url: https://code.google.com/p/catch-a-meteor-tracker/
// 		made by:
//
// 			+ Aubry Nicolas (nox.aubry@gmail.com)
// 			+ Prevost Guillaume (guillaume.prevost.gp@gmail.com)
//
// file: SectionsPagerAdapter.java
// created: Apr 20, 2013
//


package com.nox.catch_a_meteor;

import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nox.catch_a_meteor.HomeActivity.DummySectionFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	private int TAB_NUMBERS = 3;
	private Context mContext;

	public SectionsPagerAdapter(FragmentManager fm, Context c) {
		super(fm);
		mContext = c;
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		if (position == 0) {
			Fragment fragment = new OpenGLSectionFragment();
			Bundle args = new Bundle();
			args.putInt(OpenGLSectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;				
		}
		else {
			return new DummySectionFragment();
		}
	}

	@Override
	public int getCount() {
		return TAB_NUMBERS;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return mContext.getString(R.string.title_section1_name).toUpperCase(l);
		case 1:
			return mContext.getString(R.string.title_section2_name).toUpperCase(l);
		case 2:
			return mContext.getString(R.string.title_section3_name).toUpperCase(l);
		}
		return null;
	}
}