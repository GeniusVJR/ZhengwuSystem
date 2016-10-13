package com.francis.zhengwusystem.people.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.leader.activity.SuggestionDetailActivity;
import com.francis.zhengwusystem.leader.adapter.LeaderSuggestionAdapter;
import com.francis.zhengwusystem.model.Zhengwu;
import com.francis.zhengwusystem.people.adapter.PeopleNewsAdapter;
import com.francis.zhengwusystem.people.ui.activity.NewsDetailActivity;
import java.util.List;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-08-2016
 */

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	private SwipeRefreshLayout mSwipeRefreshWidget;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;
	private PeopleNewsAdapter adapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_people_news, null);
		mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
		mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
				R.color.primary_dark, R.color.primary_light,
				R.color.accent);
		mSwipeRefreshWidget.setOnRefreshListener(this);

		mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);

		mLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLayoutManager);

		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		getData();
		adapter = new PeopleNewsAdapter(getActivity().getApplicationContext());
		mRecyclerView.setAdapter(adapter);

		adapter.setOnItemClickListener(new PeopleNewsAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(getActivity(), "跳转到详情页", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}
		});
		onRefresh();
		return view;
	}

	private void getData() {
		BmobQuery<Zhengwu> query = new BmobQuery<>();
		query.setLimit(50);
		query.findObjects(getActivity(), new FindListener<Zhengwu>() {

			@Override
			public void onSuccess(List<Zhengwu> list) {

				Toast.makeText(getActivity(), "查询成功, 共" + list.size() + "条数据", Toast.LENGTH_SHORT).show();
				adapter.setData(list);

			}

			@Override
			public void onError(int i, String s) {

			}
		});
	}

	@Override
	public void onRefresh() {

	}


}
