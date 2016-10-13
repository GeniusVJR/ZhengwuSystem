package com.francis.zhengwusystem.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-11-2016
 */

public class NetworkUtils {

	//判断网络是否连接
	public static boolean isNetworkConnected(Context context)
	{
		if(context != null)
		{
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if(mNetworkInfo != null)
			{
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

}
