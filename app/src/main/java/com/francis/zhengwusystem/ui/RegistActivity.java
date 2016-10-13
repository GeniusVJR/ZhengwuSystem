package com.francis.zhengwusystem.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.model.User;
import com.francis.zhengwusystem.utils.BlackInputFilter;
import com.francis.zhengwusystem.utils.NumberAndLetterInputFilter;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-02-2016
 */

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

	private FloatingActionButton fab;
	private CardView cvAdd;
	private EditText et_username, et_password, et_repeatpassword;
	private Button bt_go;
	private RadioGroup mRadioGroup;
	User user = new User();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//第一：默认初始化
		Bmob.initialize(this, "8ad16864d4ca92c235df1b78980a5a7f");

		setContentView(R.layout.activity_register);

		fab = (FloatingActionButton) findViewById(R.id.fab);
		cvAdd = (CardView) findViewById(R.id.cv_add);

		//用户名以及用户黑名单
		et_username = (EditText) findViewById(R.id.et_username);
		BlackInputFilter filter = new BlackInputFilter(new char[]{'<', '>', '/'});
		et_username.setFilters(new BlackInputFilter[] {filter});

		//密码
		et_password = (EditText) findViewById(R.id.et_password);
		et_repeatpassword = (EditText) findViewById(R.id.et_repeatpassword);
		et_password.setFilters(new NumberAndLetterInputFilter[]{ new NumberAndLetterInputFilter()});

		bt_go = (Button) findViewById(R.id.bt_go);

		mRadioGroup = (RadioGroup) findViewById(R.id.rg);

		mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == 0){
					user.setIdentity(0);
				}else {
					user.setIdentity(1);
				}

			}
		});

		bt_go.setOnClickListener(this);

		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
			ShowEnterAnimation();
		}

		fab.setOnClickListener(new View.OnClickListener() {

			@RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)
			@Override
			public void onClick(View v) {
				animateRevealClose();
			}
		});

	}

	@RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)
	private void ShowEnterAnimation() {
		Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
		getWindow().setSharedElementEnterTransition(transition);

		transition.addListener(new Transition.TransitionListener() {
			@Override
			public void onTransitionStart(Transition transition) {
				cvAdd.setVisibility(View.GONE);
			}

			@Override
			public void onTransitionEnd(Transition transition) {
				transition.removeListener(this);
				animateRevealShow();
			}

			@Override
			public void onTransitionCancel(Transition transition) {

			}

			@Override
			public void onTransitionPause(Transition transition) {

			}

			@Override
			public void onTransitionResume(Transition transition) {

			}


		});
	}

	@RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)
	public void animateRevealShow() {
		Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
		mAnimator.setDuration(500);
		mAnimator.setInterpolator(new AccelerateInterpolator());
		mAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
			}

			@Override
			public void onAnimationStart(Animator animation) {
				cvAdd.setVisibility(View.VISIBLE);
				super.onAnimationStart(animation);
			}
		});
		mAnimator.start();
	}

	@RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)
	public void animateRevealClose() {
		Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
		mAnimator.setDuration(500);
		mAnimator.setInterpolator(new AccelerateInterpolator());
		mAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				cvAdd.setVisibility(View.INVISIBLE);
				super.onAnimationEnd(animation);
				fab.setImageResource(R.drawable.plus);
				RegistActivity.super.onBackPressed();
			}

			@Override
			public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
			}
		});
		mAnimator.start();
	}
	@RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void onBackPressed() {
		animateRevealClose();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.bt_go:
				signUp();
				break;
		}
	}

	private void signUp() {
		String userName = et_username.getText().toString();
		String password = et_password.getText().toString();
		String passwordRepeat = et_repeatpassword.getText().toString();

		if(password == null || password.length() < 4){
			Toast.makeText(getApplication(), "您输入的密码长度太短，请重新输入。", Toast.LENGTH_LONG).show();
			return;
		}

		if(password.length() > 16){
			Toast.makeText(getApplication(), "您输入的密码长度大于16位，请重新输入。", Toast.LENGTH_LONG).show();
			return;
		}

		if(password.equals(passwordRepeat)){


			user.setUsername(userName);
			user.setPassword(password);
			user.signUp(RegistActivity.this, new SaveListener() {

				@Override
				public void onSuccess() {
					Toast.makeText(RegistActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
					Intent backLogin = new Intent(RegistActivity.this,
							LoginActivity.class);
					startActivity(backLogin);
					RegistActivity.this.finish();
				}

				@Override
				public void onFailure(int i, String s) {
					Toast.makeText(RegistActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
				}
			});
		}else{
			Toast.makeText(this, "两次输入的密码不相同！", Toast.LENGTH_SHORT).show();
		}
	}
}
