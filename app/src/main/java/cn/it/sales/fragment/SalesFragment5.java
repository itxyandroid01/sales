package cn.it.sales.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import cn.it.sales.R;
import cn.it.sales.Result.MyResult;
import cn.it.sales.Service.MyService;
import cn.it.sales.Service.SalesBinder;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bll.JiaoBanManager;
import cn.it.sales.communal.Communals;
import cn.it.sales.util.DateTimeUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment5 extends Fragment {
    View rootView;
    Context mContext;
    Fragment mFragment;
    Button mButton1,mButton2;
    RadioGroup mRadioGroup1,mRadioGroup2;
    int mBanBie=1;
    int mBanci = MyApplication.getmBanCi();
    SharedPreferences mSharedPreferences;
    String mDianPu;
    ServiceConnection mServiceConnection;
    SalesBinder mSalesBinder;
    public SalesFragment5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.sales_fragment5, container, false);
        mContext=getActivity();
        //获取首先项
        mSharedPreferences=mContext.getSharedPreferences(Communals.
                        sharedPreferencesforlogIn, mContext.MODE_PRIVATE);
        //初始化班次店铺
        initRadioGroup();
        //初始化交接班
        initButton();
        return rootView;
    }

    private void initRadioGroup() {
        mRadioGroup2=(RadioGroup)rootView.findViewById(R.id.radiodianpu);
        mRadioGroup1=(RadioGroup)rootView.findViewById(R.id.radiobanci);
        mRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio01:
                        mBanBie = 1;
                        break;
                    case R.id.radio02:
                        mBanBie = 2;
                        break;
                    case R.id.radio03:
                        mBanBie = 3;
                        break;
                }
            }
        });
        mRadioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                case R.id.radio0４:
                mDianPu="起疙瘩校园超市";
                break;
                case R.id.radio0５:
                mDianPu="牛顿国际";
                break;}
            }
        });
    }

    private void initButton() {
        mButton1= (Button) rootView.findViewById(R.id.bt11);
        mButton2= (Button) rootView.findViewById(R.id.bt12);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment=new FragmentJiaoban();
            getFragmentManager().beginTransaction().replace(R.id.fragm11,mFragment).commit();
        }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String shijian = DateTimeUtil.getSystemtDateTime();
                int jieban = Integer.parseInt(shijian.substring(11, 13));
                if (jieban >= 7 && jieban < 24) {
                    //启动服务，下载数据并放到本地数据库
                    downloadservice();
                    //根据时间日期生成唯一的本班班次
                    mBanci = Integer.parseInt(shijian.substring(0, 10).replace("-", "") + mBanBie);
                    //查询接班
                    selectjieban(mBanci);
                    mFragment = new FragmentJieban();
                    getFragmentManager().beginTransaction().replace(R.id.fragm11, mFragment).commit();

                } else {
                    Toast.makeText(mContext, "亲，现在不是上班时间", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void selectjieban(int banci) {
        JiaoBanManager jiaoBanManager = new JiaoBanManager();
        //从首选项取出工号姓名
        int gonghao = mSharedPreferences.getInt("gonghao", 1001);
        String xingming = mSharedPreferences.getString("xingming", "");
        //根据班次查询上班情况
        MyResult myResult = jiaoBanManager.readJiaobanByBanCi(banci, gonghao, xingming);
        int code = myResult.getCode();
        if (code == 0) {
            Toast.makeText(mContext, myResult.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (code == 1) {
            Toast.makeText(mContext, myResult.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (code == 2) {
            Toast.makeText(mContext, myResult.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void downloadservice() {
        Intent intent = new Intent(mContext, MyService.class);
        mServiceConnection= new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //得到服务的Binder并保存到成员变量中
                mSalesBinder = (SalesBinder) service;
                //服务已连接 下载数据
                mSalesBinder.downloadShangpinInfo();
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

}
