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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.it.sales.R;
import cn.it.sales.Service.MyService;
import cn.it.sales.Service.SalesBinder;
import cn.it.sales.bean.ResultUser;
import cn.it.sales.bean.User;
import cn.it.sales.dao.LoginDao;
import cn.it.sales.util.HexUtil;
import de.greenrobot.event.EventBus;

public class LoginActivity extends BaseActivity {


    //activity调用service方法
    String mGongHao, mPassWord;
    ArrayList<String> mIsEmptylogin;
    Context mContext;
    EditText mEditTextUserName, mEditTextPassWord;
    User mUser;
    long mGroupId;
    LoginDao mLoginDao=new LoginDao();
    SalesBinder mBinder;
    String mPassWordMd5;
    Intent mIntent ;
    ServiceConnection mServiceConnection = null;
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
        registerEventBus();
        mContext = this;

        initEditText();
        //选择职位
        initRadioGroup();
        //注册界面跳转
        initImageView1();
        //登录
        initImageView2();

        //调用服务
        initBinder();
    }

    //1：LoginActivity界面接受信息   所以注册
    //2：其他位置向LoginAcitivity中发送信息时使用post（XXX xxx） xxx为携带信息
    //3：在LoginActivity界面中利用onEventMainThread(XXX xxx) 接受信息
    //4：在本界面废弃时  解除注册
    private void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    private void initBinder() {

        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                //service转换为binder
                //然后可以在activity中随处调用service中方法（mBinder.xxx）
                //xxx需要在binder中书写
                mBinder = (SalesBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

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

    //跳转到注册界面
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

    //登录
    private void initImageView2() {
        ImageView imageView= (ImageView) findViewById(R.id.bt_login);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查登录输入规则
                if(checkInputDengLu()){
                    //调用service方法提交数据到服务器
                    mIntent = new Intent(LoginActivity.this, MyService.class);
                    mContext.bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
                    tiJiaoDengLu();
                }

            }

            private void tiJiaoDengLu() {
                //密码加密
                mPassWordMd5= HexUtil.getMd5(mPassWord);
                //将信息写入bean层
                mUser = new User(mGongHao,mPassWordMd5,mGroupId);
                //调用service服务
                if(mBinder!=null) {
                    //转到网络层
                    mBinder.selectUserNameAndPassword(mUser);
                }
            }
        });
    }

    //接受post信息
    public void onEventMainThread(ResultUser resultUser) {
        Toast.makeText(LoginActivity.this, resultUser.getMessage(), Toast.LENGTH_SHORT).show();
        //根据result得到的结果，将数据存入首选项，并跳转界面
        if (resultUser.getResult() == 1) {
            mLoginDao.writeRegisterMessage(this, mUser);
            mUser.setZhuangTai(mUser.ONLINE_VERIFY);
            Intent intent = new Intent(LoginActivity.this, SalesMainActivity.class);
            startActivity(intent);
        }
    }

    //输入判定
    private boolean checkInputDengLu() {
        getInputMessage();
        if (mGongHao.isEmpty() || mPassWord.isEmpty()) {
            if (mGongHao.isEmpty()) {
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
        //错误信息为0
        if(mIsEmptylogin.size()==0){
            return true;
        }
        return false;
    }

    private void getInputMessage() {
        mIsEmptylogin = new ArrayList<String>();
        mGongHao = mEditTextUserName.getText().toString();
        mPassWord = mEditTextPassWord.getText().toString();
    }

    private void initEditText() {
        mEditTextUserName = (EditText) findViewById(R.id.editText1);
        mEditTextPassWord = (EditText) findViewById(R.id.editText2);
    }

    //在界面被覆盖（跳转到注册或者业务界面）后，在stop中解除注册
    @Override
    protected void onStop() {
        if (mServiceConnection!=null) {
            //解除binder的connection
            //解除eventBus
            this.unbindService(mServiceConnection);
            EventBus.getDefault().unregister(this);

        }
        super.onStop();
    }
}
