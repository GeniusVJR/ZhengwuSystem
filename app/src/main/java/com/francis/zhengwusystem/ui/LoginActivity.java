package com.francis.zhengwusystem.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import cn.bmob.push.lib.util.NetworkUtil;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import com.francis.zhengwusystem.leader.LeaderActivity;
import com.francis.zhengwusystem.model.User;
import com.francis.zhengwusystem.people.ui.MainActivity;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.utils.NetworkUtils;

import static com.francis.zhengwusystem.R.id.et_username;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-02-2016
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

	EditText etUsername;
	EditText etPassword;
	Button btGo;
	CardView cv;
	FloatingActionButton fab;
	private RadioGroup mRadioGroup;
	User user = new User();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//第一：默认初始化
		Bmob.initialize(this, "8ad16864d4ca92c235df1b78980a5a7f");
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		etUsername = (EditText) findViewById(et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		btGo = (Button) findViewById(R.id.bt_go);
		cv = (CardView) findViewById(R.id.cv);
		fab = (FloatingActionButton) findViewById(R.id.fab);
		mRadioGroup = (RadioGroup) findViewById(R.id.rg);
		mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == 0){
					user.setIdentity(0);
				}else {
					user.setIdentity(1);
				}
			}
		});
		btGo.setOnClickListener(this);
		fab.setOnClickListener(this);
	}

	@RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.fab:
				getWindow().setExitTransition(null);
				getWindow().setEnterTransition(null);

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					ActivityOptions options =
							ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
					startActivity(new Intent(this, RegistActivity.class), options.toBundle());
				} else {
					startActivity(new Intent(this, RegistActivity.class));
				}
				break;
			case R.id.bt_go:
				Explode explode = new Explode();
				explode.setDuration(500);

				getWindow().setExitTransition(explode);
				getWindow().setEnterTransition(explode);

				String userName = etUsername.getText().toString();
				String password = etPassword.getText().toString();
				logIn(userName, password);
				break;

		}
	}

	private void logIn(final String userName, String password) {
		if(!NetworkUtils.isNetworkConnected(this))
		{
			Toast.makeText(LoginActivity.this, "请检查网络状态", Toast.LENGTH_SHORT).show();
		}
		else if(userName.equals("")||password.equals(""))
		{
			Toast.makeText(LoginActivity.this, "请正确输入账号密码", Toast.LENGTH_SHORT).show();
		}
		else
		{
			user.setUsername(userName);
			user.setPassword(password);
			user.login(this, new SaveListener() {
				@Override
				public void onSuccess() {

					//保存用户信息
					//saveUserInfo(userName, password);
					//跳转到主页面
					Log.d("TAG", String.valueOf(user.getIdentity()));
					if(user.getIdentity() == 0){
						Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
						Intent toHome = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(toHome);
						LoginActivity.this.finish();
					}else if(user.getIdentity() == 1){
						Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
						Intent toHome = new Intent(LoginActivity.this, LeaderActivity.class);
						startActivity(toHome);
						LoginActivity.this.finish();
					}

				}

				@Override
				public void onFailure(int i, String s) {
					Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
}
