package cn.it.sales.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import cn.it.sales.R;
import cn.it.sales.adapter.AccountExpandableAp;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bll.ShangPinLeiXingManager;
import cn.it.sales.bean.ShangPinLeiXing1;
import cn.it.sales.bean.ShangPinLeiXing2;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExcepandableFragment extends Fragment {
    ShangPinLeiXingManager mShangPinLeiXingManager;
    ExpandableListView mExpandableListView;
    List<String> groupArray;
    List<List<String>> childArray;
    AccountExpandableAp mAccountExpandableAp;
    Item2OnClickListener mListener;
    public void setItem2OnClickListener(Item2OnClickListener listener){
        mListener=listener;
    }
    public ExcepandableFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.excepandable, container, false);
        initExpandable(rootView);
        return rootView;
    }
    private void initExpandable(View rootView) {
        mExpandableListView = (ExpandableListView)rootView.findViewById(R.id.expandablefragment);
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
        mExpandableListView.setAdapter(mAccountExpandableAp);
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                initColor(v);
                if(mListener != null) {
                    mListener.item2OnClikListenr(groupPosition, childPosition);
                }
                return true;
            }
        });
    }

    private void initColor(View v) {
        int color1=getResources().getColor(R.color.seashell);
        int color = getResources().
                getColor(R.color.lightpink);
        //设置所有的条目的颜色与背景色一致
        int visStart = mExpandableListView.getFirstVisiblePosition();
        int visEnd = mExpandableListView.getLastVisiblePosition();
        View viewTemp;
        for (int i1 = 0; i1 <= visEnd - visStart; i1++)
        {
            viewTemp = mExpandableListView.getChildAt(i1);
            viewTemp.setBackgroundColor(color1);
        }
        //设置点击条目颜色变化
        v.setBackgroundColor(color);

    }

    public interface Item2OnClickListener{
        /***
         * 用户点击了子条目
         * @param groupPosition:用户所点击的该组的条目
         * @param childPosition：用户所点击的该组子项条目
         */
        public void item2OnClikListenr(int groupPosition, int childPosition);
    }
}
