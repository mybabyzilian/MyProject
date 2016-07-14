package com.example.dllo.chanyouji.load;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.personal.PersonalActivity;
import com.example.dllo.chanyouji.user_detail.UserActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/6.
 */
public class LoadFragment extends BaseFragment {

    private EditText numberEt;
    private EditText passwordEt;
    private Button button;

    @Override
    public int setLayout() {
        return R.layout.load_fragment;
    }

    @Override
    public void initView(View view) {

        numberEt = (EditText) view.findViewById(R.id.user_number);
        passwordEt = (EditText) view.findViewById(R.id.password);
        button = (Button) view.findViewById(R.id.login_btn);

    }

    @Override
    public void initData() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = BmobUser.getCurrentUser(context);

                    bmobUser = new BmobUser();
                    bmobUser.setUsername(numberEt.getText().toString());
                    bmobUser.setPassword(passwordEt.getText().toString());

                    bmobUser.login(context, new SaveListener() {
                        @Override
                        public void onSuccess() {


                            SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("user",1);
                            editor.putString("userName",numberEt.getText().toString());
                            editor.commit();
                            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, PersonalActivity.class);

                            startActivity(intent);
                            getActivity().finish();


                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(context, "账号或密码不正确", Toast.LENGTH_SHORT).show();
                        }
                    });


            }
        });



    }
}
