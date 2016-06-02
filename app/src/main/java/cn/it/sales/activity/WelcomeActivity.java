package cn.it.sales.activity;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import cn.it.sales.R;
import cn.it.sales.Service.SalesBinder;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.LoginResult;
import cn.it.sales.bean.User;
import cn.it.sales.communal.Communals;
import de.greenrobot.event.EventBus;

public class WelcomeActivity extends BaseActivity {
    Handler mHandler;
    String username;
    String  password;
    String mBaiDuFanHuiZhi,mFanHuiZhi;
    ServiceConnection mServiceConnection;
    SalesBinder mSalesBinder;
    Boolean mFirstRun=true;

    Thread mLoginThread=null;
    SharedPreferences mSharedPreferences;
    RegisterActivity mRegisterActivitym=new RegisterActivity();
    MainActivity mMainActivitym=new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initEventBus();
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                initLogin();

                super.handleMessage(msg);
            }
        };
        mHandler.sendEmptyMessageDelayed(1, 2000);

    }

    private void initEventBus() {
        EventBus.getDefault().register(WelcomeActivity.this);
    }


    private void initLogin(){

            mSharedPreferences=getSharedPreferences(Communals.sharedPreferencesforlogIn, Context.MODE_APPEND);
            mFirstRun=mSharedPreferences.getBoolean("FIRST",true);
            if(mFirstRun){
                mSharedPreferences.edit().putBoolean("FIRST", false).commit();
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }

        initDengLuShouYuanXiang();
        }


    private void initDengLuShouYuanXiang() {
        mSharedPreferences = getSharedPreferences(mRegisterActivitym.SHARED_NANE, Context.MODE_PRIVATE);
        username = mSharedPreferences.getString("username", "");
        password = mSharedPreferences.getString("password", "");


        if (!username.isEmpty() && !password.isEmpty()) {
            initShuJuHuiDiaoByBaidu();


//        }else{
//            Intent intent=new Intent(WelcomeActivity.this, MyService.class);
//            mServiceConnection=new ServiceConnection() {
//                @Override
//                public void onServiceConnected(ComponentName name, IBinder service) {
//                    mSalesBinder= (SalesBinder) service;
//                    selectUserMassage(username,password,groupid);
//                }
//
//                @Override
//                public void onServiceDisconnected(ComponentName name) {
//
//                }
//            };
//            bindService(intent,mServiceConnection,Context.BIND_AUTO_CREATE);
//        }
        }
    }
    private void initShuJuHuiDiaoByBaidu() {
        if(mLoginThread==null){
            mLoginThread=new Thread(){
                @Override
                public void run() {
                    String urlStr="http://www.baidu.com";
                    //第2步 利用Builer模式，配置URL地址和参数，并创建Builder实例
                    Request request=
                            new Request.Builder().url(urlStr).build();
                    //第3步创建一个调用call
                    Call call =
                            MyApplication.getOkHttpClient().newCall(request);
                    //第4步执行call,并保存回复Response
                    try {
                        Response response=call.execute();
                        //第5步判断回复是否正常,code[200..300)
                        if(response.isSuccessful()){
                            //第6步 解析回复
                            mBaiDuFanHuiZhi=response.body().string();
                            Log.d("OKHTTP", "text=" + mBaiDuFanHuiZhi);
                        }else{
                            mBaiDuFanHuiZhi="ERROR="+response.message();
                            Log.d("OKHTTP","ERROR="+response.message());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    super.run();
                    //变量置空，就允许再次点击下载按钮
                    mLoginThread=null;

                    sendLoginMessage();
                }
            };
            mLoginThread.start();
        }
    }
    private void sendLoginMessage() {

        LoginResult loginResult =new LoginResult(mBaiDuFanHuiZhi);
        EventBus.getDefault().post(loginResult);
    }

    public void onEventMainThread(LoginResult loginResult){
        mFanHuiZhi= loginResult.toString();
        if(!mFanHuiZhi.isEmpty()&&!mFanHuiZhi.startsWith("ERROR=")){
            MyApplication.DENG_LU_ZHUANG_TAI=1;
            switch (mMainActivitym.mRadioGroupId){
                case 1:
                    Intent intent = new Intent(WelcomeActivity.this, SalesmanActivity.class);
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
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    }

    private void selectUserMassage(String username, String password, long groupid) {
        User user=new User(username,password,groupid);
        mSalesBinder.selectUserMessage(user);
    }
    public void onEventMainThread(User event){
//        Log.d("oi",""+event);
        int result=event.getResultcode();
        if(result==1){
            long group=event.getGroupId();
            String username=event.getUserName();
//            SharedPreferences.Editor editor=mSharedPreferences.edit();
//            editor.putString("name",username);
//            editor.commit();
            if(group==1){
                Intent intent=new Intent(WelcomeActivity.this,SalesmanActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
            if(group==2){
                Intent intent=new Intent(WelcomeActivity.this,GovernorActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
            if (group==3){
                Intent intent=new Intent(WelcomeActivity.this,WarehouseActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }
        if(result==-1){
            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            intent.putExtra("loginstatus",-1);
            intent.putExtra("message",event.getMessage());
            startActivity(intent);
            WelcomeActivity.this.finish();
        }
    }



    @Override
    protected void onResume() {
      //  EventBus.getDefault().register(WelcomeActivity.this);
        super.onResume();
    }

    @Override
    protected void onStop() {
        if(mServiceConnection!=null){
            unbindService(mServiceConnection);
            mServiceConnection=null;
        }
        EventBus.getDefault().unregister(WelcomeActivity.this);
        super.onStop();
    }
}
