package cn.it.sales.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.it.sales.R;
import cn.it.sales.Service.SalesBinder;
import cn.it.sales.application.MyDebug;
import cn.it.sales.bean.ResultUser;
import cn.it.sales.bean.User;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {
    String mUsername, mPassWord;
    ArrayList<String> mIsEmptylogin;
    Context mContext;
    EditText mEditTextUserName, mEditTextPassWord;
    //    TextView mTextView;
    SharedPreferences mSharedPreferences;
    RegisterActivity mRegisterActivitym=new RegisterActivity();
    ServiceConnection mServiceConnection;
    SalesBinder mSalesBinder;
       RadioGroup mRadioGroup;
    ProgressBar mProgressBar;
    RadioButton mRadioButtonXiaoShou,mRadioButtonZhuGuan,mRadioButtonKuGuan;
    int mRadioGroupId=1;

    ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onResume() {
        EventBus.getDefault().register(MainActivity.this);
        super.onResume();
    }

    @Override
    protected void onStop() {
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
            mServiceConnection = null;
        }
        EventBus.getDefault().unregister(MainActivity.this);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
//       mProgressBar= (ProgressBar) findViewById(R.id.progreeBar1);
//        mProgressBar.setVisibility(View.INVISIBLE);
        initEditText();
        //选择职位
       initRadioGroup();
        //跳转登录界面
        initButton1();
        //跳转注册界面
        initButton2();
        //清除编辑框的内容，重新输入
        initButton3();
//        init();
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

    private void initButton3() {
        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextUserName.setText("");
                mEditTextPassWord.setText("");
            }
        });
    }

//    private void init() {
//        Intent intent = getIntent();
//        int loginstatus = intent.getIntExtra("loginstatus", -2);
//        if (loginstatus == -1) {
////            mTextView = (TextView) findViewById(R.id.message);
////            mTextView.setVisibility(View.VISIBLE);
////            mTextView.setText(intent.getStringExtra("message"));
//        }
//    }

    private void initButton2() {
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    private void initButton1() {
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (MyDebug.Demo_classify) {
//                    Intent intent = new Intent(MainActivity.this, SalesmanActivity.class);
//                    startActivity(intent);
//                } else {
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
                    mSharedPreferences=getSharedPreferences(mRegisterActivitym.SHARED_NANE, Context.MODE_PRIVATE);
                    String username=mSharedPreferences.getString("username", "");
                    String password=mSharedPreferences.getString("password", "");
                    if(!mUsername.equals(username)){
                        Toast.makeText(MainActivity.this, "无此用户", Toast.LENGTH_SHORT).show();
                    }
                    if(!mPassWord.equals(password)){
                        Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }

                    if(mUsername.equals(username)&&mPassWord.equals(password)){
                        switch (mRadioGroupId){
                            case 1:
                                Intent intent=new Intent(MainActivity.this,SalesmanActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                Intent intent1=new Intent(MainActivity.this,GovernorActivity.class);
                                startActivity(intent1);
                                break;
                            case 3:
                                Intent intent2=new Intent(MainActivity.this,WarehouseActivity.class);
                                startActivity(intent2);
                                break;
                        }

                    }
                }
            }
                //如果输入有效
//                    if (mIsEmptylogin.size() == 0) {
//                        Intent intent = new Intent(MainActivity.this, MyService.class);
//                        mServiceConnection = new ServiceConnection() {
//                            @Override
//                            public void onServiceConnected(ComponentName name, IBinder service) {
//                                mSalesBinder = (SalesBinder) service;
//                                selectUserNameAndPassword(mUsername, mPassWord);
//                            }
//
//                            @Override
//                            public void onServiceDisconnected(ComponentName name) {
//
//                            }
//                        };
//                        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
//
//                    }

        });
    }




    public void onEventMainThread(ResultUser event) {
        int code = event.getResult();
        if (code == 1) {
            long groupid = event.getGroupid();
            ResultUser resultUser = new ResultUser();
            resultUser.setUsername(mUsername);
            resultUser.setPassword(mPassWord);
            resultUser.setGroupid(groupid);
//            UserManager userManager=new UserManager();
//            userManager.resultToSharedPreference(resultUser);
            if (groupid == 1) {

                Intent intent = new Intent(MainActivity.this, SalesmanActivity.class);
                startActivity(intent);

            }
            if (groupid == 2) {
                Intent intent = new Intent(MainActivity.this, GovernorActivity.class);
                startActivity(intent);
            }
            if (groupid == 3) {
                Intent intent = new Intent(MainActivity.this, WarehouseActivity.class);
                startActivity(intent);
            }
        }
        if (code == -1) {
//            mTextView.setVisibility(View.VISIBLE);
//            mTextView.setText(event.getMessage());
            Toast.makeText(MainActivity.this, event.getMessage(), Toast.LENGTH_LONG).show();
        }
        if (code == -2) {
//            mTextView.setText(View.VISIBLE);
//            mTextView.setText(event.getMessage());
            Toast.makeText(MainActivity.this, event.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void selectUserNameAndPassword(String username, String passWord) {
        User user = new User();
        user.setUserName(username);
        user.setPassWord(passWord);
        mSalesBinder.selectUserNameAndPassword(user);
    }

    private void getInputMessage() {
        mIsEmptylogin = new ArrayList<String>();
        mUsername = mEditTextUserName.getText().toString();
        mPassWord = mEditTextPassWord.getText().toString();
    }

    private void initEditText() {
        mEditTextUserName = (EditText) findViewById(R.id.editText1);
        mEditTextPassWord = (EditText) findViewById(R.id.editText2);
//        mTextView= (TextView) findViewById(R.id.lodin_success_show);
    }
}
