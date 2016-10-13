package com.francis.zhengwusystem.leader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.model.Zhengwu;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-08-2016
 *
 * 发布政务
 */

public class NewsFragment extends Fragment {

	private EditText etContent;
	private Button btnNews;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_leader_news, null);
		etContent = (EditText) view.findViewById(R.id.etContent);
		btnNews = (Button) view.findViewById(R.id.btnNews);

		btnNews.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Zhengwu zhengwu = new Zhengwu();
				zhengwu.setContent(etContent.getText().toString());
				zhengwu.save(getActivity(), new SaveListener() {

					@Override
					public void onSuccess() {
						Toast.makeText(getActivity(), "政务发布成功", Toast.LENGTH_SHORT).show();
						etContent.setText("");
					}

					@Override
					public void onFailure(int i, String s) {
						Toast.makeText(getActivity(), "政务发布失败", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});

		return view;

	}
}
