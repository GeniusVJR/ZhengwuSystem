package com.francis.zhengwusystem.people.ui.activity;

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
import com.francis.zhengwusystem.model.Zhengwu;
import java.util.List;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-12-2016
 */

public class NewsDetailActivity extends AppCompatActivity {

	private TextView tvCommand;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		tvCommand = (TextView) findViewById(R.id.tvCommand);
		Intent intent = getIntent();

		final int position = intent.getIntExtra("position", 0);

		BmobQuery<Zhengwu> query = new BmobQuery<>();
		query.setLimit(50);
		query.findObjects(this, new FindListener<Zhengwu>() {

			@Override
			public void onSuccess(List<Zhengwu> list) {
				String content = list.get(position).getContent().toString();
				tvCommand.setText(content);
			}

			@Override
			public void onError(int i, String s) {

			}
		});
	}
}
