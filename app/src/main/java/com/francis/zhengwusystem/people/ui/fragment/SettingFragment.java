package com.francis.zhengwusystem.people.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.people.ui.activity.PeopleChangeInfoActivity;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-08-2016
 */

public class SettingFragment extends Fragment {


	private Button tvChangeInfo, tvReply;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_people_setting, null);
		tvChangeInfo = (Button) view.findViewById(R.id.tvChangeInfo);
		tvReply = (Button) view.findViewById(R.id.tvReply);

		tvChangeInfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), PeopleChangeInfoActivity.class));
			}
		});

		tvReply.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		return view;
	}
}
