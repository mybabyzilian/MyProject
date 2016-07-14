package com.example.dllo.chanyouji.load;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.user_detail.UserActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/6.
 */
public class RegisterFragment extends BaseFragment {
    private EditText userNameEt;
    private EditText userPassword;
    private ImageView okIv;

    @Override
    public int setLayout() {
        return R.layout.register_fragment;
    }

    @Override
    public void initView(View view) {
        userPassword = (EditText) view.findViewById(R.id.register_password);
        userNameEt = (EditText) view.findViewById(R.id.register_number);
        okIv = (ImageView) getActivity().findViewById(R.id.ok);

    }

    @Override
    public void initData() {
        okIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BmobUser bmobUser = BmobUser.getCurrentUser(context);

                    bmobUser = new BmobUser();
                    bmobUser.setUsername(userNameEt.getText().toString());
                    bmobUser.setPassword(userPassword.getText().toString());
                    bmobUser.signUp(context, new SaveListener() {
                        @Override
                        public void onSuccess() {

                            Toast.makeText(context, "注册成功,请登录", Toast.LENGTH_SHORT).show();

                            userNameEt.setText("");
                            userPassword.setText("");

                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });


                }


        });


    }
}
