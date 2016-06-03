package cn.it.sales.dao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import cn.it.sales.activity.LoginActivity;
import cn.it.sales.activity.RegisterActivity;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.User;
import cn.it.sales.communal.Communals;
import cn.it.sales.db.MyOpenHelp;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LoginDao {
    SharedPreferences mSharedPreferences;
    RegisterActivity mRegisterActivity=new RegisterActivity();
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
}
