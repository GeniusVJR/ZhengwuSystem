package com.francis.zhengwusystem.leader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.francis.zhengwusystem.R;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-08-2016
 */

public class SettingFragment extends Fragment {


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_leader_setting, null);
		return view;

	}
}
