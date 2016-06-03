package cn.it.sales.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.it.sales.R;
import cn.it.sales.adapter.AccountExpandableAp;
import cn.it.sales.adapter.RecyclerViewAdaPter;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bll.SalesJiLuManager;
import cn.it.sales.bll.ShangPinLeiXingManager;
import cn.it.sales.bll.ShangPinManager;
import cn.it.sales.bean.SalesMessage;
import cn.it.sales.bean.ShangPin;
import cn.it.sales.bean.ShangPinLeiXing1;
import cn.it.sales.bean.ShangPinLeiXing2;
import cn.it.sales.diglog.DoubleDatePickerDialog;
import cn.it.sales.util.DateTimeUtil;

/**
 * Created by Administrator on 2016/5/13.
 */
public class SalesFragment2 extends Fragment {
    RecyclerView mRecyclerView;
    Button btn, mChaXunBtn, btnNormal;
    EditText et1, et2;
    AutoCompleteTextView mAuto;  //m:member ,组件
    ShangPinLeiXingManager mShangPinLeiXingManager;
    List<String> groupArray;
    List<List<String>> childArray;
    ExpandableListView expandableList;
    AccountExpandableAp mAccountExpandableAp;
    List<SalesMessage> mDataList1;
    List<ShangPin> mDataList;
    ShangPinManager shangPinManager = new ShangPinManager();
    SalesJiLuManager mSalesJiLuManager = new SalesJiLuManager();
    RecyclerViewAdaPter mRecyclerViewAdaPter;

    String mShangPinMingCheng;

    String textString1;
    String textString2;

    String mKaiShiShiJian;
    String mJieShuShiJian;

    int leibiebianhao;
    String leibiemingcheng;

    public SalesFragment2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sale_fragment2, container, false);

        btn = (Button) rootView.findViewById(R.id.dateBtn);
        et1 = (EditText) rootView.findViewById(R.id.et1);
        et2 = (EditText) rootView.findViewById(R.id.et2);


        et1.setText(DateTimeUtil.getSystemtDateTime());
        et2.setText(DateTimeUtil.getSystemtDateTime());

        mAuto = (AutoCompleteTextView)
                rootView.findViewById(R.id.autoCompleteTextView1);
        initAutoCompleteTextView();
        mChaXunBtn = (Button) rootView.findViewById(R.id.chaxunBtn);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.mrecyclerView);


        expandableList = (ExpandableListView) rootView.findViewById(R.id.ExpandableListView01);
        btnNormal = (Button) rootView.findViewById(R.id.btnNormal);


        View.OnClickListener listener;

        listener = new View.OnClickListener() {
            Calendar c = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.dateBtn:
                        new DoubleDatePickerDialog(getActivity(), 0, new DoubleDatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
                                                  int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear,
                                                  int endDayOfMonth) {

                                textString1 = String.format("%04d-%02d-%02d", startYear,
                                        startMonthOfYear + 1, startDayOfMonth);
                                et1.setText(textString1);

                                textString2 = String.format("%04d-%02d-%02d", endYear, endMonthOfYear + 1, endDayOfMonth);
                                et2.setText(textString2);


                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), true).show();

                        break;
                    case R.id.btnNormal:
                        initwupinBtn();

                        break;

                    case R.id.chaxunBtn:
                        initRecycleView();
                        break;
                }
            }

        };
        btn.setOnClickListener(listener);
        mChaXunBtn.setOnClickListener(listener);
        btnNormal.setOnClickListener(listener);
        return rootView;
    }

    private void initwupinBtn() {
        expandableList.setVisibility(View.VISIBLE);
        mShangPinLeiXingManager = new ShangPinLeiXingManager();
        mShangPinLeiXingManager.readShangPinLeiXing();

        final List<ShangPinLeiXing1> list = MyApplication.getShangPinLeiXing1List();
        groupArray = new ArrayList<String>();
        childArray = new ArrayList<List<String>>();
        for (ShangPinLeiXing1 shangPinLeiXing1 : list) {

            groupArray.add(shangPinLeiXing1.getLeibiemingcheng());

            List<ShangPinLeiXing2> list1 = shangPinLeiXing1.getShangPinLeiXing2List();

            ArrayList<String> list2 = new ArrayList<String>();

            for (ShangPinLeiXing2 shangPinLeiXing2 : list1) {
                list2.add(shangPinLeiXing2.getLeibiemingcheng());
            }
            childArray.add(list2);
        }
        mAccountExpandableAp = new AccountExpandableAp(getActivity(), groupArray, childArray);
        expandableList.setAdapter(mAccountExpandableAp);
        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                leibiemingcheng =
                        list.get(groupPosition).
                                getShangPinLeiXing2List().
                                get(childPosition).
                                getLeibiemingcheng();

                leibiebianhao =
                        list.get(groupPosition).
                                getShangPinLeiXing2List().
                                get(childPosition).
                                getLeibiebianhao();

                mAuto.setText(leibiemingcheng);
                expandableList.setVisibility(View.GONE);
                return true;
            }
        });

    }


    private void initRecycleView() {
        mKaiShiShiJian = et1.getText().toString();
        mJieShuShiJian = et2.getText().toString();

        if (mDataList != null) {
            mDataList.clear();
        }
        readListView();
    }


    private void readListView() {


        if (mKaiShiShiJian != null && mJieShuShiJian != null) {

            if (leibiemingcheng == null) {
                mSalesJiLuManager = new SalesJiLuManager();
                mDataList1 = mSalesJiLuManager.readJiLuXiaoShou1(mKaiShiShiJian, mJieShuShiJian);
                mRecyclerViewAdaPter = new RecyclerViewAdaPter(getActivity(), mDataList1);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mRecyclerViewAdaPter);

            } else if (leibiemingcheng != null) {

                mSalesJiLuManager = new SalesJiLuManager();
                mDataList1 = mSalesJiLuManager.readJiLuXiaoShou3(leibiebianhao, mKaiShiShiJian, mJieShuShiJian);
                mRecyclerViewAdaPter = new RecyclerViewAdaPter(getActivity(), mDataList1);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mRecyclerViewAdaPter);

            }

        }

    }


    private void initAutoCompleteTextView() {
        ShangPinLeiXingManager mShangPinLeiXingManager = new ShangPinLeiXingManager();
        mShangPinLeiXingManager.readShangPinLeiXing();
        List<String> mAutoList = new ArrayList<String>();
        List<List<String>> mChild = new ArrayList<List<String>>();
        final List<ShangPinLeiXing1> list = MyApplication.getShangPinLeiXing1List();
        for (ShangPinLeiXing1 shangPinLeiXing1 : list) {
            mAutoList.add(shangPinLeiXing1.getLeibiemingcheng());
            List<ShangPinLeiXing2> list1 = shangPinLeiXing1.getShangPinLeiXing2List();

            ArrayList<String> list2 = new ArrayList<String>();

            for (ShangPinLeiXing2 shangPinLeiXing2 : list1) {

                list2.add(shangPinLeiXing2.getLeibiemingcheng());
            }
            mChild.add(list2);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mAutoList);
        mAuto.setAdapter(adapter);
    }
}




