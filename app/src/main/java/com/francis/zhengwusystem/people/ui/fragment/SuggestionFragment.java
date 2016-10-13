package com.francis.zhengwusystem.people.ui.fragment;

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
import com.francis.zhengwusystem.model.Suggestion;
import com.francis.zhengwusystem.model.Zhengwu;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-08-2016
 */

public class SuggestionFragment extends Fragment {

	private EditText etContent;
	private Button btnNews;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_people_suggestion, null);
		etContent = (EditText) view.findViewById(R.id.etContent);
		btnNews = (Button) view.findViewById(R.id.btnNews);

		btnNews.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Suggestion suggestion = new Suggestion();
				suggestion.setContent(etContent.getText().toString());
				suggestion.save(getActivity(), new SaveListener() {

					@Override
					public void onSuccess() {
						Toast.makeText(getActivity(), "意见反馈成功", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(int i, String s) {
						Toast.makeText(getActivity(), "意见反馈失败", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});

		return view;
	}
}
