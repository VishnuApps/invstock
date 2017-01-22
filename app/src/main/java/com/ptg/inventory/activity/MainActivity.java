package com.ptg.inventory.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import com.ptg.inventory.adapter.NavDrawerListAdapter;
import com.ptg.inventory.fragments.DamagesFragment;
import com.ptg.inventory.fragments.DeliveryStockFragment;
import com.ptg.inventory.fragments.InwardListFragment;
import com.ptg.inventory.fragments.NonMovingProductSettingsFragment;
import com.ptg.inventory.fragments.ProductListFragment;
import com.ptg.inventory.fragments.ReportsFragment;
import com.ptg.inventory.fragments.VariableFragment;
import com.ptg.inventory.model.NavDrawerItem;
import com.ptg.inventory.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	public static MainActivity _CONTEXT;
	public int year;
	public int month;
	public int day;

	static final int DATE_PICKER_ID = 1111;
	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_CONTEXT=MainActivity.this;

		final Calendar c = Calendar.getInstance();
		year  = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day   = c.get(Calendar.DAY_OF_MONTH);


		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Inwords
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0]));
		// Delivery Stock
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1]));
		// Products
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2]));
		// Reports
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3]));
		// Contact us
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4]));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5]));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6]));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7]));
		//navDrawerItems.add(new NavDrawerItem(navMenuTitles[8]));


		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(Constants.NAVITEM);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
        Fragment fragment = null;
		switch (position) {
			/*case 0:
				fragment = new HomeFragment();
				break;*/
		case 0:
			fragment = new InwardListFragment();
			break;
		case 1:
			fragment = new DeliveryStockFragment();
			break;

			case 2:
				fragment = new DamagesFragment();
				break;

			case 3:
				fragment = new VariableFragment();
				break;

			case 4:
				fragment = new NonMovingProductSettingsFragment();
				break;
			case 5:
				fragment = new ProductListFragment();
				break;
		case 6:
			fragment = new ReportsFragment();
			break;


		}

		if (fragment != null) {
			getFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("frag").commit();
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}


	@Override
	public Dialog onCreateDialog(int id) {
		switch (id) {
			case DATE_PICKER_ID:

				// open datepicker dialog.
				// set date picker for current date
				// add pickerListener listner to date picker
				return new DatePickerDialog(this, pickerListener, year, month,day);

		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker view, int selectedYear,
							  int selectedMonth, int selectedDay) {

			year  = selectedYear;
			month = selectedMonth;
			day   = selectedDay;

			// Show selected date

			//if(  Constants.dateID==100) {
			Constants.strDeliveryStockDate=""+new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" ");

				Constants.strInwardDate=""+new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" ");
				HomeActivity.btn_Date.setText(Constants.strInwardDate);
			/*}else if(  Constants.dateID==200) {
				Constants.strDeliveryStockDate=""+new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" ");

				DeliveryStockFragment.et_Date.setText(Constants.strDeliveryStockDate);
			}*/



		}
	};




}
