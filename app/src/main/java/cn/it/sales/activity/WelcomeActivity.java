package cn.it.sales.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import cn.it.sales.R;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.LoginResult;
import cn.it.sales.bean.User;
import cn.it.sales.dao.LoginDao;
import de.greenrobot.event.EventBus;

public class WelcomeActivity extends BaseActivity {
    Handler mHandler;
    String mCallbackData, mAcceptCallbackData;
    Thread mLoginThread = null;
    String mUrlStr = "http://www.baidu.com";
    Boolean mFirstRun = true;
    User mUser = new User();
    LoginDao mLoginDao = new LoginDao();
    LoginActivity mMainActivity = new LoginActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        registerEventBus();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                initLogin();

                super.handleMessage(msg);
            }
        };
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    private void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    private void initLogin() {

        mFirstRun = mLoginDao.firstRun(this);
        if (!mFirstRun) {
            //效验登录状态
            ValidationLoginStatus();
        }
    }

    private void ValidationLoginStatus() {
        mUser = mLoginDao.loginMessage(this);
        String userName = mUser.getUserName();
        String password = mUser.getPassWord();

        //用TextUtils.isEmpty替换
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            //从服务器效验账户密码
            validationLoginByService();

        } else {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void validationLoginByService() {
        if (mLoginThread == null) {
            mLoginThread = new Thread() {
                @Override
                public void run() {
                    //第2步 利用Builer模式，配置URL地址和参数，并创建Builder实例
                    Request request =
                            new Request.Builder().url(mUrlStr).build();
                    //第3步创建一个调用call
                    Call call =
                            MyApplication.getOkHttpClient().newCall(request);
                    //第4步执行call,并保存回复Response
                    try {
                        Response response = call.execute();
                        //第5步判断回复是否正常,code[200..300)
                        if (response.isSuccessful()) {
                            //第6步 解析回复
                            mCallbackData = response.body().string();
                            Log.d("OKHTTP", "text=" + mCallbackData);
                        } else {
                            mCallbackData = "ERROR=" + response.message();
                            Log.d("OKHTTP", "ERROR=" + response.message());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    super.run();
                    //变量置空，就允许再次点击下载按钮
                    mLoginThread = null;

                    //开启一个事务发送从服务器得到的数据
                    sendLoginMessage();

                }
            };
            mLoginThread.start();
        }

    }

    private void sendLoginMessage() {
        LoginResult loginResult = new LoginResult(mCallbackData);
        EventBus.getDefault().post(loginResult);
    }
    public void onEventMainThread(LoginResult loginResult){
        mAcceptCallbackData= loginResult.toString();
        if (!TextUtils.isEmpty(mAcceptCallbackData) && !mAcceptCallbackData.startsWith("ERROR="))

        {
            //设置登录状态为1
            // MyApplication.DENG_LU_ZHUANG_TAI=1;
            //选择职位
            switch (mMainActivity.mRadioGroupId) {
                case 1:
                    Intent intent = new Intent(WelcomeActivity.this, SalesmanActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Intent intent1 = new Intent(WelcomeActivity.this, GovernorActivity.class);
                    startActivity(intent1);
                    break;
                case 3:
                    Intent intent2 = new Intent(WelcomeActivity.this, WarehouseActivity.class);
                    startActivity(intent2);
                    break;
            }


        } else

        {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }


    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
