package com.francis.zhengwusystem.leader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.model.Suggestion;
import java.util.List;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-12-2016
 */

public class SuggestionDetailActivity extends AppCompatActivity {

	private TextView tvCommand;
	private Button btnSuggest;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggestion_detail);
		tvCommand = (TextView) findViewById(R.id.tvCommand);
		Intent intent = getIntent();

		final int position = intent.getIntExtra("position", 0);

		BmobQuery<Suggestion> query = new BmobQuery<>();
		query.setLimit(50);
		query.findObjects(this, new FindListener<Suggestion>() {

			@Override
			public void onSuccess(List<Suggestion> list) {
				String content = list.get(position).getContent().toString();
				tvCommand.setText(content);
			}

			@Override
			public void onError(int i, String s) {

			}
		});

		btnSuggest = (Button) findViewById(R.id.btnSuggest);
		btnSuggest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}


}
