package cn.it.sales.dao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import cn.it.sales.activity.LoginActivity;
import cn.it.sales.activity.RegisterActivity;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.LoginResult;
import cn.it.sales.bean.User;
import cn.it.sales.communal.Communals;
import cn.it.sales.db.MyOpenHelp;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LoginDao {
    SharedPreferences mSharedPreferences;
    RegisterActivity mRegisterActivity=new RegisterActivity();
    String mCallbackData,mAcceptCallbackData;
    Thread mLoginThread=null;
    String mUrlStr="http://www.baidu.com";
    MyOpenHelp mDBHelpe;
    User mUser=new User();
    Boolean mFirstRun=true;
    public LoginDao() {
        mDBHelpe = MyApplication.getDb1Help();
    }
    //判断是否第一次进入app
    public Boolean firstRun(Context context) {
        mSharedPreferences = context.getSharedPreferences(Communals.sharedPreferencesforlogIn, Context.MODE_APPEND);
        mFirstRun = mSharedPreferences.getBoolean("FIRST", true);
        if (mFirstRun) {
            mSharedPreferences.edit().putBoolean("FIRST", false).commit();
            Intent intent=new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
        return mFirstRun;
    }
    //登录信息
    public User loginMessage(Context context){
        mSharedPreferences = context.getSharedPreferences(mRegisterActivity.SHARED_NANE, Context.MODE_PRIVATE);
        String userName=mSharedPreferences.getString("username", "");
        String password=mSharedPreferences.getString("password", "");
        mUser.setUserName(userName);
        mUser.setPassWord(password);
        return mUser;
    }
    public void validationLoginByService() {
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
                    //注册一个事务
                    registerEventBus();
                    //开启一个事务发送从服务器得到的数据
                    sendLoginMessage();
                    //取消注册
                    unRegisterEventBus();

                }
            };
            mLoginThread.start();
        }
    }



    private void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    private void sendLoginMessage() {
        LoginResult loginResult =new LoginResult(mCallbackData);
        EventBus.getDefault().post(loginResult);

    }
    public String onEventMainThread(LoginResult loginResult) {
        mAcceptCallbackData = loginResult.toString();
        return mAcceptCallbackData;
    }
    private void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }
}
