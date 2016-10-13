package com.francis.zhengwusystem.people.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;
import com.francis.zhengwusystem.R;
import com.francis.zhengwusystem.model.User;

import static android.R.attr.password;

/**
 * @author taoc @ Zhihu Inc.
 * @since 10-12-2016
 */

public class PeopleChangeInfoActivity extends AppCompatActivity {


	private EditText etName, etPassword, etPhone;
	private Button btnChange;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_change_info);
		etName = (EditText) findViewById(R.id.etName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etPhone = (EditText) findViewById(R.id.etPhone);

		btnChange = (Button) findViewById(R.id.btnChange);




		btnChange.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				//Log.d("TAG", name + "  " + password + "  " + phone);
				String name = etName.getText().toString();
				String password = etPassword.getText().toString();
				String phone = etPhone.getText().toString();

				User user = new User();
				user.setUsername(name);
				user.setMobilePhoneNumber(phone);
				user.setPassword(password);
				user.update(PeopleChangeInfoActivity.this, BmobUser.getCurrentUser(PeopleChangeInfoActivity.this).getObjectId(), new UpdateListener() {

					@Override
					public void onSuccess() {
						Toast.makeText(PeopleChangeInfoActivity.this, "更新信息成功", Toast.LENGTH_SHORT).show();
						finish();
					}

					@Override
					public void onFailure(int i, String s) {
						Toast.makeText(PeopleChangeInfoActivity.this, "更新信息失败", Toast.LENGTH_SHORT).show();
					}
				});
			}


		});



	}
}
