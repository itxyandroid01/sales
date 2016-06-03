package cn.it.sales.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import cn.it.sales.R;
import cn.it.sales.bean.User;
import cn.it.sales.dao.LoginDao;

public class WelcomeActivity extends BaseActivity {
    Handler mHandler;

    String mAcceptCallbackData;
    Boolean mFirstRun=true;
    User mUser=new User();
    LoginDao mLoginDao=new LoginDao();
    LoginActivity mMainActivity=new LoginActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                initLogin();

                super.handleMessage(msg);
            }
        };
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    private void initLogin(){

        mFirstRun=mLoginDao.firstRun(this);
        if(!mFirstRun) {
            //效验登录状态
            ValidationLoginStatus();
        }
        }

    private void ValidationLoginStatus() {
       mUser= mLoginDao.loginMessage(this);
        String userName=mUser.getUserName();
        String password=mUser.getPassWord();

            //用TextUtils.isEmpty替换
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            //从服务器效验账户密码
            ValidationLoginByService();

        }else {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    private void ValidationLoginByService() {
        mLoginDao.validationLoginByService();

        if(!TextUtils.isEmpty(mAcceptCallbackData)&&!mAcceptCallbackData.startsWith("ERROR=")){
            //设置登录状态为1
           // MyApplication.DENG_LU_ZHUANG_TAI=1;
            //选择职位
            switch (mMainActivity.mRadioGroupId){
                case 1:
                    Intent intent = new Intent(WelcomeActivity.this,SalesmanActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Intent intent1=new Intent(WelcomeActivity.this,GovernorActivity.class);
                    startActivity(intent1);
                    break;
                case 3:
                    Intent intent2=new Intent(WelcomeActivity.this,WarehouseActivity.class);
                    startActivity(intent2);
                    break;
            }


    }else{
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    }
}
