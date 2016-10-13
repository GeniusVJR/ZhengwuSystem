package com.francis.zhengwusystem.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-10-2016
 */

public class User extends BmobUser {

	public static boolean isLogin = false;

	private String userSays;
	private String name;

	/**
	 * 0 表示民众, 1 表示领导
	 */
	private int identity;

	public String getUserSays() {
		return userSays;
	}

	public void setUserSays(String userSays) {
		this.userSays = userSays;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}
}
