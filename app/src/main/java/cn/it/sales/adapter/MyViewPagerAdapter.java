package cn.it.sales.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter{

    List<Fragment> mDataList;
    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> dataList) {
        super(fm);
        mDataList=dataList;
    }
    @Override
    public Fragment getItem(int position) {
        return mDataList.get(position);
    }
    @Override
    public int getCount() {
        return mDataList.size();
    }
}
