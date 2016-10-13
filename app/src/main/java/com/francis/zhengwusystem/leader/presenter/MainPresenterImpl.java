package com.francis.zhengwusystem.leader.presenter;

import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.leader.view.MainView;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-08-2016
 */

public class MainPresenterImpl implements MainPresenter {

	private MainView mMainView;

	public MainPresenterImpl(MainView mainView) {
		mMainView = mainView;
	}

	@Override
	public void switchNavigation(int id) {
		switch (id) {
			case R.id.navigation_item_news:
				mMainView.switch2News();
				break;
			case R.id.navigation_item_suggestion:
				mMainView.switch2Suggestion();
				break;
			case R.id.navigation_item_personal_info:
				mMainView.switch2PersonalInfo();
				break;
			case R.id.navigation_item_setting:
				mMainView.switch2Setting();
				break;
			default:
				mMainView.switch2News();
				break;
		}
	}
}
