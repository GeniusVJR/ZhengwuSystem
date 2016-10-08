package com.francis.zhengwusystem.utils;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.logging.Filter;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-08-2016
 *
 *
 * 只允许输入指定的字符
 */

public class NumberAndLetterInputFilter implements InputFilter {

	@Override
	public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
		if(!source.toString().matches("[a-zA-Z0-9_-]*")){
			return "";
		}
		return null;
	}
}
