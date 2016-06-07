package cn.it.sales.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cn.it.sales.R;
import cn.it.sales.Result.MyResult;
import cn.it.sales.Service.MyService;
import cn.it.sales.Service.SalesBinder;
import cn.it.sales.application.MyApplication;
import cn.it.sales.application.MyDebug;
import cn.it.sales.bean.ResultUser;
import cn.it.sales.bean.User;
import cn.it.sales.bll.UserManager;
import cn.it.sales.dao.LoginDao;
import de.greenrobot.event.EventBus;

public class RegisterActivity extends BaseActivity {
    ArrayList<String> mIsEmpty;
    String mUserName, mPassword, mPassword2, mName, mPhone;
    Spinner mSpinner;
    String[] mJob = {"选择职位", "销售", "主管", "库管"};
    User mUser= MyApplication.getUser();
    SalesBinder mBinder;
    LoginDao mLoginDao=new LoginDao();
    ServiceConnection mServiceConnection = null;
    int mPosition;
    long mSelectjob;


    //用户名
    EditText mEditTextUserName;
    //密码
    EditText mEditTextPassword;
    //再次输入密码
    EditText mEditTextPassword2;
    //姓名
    EditText mEditTextName;
    //手机号
    EditText mEditTextPhone;

    ArrayAdapter<String> mAdapter;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerEventBus();
        mContext = this;
        //注册
        initButtonResister();
        initEditText();
        initButton();
        initSpinner();
        initBinder();
    }

    private void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    private void initBinder() {
        Intent intent = new Intent(RegisterActivity.this, MyService.class);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mBinder = (SalesBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        this.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void initSpinner() {
        mSpinner = (Spinner) findViewById(R.id.spinner1);
        mAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mJob);
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                setJob(position);
                mPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setJob(int position) {
        if (position == 1) {
            mSelectjob = 1;
        }
        if (position == 2) {
            mSelectjob = 2;
        }
        if (position == 3) {
            mSelectjob = 3;
        }
    }

    private void initButtonResister() {
        Button button = (Button) findViewById(R.id.buttonRegistSubmit);
        if (MyDebug.DEMO_TiJiaoZhuCe) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = mEditTextUserName.getText().toString();
                    String pass = mEditTextPassword.getText().toString();
                    String pass2 = mEditTextPassword2.getText().toString();
                    String niCheng = mEditTextName.getText().toString();
                    String phone = mEditTextPhone.getText().toString();
                    mUser = new User(name, pass, niCheng, phone, mSelectjob);
                    mBinder.upJson(mUser);
                }
            });
        } else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getEditTextInfo();
                    if (mUserName.isEmpty() || mPassword.isEmpty() || mPassword2.isEmpty() || mName.isEmpty() || mPhone.isEmpty()) {
                        if (mUserName.isEmpty()) {
                            mIsEmpty.add("用户名不能为空");
                        }
                        if (mPassword.isEmpty()) {

                            mIsEmpty.add("密码不能为空");
                        }
                        if (mPassword2.isEmpty()) {


                            mIsEmpty.add("请确认密码");
                        }

                        if (mName.isEmpty()) {

                            mIsEmpty.add("姓名不能为空");
                        }
                        if (mPhone.isEmpty()) {

                            mIsEmpty.add("手机号不能为空");
                        }
                        //把这些错误信息加入到一个数组中
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < mIsEmpty.size(); i++) {
                            stringBuffer.append(mIsEmpty.get(i) + "\t");
                        }
                        //创建一个对话框来显示错误信息
                        String line2 = stringBuffer.toString();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("温馨提醒");
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
                    } else if (!mPassword.equals(mPassword2)) {
                        android.app.AlertDialog.Builder builder = new
                                android.app.AlertDialog.Builder(mContext);
                        builder.setTitle("温馨提醒");
                        builder.setMessage("两次输入的密码不一致");
                        DialogInterface.OnClickListener listener;
                        listener = new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        };
                        builder.setNegativeButton("取消", listener);
                        android.app.AlertDialog alertDialog = builder.create();

                        alertDialog.show();
                    } else if (mPosition == 0) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("工作");
                        builder.setMessage("请选择职位");
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
                    //mIsEmpty无错误信息
                    if (mIsEmpty.size() == 0 && mPassword.equals(mPassword2) && mPosition != 0) {
                        initGetMessageCallback();
                    }

                }
            });
        }
    }

    private void initGetMessageCallback() {

    }

    private MyResult loadSave() {
        getEditTextInfo();
        User user = new User(mUserName, mPassword, mName, mPhone, mPosition);
        UserManager userManager = new UserManager();
        MyResult myResult = userManager.register(user);
        return myResult;
    }

//    private void upLoadUserInfo() {
//        //得到键盘输入内容
//        getEditTextInfo();
//        User user=new User(mUserName,mPassword,mName,mPhone,mPosition);
//        mSalesBinder.upJson(user);
//    }

    private void getEditTextInfo() {
        mIsEmpty = new ArrayList<String>();
        mUserName = mEditTextUserName.getText().toString();
        mPassword = mEditTextPassword.getText().toString();
        mPassword2 = mEditTextPassword2.getText().toString();
        mName = mEditTextName.getText().toString();
        mPhone = mEditTextPhone.getText().toString();
    }

    private void initButton() {
        Button button = (Button) findViewById(R.id.buttonRegistreturn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    private void initEditText() {
        mEditTextUserName = (EditText) findViewById(R.id.editTextRegistUserName);
        mEditTextPassword = (EditText) findViewById(R.id.editTextRegistPassword);
        mEditTextPassword2 = (EditText) findViewById(R.id.editTextRegistPassword2);
        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhoneNumer);
    }

    public void onEventMainThread(ResultUser resultUser) {
        Toast.makeText(RegisterActivity.this, resultUser.getMessage(), Toast.LENGTH_SHORT).show();
        if (resultUser.getResult() == 1) {
            //存入首选项，根据groupid跳转界面
            mLoginDao.writeRegisterMessage(this, mUser);
           mUser.setLOGIN_ZHUANGTAI(mUser.ONLINE_VERIFY);

            Intent intent = new Intent(RegisterActivity.this, SalesmanActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
