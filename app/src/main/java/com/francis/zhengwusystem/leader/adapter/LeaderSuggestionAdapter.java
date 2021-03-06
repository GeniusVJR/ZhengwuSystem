package com.francis.zhengwusystem.leader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.model.Suggestion;
import com.francis.zhengwusystem.model.Zhengwu;
import java.util.List;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-12-2016
 */

public class LeaderSuggestionAdapter extends RecyclerView.Adapter<LeaderSuggestionAdapter.ItemViewHolder>{

	private List<Suggestion> mData;
	private Context mContext;

	private OnItemClickListener mOnItemClickListener;

	public LeaderSuggestionAdapter(Context context) {
		mContext = context;
	}

	public void setData(List<Suggestion> data){
		this.mData = data;
		this.notifyDataSetChanged();
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.mOnItemClickListener = onItemClickListener;
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.item_people_news, parent, false
		);
		ItemViewHolder vh = new ItemViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ItemViewHolder holder, int position) {
		Suggestion suggestion = mData.get(position);
		if(suggestion == null) {
			return;
		}
		holder.mContent.setText(suggestion.getContent());
	}

	@Override
	public int getItemCount() {
		if(mData == null) {
			return 0;
		}
		return mData.size();
	}

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}


	public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		public TextView mContent;

		public ItemViewHolder(View v) {
			super(v);
			mContent = (TextView) v.findViewById(R.id.tvContent);
			v.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			if(mOnItemClickListener != null) {
				mOnItemClickListener.onItemClick(view, this.getPosition());
			}
		}
	}
}
