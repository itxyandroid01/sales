package cn.it.sales.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.it.sales.R;
import cn.it.sales.activity.cozeActivity;

/**
 * 即时通讯功能
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment6 extends Fragment {

    Button mButton1,mButton2;
    View mRootView;
    Context mContext;
    public SalesFragment6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mRootView=inflater.inflate(R.layout.sales_fragment6, container, false);
        mContext=getActivity();
        initButton();
        return mRootView;
    }

    private void initButton() {
        mButton1=(Button) mRootView.findViewById(R.id.gongsitongzhi);
        mButton2=(Button) mRootView.findViewById(R.id.liaotian);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, cozeActivity.class);
                startActivity(intent);
            }


        });
    }

}
