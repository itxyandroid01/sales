package cn.it.sales.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.adapter.AccountRecycleAp;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bll.ShangPinManager;
import cn.it.sales.bean.ShangPin;
import cn.it.sales.bean.ShangPinLeiXing1;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment1 extends Fragment {

    RecyclerView mRecyclerView;
    List<ShangPin> mList;
    Context mContext;
    AccountRecycleAp mAccountAdapter;

    ShangPinManager mShangPinManager=new ShangPinManager();
    FrameLayout mFrameLayout;
    public SalesFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.sales_fragment1, container, false);
        mContext =getActivity();
        initFrameLayout(rootView);
        initRecycler(rootView);
        return rootView;
    }
    private void initFrameLayout(View rootView) {
        mFrameLayout = (FrameLayout)rootView.findViewById(R.id.fragmeLayoutleibie);
        final List<ShangPinLeiXing1> list = MyApplication.getShangPinLeiXing1List();
        ExcepandableFragment.Item2OnClickListener listener;
        listener=new ExcepandableFragment.Item2OnClickListener() {
            @Override
            public void item2OnClikListenr(int groupPosition, int childPosition) {
                int leibiebianhao =
                        list.get(groupPosition).
                                getShangPinLeiXing2List().
                                get(childPosition).
                                getLeibiebianhao();
                mList=mShangPinManager.readShangPinListByLeiBieBianHao(leibiebianhao);
                mAccountAdapter.setDataListAndReFresh(mList);
            }
        };
        ExcepandableFragment excepandableFragment=new ExcepandableFragment();
        excepandableFragment.setItem2OnClickListener(listener);

        getFragmentManager().beginTransaction().replace(R.id.fragmeLayoutleibie,excepandableFragment).commit();


    }

    private void initRecycler(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewshangpin);
        //获取数据源
        mList=mShangPinManager.readShangPinListByLeiBieBianHao(1001001);
        mAccountAdapter = new AccountRecycleAp(mContext, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAccountAdapter);

    }
}
