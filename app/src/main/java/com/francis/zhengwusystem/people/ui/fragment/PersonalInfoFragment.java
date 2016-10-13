package com.francis.zhengwusystem.people.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.model.User;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-08-2016
 */

public class PersonalInfoFragment extends Fragment {

	private TextView tvId, tvUserName, tvPassword, tvPhone;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_people_info, null);
		tvId = (TextView) view.findViewById(R.id.tvId);
		tvUserName = (TextView) view.findViewById(R.id.tvUserName);
		tvPassword = (TextView) view.findViewById(R.id.tvPassword);
		tvPhone = (TextView) view.findViewById(R.id.tvPhone);

		BmobQuery<User> query = new BmobQuery<>();
		query.getObject(getActivity(), BmobUser.getCurrentUser(getActivity()).getObjectId(), new GetListener<User>() {

			@Override
			public void onSuccess(User user) {
				if (tvId != null) {
					tvId.setText(User.getCurrentUser(getActivity()).getObjectId().toString());
				} else {
					tvId.setText("请先在设置里面填写");
				}
				if (tvUserName != null) {
					tvUserName.setText(User.getCurrentUser(getActivity()).getUsername().toString());
				} else {
					tvUserName.setText("请先在设置里面填写");
				}
				tvPassword.setText("密码暂不显示");
				if(tvPhone != null){
					tvPhone.setText(User.getCurrentUser(getActivity()).getMobilePhoneNumber().toString());
				}else {
					tvPhone.setText("请先在设置里面填写");
				}

			}

			@Override
			public void onFailure(int i, String s) {

				Toast.makeText(getActivity(), "显示失败", Toast.LENGTH_SHORT).show();
			}
		});

		return view;
	}
}
