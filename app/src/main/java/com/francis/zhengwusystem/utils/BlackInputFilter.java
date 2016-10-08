package com.francis.zhengwusystem.utils;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-08-2016
 *
 *
 * 不允许输入指定的字符
 */

public class BlackInputFilter implements InputFilter {

	private char[] blackList;

	public BlackInputFilter(char[] blackList) {
		this.blackList = blackList;
	}

	@Override
	public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

		if(isContainBlackChar(source)){
			//拦截
			return "";
		}
		return null;
	}

	/**
	 * 判断这个字符串是否包含黑名单中的字符
	 * @param source
	 * @return
	 */
	private boolean isContainBlackChar(CharSequence source){
		int len = blackList.length;
		String temp = source.toString();

		for(int i = 0; i < len; i++){
			if(temp.contains(blackList[i] + "")){
				return true;
			}
		}
		return false;
	}
}
