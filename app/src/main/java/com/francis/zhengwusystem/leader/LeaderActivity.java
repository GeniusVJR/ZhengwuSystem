package com.francis.zhengwusystem.leader;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.leader.presenter.MainPresenter;
import com.francis.zhengwusystem.leader.presenter.MainPresenterImpl;
import com.francis.zhengwusystem.leader.ui.fragment.NewsFragment;
import com.francis.zhengwusystem.leader.ui.fragment.PersonalInfoFragment;
import com.francis.zhengwusystem.leader.ui.fragment.SettingFragment;
import com.francis.zhengwusystem.leader.ui.fragment.SuggestionFragment;
import com.francis.zhengwusystem.leader.view.MainView;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-11-2016
 */

public class LeaderActivity extends AppCompatActivity implements MainView {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private Toolbar mToolbar;
	private NavigationView mNavigationView;
	private MainPresenter mMainPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leader);
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
		getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new NewsFragment()).commit();
		mToolbar.setTitle("发布政务");

	}

	@Override
	public void switch2Suggestion() {

		getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SuggestionFragment()).commit();
		mToolbar.setTitle("回复意见");
	}

	@Override
	public void switch2PersonalInfo() {
		getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new PersonalInfoFragment()).commit();
		mToolbar.setTitle("查询信息");
	}

	@Override
	public void switch2Setting() {

		getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SettingFragment()).commit();
		mToolbar.setTitle("个人设置");

	}
}
