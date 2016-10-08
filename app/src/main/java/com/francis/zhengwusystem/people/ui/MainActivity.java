package com.francis.zhengwusystem.people.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.people.presenter.MainPresenter;
import com.francis.zhengwusystem.people.presenter.MainPresenterImpl;
import com.francis.zhengwusystem.people.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView{

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private Toolbar mToolbar;
	private NavigationView mNavigationView;
	private MainPresenter mMainPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
				R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
		setupDrawerContent(mNavigationView);

		mMainPresenter = new MainPresenterImpl(this);

		switch2News();
	}

	private void setupDrawerContent(NavigationView navigationView) {
		navigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(MenuItem menuItem) {
						mMainPresenter.switchNavigation(menuItem.getItemId());
						menuItem.setChecked(true);
						mDrawerLayout.closeDrawers();
						return true;
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if(id == R.id.action_settings){
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void switch2News() {

	}

	@Override
	public void switch2Suggestion() {

	}

	@Override
	public void switch2PersonalInfo() {

	}

	@Override
	public void switch2Setting() {

	}

}
