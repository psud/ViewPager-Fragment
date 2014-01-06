package com.example.slidingtut;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class TutorialActivity extends FragmentActivity {
	/**
	 * The number of pages (wizard steps) to show in this demo.
	 */
	private static final int NUM_PAGES = 7;

	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next wizard steps.
	 */
	private ViewPager mPager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.tutorial_activity);

		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				invalidateOptionsMenu();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.tutorial_buttons, menu);

		menu.findItem(R.id.action_last).setEnabled(mPager.getCurrentItem() < NUM_PAGES-1);
		Log.d("CurrentItem", String.valueOf(mPager.getCurrentItem()));

		MenuItem item = null;
		item = menu.add(Menu.NONE,R.id.action_next,Menu.NONE,(mPager.getCurrentItem() == mPagerAdapter.getCount() - 1) ? R.string.action_next: R.string.action_next);
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_last:
			// Go to the previous step in the wizard. If there is no previous
			// step,
			// setCurrentItem will do nothing.
			mPager.setCurrentItem(NUM_PAGES);
			return true;

		case R.id.action_next:
			// Advance to the next step in the wizard. If there is no next step,
			// setCurrentItem
			// will do nothing.
			mPager.setCurrentItem(mPager.getCurrentItem() + 1);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void setNextPage(){
		Log.d("SetPage", "SetPage Next");
		mPager.setCurrentItem(mPager.getCurrentItem() + 1);
	}
	public void setLastPage(){
		Log.d("SetPage", "SetPage Login");
		mPager.setCurrentItem(NUM_PAGES);
	}

	/**
	 * A simple pager adapter that represents 5 {@link ScreenSlidePageFragment}
	 * objects, in sequence.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment frag = new Fragment1();
			switch (position) {
			case 0:
				frag = new Fragment1();
				break;
			case 1:
			case 2:
			case 3:
			case 4:
				return TutFragGeneral.create(position);
			case 5:
				frag = new TutFragDownload();
				break;
			case 6:
				frag = new FragmentLogin();
				break;
			}

			return frag;
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}
}