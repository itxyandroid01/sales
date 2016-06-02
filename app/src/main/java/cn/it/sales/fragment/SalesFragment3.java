package cn.it.sales.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.adapter.HotSales_Adapter_Activity;
import cn.it.sales.ball.HotSalesManager;
import cn.it.sales.bean.JiaoBanShangPin;
import cn.it.sales.dao.ConnectDao;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment3 extends Fragment {
    RecyclerView mRecyclerView;
    List<JiaoBanShangPin> mDataList;
    HotSalesManager mHotSalesManager=new HotSalesManager();
    View mView;
    Button mButton1,mButton2,mButton3,mButton4,mButton5,mButton6;
    ConnectDao mJiaoBan=new ConnectDao();
    HotSales_Adapter_Activity mDianJiAnNiu= new HotSales_Adapter_Activity();
    HotSales_Adapter_Activity mRecyclerAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.sales_fragment3, container, false);

        initRecyclerView();
        initFindButton();



        return mView;

    }

    private void initFindButton() {

        mButton1= (Button) mView.findViewById(R.id.buttonsales1);
        mButton2= (Button) mView.findViewById(R.id.buttonsales2);
        mButton3= (Button) mView.findViewById(R.id.buttonsales3);
        mButton4= (Button) mView.findViewById(R.id.buttonsales4);
        mButton5= (Button) mView.findViewById(R.id.buttonsales5);
        mButton6= (Button) mView.findViewById(R.id.buttonsales6);
        hotSales();
    }

    private void initRecyclerView() {
        mDataList=mHotSalesManager.reaHotSalesShangPin(mJiaoBan.getSql(0));
        mRecyclerView= (RecyclerView)mView.findViewById(R.id.recyclerViewSales);
        shiPeiQi();
        mDianJiAnNiu.dianJiAnNiu(1);
    }

    public void hotSales(){
        mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDianJiAnNiu.dianJiAnNiu(1);
                    mDataList=mHotSalesManager.reaHotSalesShangPin(mJiaoBan.getSql(1));
                    shiPeiQi();

                }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDianJiAnNiu.dianJiAnNiu(2);
                mDataList=mHotSalesManager.reaHotSalesShangPin(mJiaoBan.getSql(0));
                shiPeiQi();

            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDianJiAnNiu.dianJiAnNiu(3);
                mDataList=mHotSalesManager.reaHotSalesShangPin(mJiaoBan.getSql(1));
                shiPeiQi();

            }
        });
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList=mHotSalesManager.reaHotSalesShangPin(mJiaoBan.getSql(2));
                shiPeiQi();
                mDianJiAnNiu.dianJiAnNiu(4);
            }
        });
        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList = mHotSalesManager.reaHotSalesShangPin(mJiaoBan.getSql(3));
                shiPeiQi();
                mDianJiAnNiu.dianJiAnNiu(5);
            }
        });
        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList = mHotSalesManager.reaHotSalesShangPin(mJiaoBan.getSql(3));
                shiPeiQi();
                mDianJiAnNiu.dianJiAnNiu(6);
            }
        });
     }

    public void shiPeiQi(){
        mRecyclerAdapter=new HotSales_Adapter_Activity(mDataList,getActivity());
        //设置组件的显示方式(ListView，GridView,瀑布流模式),设置为线性显示方式(ListView模式)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mRecyclerAdapter);
}

}
