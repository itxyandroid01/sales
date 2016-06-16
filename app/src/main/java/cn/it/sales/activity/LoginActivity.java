package cn.it.sales.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.it.sales.R;
import cn.it.sales.Service.MyService;
import cn.it.sales.Service.SalesBinder;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.ResultUser;
import cn.it.sales.bean.User;
import cn.it.sales.bll.UserManager;
import de.greenrobot.event.EventBus;


/***
 * binder以调用service
 * 注册（eventBus）以从service返回信息（service 到activity）
 * （startActivityForResult：activity 到activity）
 */
public class LoginActivity extends BaseActivity {

    EditText mEditTextUserName, mEditTextPassWord;
    RadioGroup mRadioGroup;
    ImageView mLogin, mRegister;

    Context mContext;
    User mUser;
    String mGongHao, mPassWord;
    ArrayList<String> mIsEmptylogin;

    SalesBinder mBinder;
    ServiceConnection mServiceConnection = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        mUser= MyApplication.getUser();

        //输入框
        initEditText();
        //选择职位
        initRadioGroup();
        //登入到业务界面
        initLogin();
        //跳转到注册界面
        initRegister();
    }

    private void initEditText() {
        mEditTextUserName = (EditText) findViewById(R.id.editText1);
        mEditTextPassWord = (EditText) findViewById(R.id.editText2);
    }

    private void initRadioGroup() {
        mRadioGroup = (RadioGroup) findViewById(R.id.RadioGroup1);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId ==R.id.XiaoShou) {
                    mUser.setGroupId(1001);
                }
                if (checkedId == R.id.ZhuGuan) {
                    mUser.setGroupId(1002);
                }
                if (checkedId ==R.id.KuGuan) {
                    mUser.setGroupId(1003);
                }
            }
        });
    }

    private void initLogin() {
        mLogin= (ImageView) findViewById(R.id.bt_login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查登录输入规则
                if(checkInputDengLu()){
                    //提交数据到服务器
//                    Intent intent = new Intent(LoginActivity.this, MyService.class);
//                    mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                    initBinder();

                }
            }
        });
    }

    private boolean checkInputDengLu() {
        // getInputMessage();
        mGongHao= mEditTextUserName.getText().toString();
        mPassWord= mEditTextPassWord.getText().toString();
        mIsEmptylogin = new ArrayList<String>();
        if (TextUtils.isEmpty(mGongHao) || TextUtils.isEmpty(mPassWord)) {
            if (TextUtils.isEmpty(mGongHao)) {
                mIsEmptylogin.add("用户名不能为空");
            }
            if (TextUtils.isEmpty(mPassWord)) {
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
        //错误信息为0
        if(mIsEmptylogin.size()==0){
            return true;
        }else {
            return false;
        }
    }

    private void initBinder() {
        Intent intent = new Intent(LoginActivity.this, MyService.class);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mBinder = (SalesBinder) service;
                //绑定后 调用方法
                tiJiaoDengLu();
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void tiJiaoDengLu() {
        //mPassWordMd5= HexUtil.getMd5(mPassWord);
        mUser.setGongHao(mGongHao);
        mUser.setPassWord(mPassWord);
        if (mBinder != null) {
            mBinder.selectUserNameAndPassword(mUser);
        }
    }

    public void onEventMainThread(ResultUser resultUser) {
        Toast.makeText(LoginActivity.this, resultUser.getMessage(), Toast.LENGTH_SHORT).show();
        if (resultUser.getResult() == 1) {
            //存入首选项，根据groupid跳转界面
            UserManager userManager=new UserManager();
            userManager.writeRegisterMessage(this, mUser);
            mUser.setZhuangTai(mUser.ONLINE_VERIFY);
            Intent intent = new Intent(LoginActivity.this, SalesMainActivity.class);
            startActivity(intent);
        }
    }

    private void initRegister() {
        mRegister= (ImageView) findViewById(R.id.bt_lon);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }




    @Override
    protected void onResume() {
        EventBus.getDefault().register(LoginActivity.this);
        super.onResume();
    }
    @Override
    protected void onStop() {
        //断开binder
        if (mServiceConnection!=null) {
            unbindService(mServiceConnection);
            mServiceConnection = null;
        }
        //解除注册
        EventBus.getDefault().unregister(LoginActivity.this);
        super.onStop();
    }

}
