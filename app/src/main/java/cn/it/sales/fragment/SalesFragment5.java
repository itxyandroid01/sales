package cn.it.sales.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.it.sales.R;
import cn.it.sales.Result.MyResult;
import cn.it.sales.application.MyApplication;
import cn.it.sales.ball.JiaoBanManager;
import cn.it.sales.communal.Communals;
import cn.it.sales.util.DateTimeUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment5 extends Fragment {
    View rootView;
    Context mContext;
    TextView mTextView;
    Fragment mFragment;
    Button mButton1,mButton2;
    RadioGroup mRadioGroup;
    int mBanBie=1;
    int mBanci = MyApplication.getmBanCi();
    SharedPreferences mSharedPreferences;

    public SalesFragment5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.sales_fragment5, container, false);
        mContext=getActivity();
                mSharedPreferences=mContext.getSharedPreferences(Communals.
                        sharedPreferencesforlogIn, mContext.MODE_PRIVATE);
        initRadioGroup();
        initButton();
        return rootView;
    }

    private void initRadioGroup() {
//        DateTimeUtil dateTimeUtil=new DateTimeUtil();
//        mTextView=(TextView) rootView.findViewById(R.id.textshangpin);
//        mTextView.setText(dateTimeUtil.getSystemtDateTime().toString());
        mRadioGroup=(RadioGroup)rootView.findViewById(R.id.radiobanci);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                    mBanci = Integer.parseInt(shijian.substring(0, 10).replace("-", "") + mBanBie);
                    selectJiaobanByBanCi(mBanci);
                    mFragment = new FragmentJieban();
                    getFragmentManager().beginTransaction().replace(R.id.fragm11, mFragment).commit();

                } else {
                    Toast.makeText(mContext, "亲，现在不是上班时间", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void selectJiaobanByBanCi(int banci) {
        JiaoBanManager jiaoBanManager = new JiaoBanManager();
        int gonghao = mSharedPreferences.getInt("gonghao", 0);
        String xingming = mSharedPreferences.getString("xingming", "");
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


}
