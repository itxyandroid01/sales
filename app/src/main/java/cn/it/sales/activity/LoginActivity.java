package cn.it.sales.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.it.sales.R;
import cn.it.sales.application.MyDebug;
import cn.it.sales.bean.User;
import cn.it.sales.dao.LoginDao;

public class LoginActivity extends BaseActivity {
    String mUsername, mPassWord;
    ArrayList<String> mIsEmptylogin;
    Context mContext;
    EditText mEditTextUserName, mEditTextPassWord;
    User mUser=new User();
    LoginDao mLoginDao=new LoginDao();
    RadioGroup mRadioGroup;
    RadioButton mRadioButtonXiaoShou,mRadioButtonZhuGuan,mRadioButtonKuGuan;
    int mRadioGroupId=1;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;

        initEditText();
        //选择职位
       initRadioGroup();
        //跳转登录界面
        initImageView1();
        //跳转注册界面
        initImageView2();

    }

    private void initRadioGroup() {
        mRadioGroup = (RadioGroup) findViewById(R.id.RadioGroup1);
        mRadioButtonXiaoShou = (RadioButton) findViewById(R.id.XiaoShou);
        mRadioButtonZhuGuan = (RadioButton) findViewById(R.id.ZhuGuan);
        mRadioButtonKuGuan = (RadioButton) findViewById(R.id.KuGuan);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRadioButtonXiaoShou.getId()) {
                    mRadioGroupId = 1;
                }
                if (checkedId == mRadioButtonZhuGuan.getId()) {
                    mRadioGroupId = 2;
                }
                if (checkedId == mRadioButtonKuGuan.getId()) {
                    mRadioGroupId = 3;
                }
            }
        });
    }

    private void initImageView1() {
        ImageView imageView= (ImageView) findViewById(R.id.bt_lon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    private void initImageView2() {
        ImageView imageView= (ImageView) findViewById(R.id.bt_login);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MyDebug.DEMO_TiJiaoZhuCe) {
                    getInputMessage();
                    if (mUsername.isEmpty() || mPassWord.isEmpty()) {
                        if (mUsername.isEmpty()) {
                            mIsEmptylogin.add("用户名不能为空");
                        }
                        if (mPassWord.isEmpty()) {
                            mIsEmptylogin.add("密码不能为空");
                        }
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < mIsEmptylogin.size(); i++) {
                            stringBuffer.append(mIsEmptylogin.get(i) + "\t");
                        }
                        String line2 = stringBuffer.toString();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("温馨提示");
                        builder.setMessage(line2);
                        DialogInterface.OnClickListener listener;
                        listener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        };
                        builder.setNegativeButton("取消", listener);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }

                    mUser= mLoginDao.loginMessage(LoginActivity.this);
                    String userName=mUser.getUserName();
                    String password=mUser.getPassWord();
                    if (!mUsername.equals(userName)) {
                        Toast.makeText(LoginActivity.this, "无此用户", Toast.LENGTH_SHORT).show();
                    }

                    if (!mPassWord.equals(password) && mPassWord.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                    if (mUsername.equals(userName) && mPassWord.equals(password) && !mUsername.isEmpty() && !mPassWord.isEmpty()) {
                        switch (mRadioGroupId) {
                            case 1:
                                Intent intent = new Intent(LoginActivity.this, SalesmanActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                Intent intent1 = new Intent(LoginActivity.this, GovernorActivity.class);
                                startActivity(intent1);
                                break;
                            case 3:
                                Intent intent2=new Intent(LoginActivity.this,WarehouseActivity.class);
                                startActivity(intent2);
                                break;
                        }
                    }
                }
            }
        });
    }

    private void getInputMessage() {
        mIsEmptylogin = new ArrayList<String>();
        mUsername = mEditTextUserName.getText().toString();
        mPassWord = mEditTextPassWord.getText().toString();
    }

    private void initEditText() {
        mEditTextUserName = (EditText) findViewById(R.id.editText1);
        mEditTextPassWord = (EditText) findViewById(R.id.editText2);
    }
}
