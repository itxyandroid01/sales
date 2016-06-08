package cn.it.sales.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.it.sales.R;
import cn.it.sales.adapter.MyViewPagerAdapter;
import cn.it.sales.fragment.SalesFragment1;
import cn.it.sales.fragment.SalesFragment2;
import cn.it.sales.fragment.SalesFragment3;
import cn.it.sales.fragment.SalesFragment4;
import cn.it.sales.fragment.SalesFragment5;
import cn.it.sales.fragment.SalesFragment6;

public class SalesMainActivity extends FragmentActivity {
    GridView mGridView;
    ViewPager mViewPager;
    MyViewPagerAdapter mViewAdapter;
    private String text[];
    private int images[];
    List<Fragment> mDataList;
    SimpleAdapter mSimpleAdapter;
    ArrayList<HashMap<String, Object>> mSalesList;
    Context mContext;
    boolean isclicke=true;
    Fragment mFragment[] = new Fragment[6];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesmain);
//        if(savedInstanceState==null){
//            newAndAttchFragment(0);
//        }
        mContext = this;
        //ViewPager和GridView实现
        initViewPager();
        initGridView();

    }

    private void initViewPager() {
        //ViewPager  数据  适配
        mViewPager = (ViewPager) findViewById(R.id.viewPager01);
        readData();
        mViewAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mDataList);
        mViewPager.setAdapter(mViewAdapter);

    }

    private void readData() {
        mDataList=new ArrayList<Fragment>();
        Fragment item1=new  SalesFragment1();
        mDataList.add(item1);
        Fragment item2=new SalesFragment2();
        mDataList.add(item2);
        Fragment item3=new SalesFragment3();
        mDataList.add(item3);
        Fragment item4=new  SalesFragment4();
        mDataList.add(item4);
        Fragment item5=new SalesFragment5();
        mDataList.add(item5);
        Fragment item6=new  SalesFragment6();
        mDataList.add(item6);
    }


    private void initGridView() {
        mGridView = (GridView) findViewById(R.id.sales_gridView);
        mSalesList = new ArrayList<HashMap<String, Object>>();
        final int[] images = {R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R.mipmap.p4, R.mipmap.p5, R.mipmap.p6};
        String[] text = {"销售记账", "销售业绩", "热销分析", "公司通知", "交接班", "聊天交流"};
        for (int i = 0; i < images.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("images", images[i]);
            map.put("text", text[i]);
            mSalesList.add(map);
        }
        String[] from = {"images", "text"};
        int[] into = {R.id.iv_salesgridView, R.id.tv_salesgridView};
        mSimpleAdapter = new SimpleAdapter(mContext, mSalesList, R.layout.sales_gridview, from, into);
        mGridView.setAdapter(mSimpleAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> item= (HashMap<String, Object>) parent.getItemAtPosition(position);
                //获取数据源的属性值
                String itemText= (String) item.get("text");
                Object objecy=item.get("images");
                int color = getResources().
                        getColor(R.color.lightpink);
//                Toast.makeText(mContext, itemText, Toast.LENGTH_SHORT).show();
                switch (images[position]) {
                    case R.mipmap.p1:

                        mViewPager.setCurrentItem(0);
                        break;
                    case R.mipmap.p2:

                        mViewPager.setCurrentItem(1);
                        break;
                    case R.mipmap.p3:

                        mViewPager.setCurrentItem(2);
                        break;
                    case R.mipmap.p4:

                        mViewPager.setCurrentItem(3);
                        break;
                    case R.mipmap.p5:

                        mViewPager.setCurrentItem(4);
                        break;
                    case R.mipmap.p6:

                        mViewPager.setCurrentItem(5);
                        break;
                }
            }
        });
    }
}






   
