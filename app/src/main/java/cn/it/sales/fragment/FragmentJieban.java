package cn.it.sales.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.Result.MyResult;
import cn.it.sales.adapter.JiaoJieRecyclerAdapter;
import cn.it.sales.application.MyApplication;
import cn.it.sales.ball.JiaoBanManager;
import cn.it.sales.communal.Communals;
import cn.it.sales.util.DateTimeUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentJieban extends Fragment {
    TextView mTextView;
    Button mButton1,mButton2;
    String mJiebanshiyi;
    View rootView;
    Context mContext;
    List<JSONObject> mDataList;
    JiaoJieRecyclerAdapter mJiaoJieRecyclerAdapter;
    RecyclerView mRecyclerView;
    JiaoBanManager mJiaoBanManager;
    int mCount=0;
    boolean ONE=true;
    public FragmentJieban() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.jieban_fragment, container, false);
        mContext=getActivity();
        mJiaoBanManager=new JiaoBanManager();
        int jieBanBanCi=mJiaoBanManager.selectJieBanBanCi();
        initshiyi(jieBanBanCi);
        initRecyclerView(jieBanBanCi);
        initButton();

        return rootView;

    }

    private void initButton() {

//        mButton1=(Button)rootView.findViewById(R.id.kaishijieban);
        mButton2=(Button)rootView.findViewById(R.id.querenjieban);
//        mButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mJiaoBanManager=new JiaoBanManager();
//                int jieBanBanCi=mJiaoBanManager.selectJieBanBanCi();
//                initshiyi(jieBanBanCi);
//                initRecyclerView(jieBanBanCi);
//            }
//        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJiaoBanManager = new JiaoBanManager();

                writInfoToDB(mDataList);
            }
        });
    }

    private void initshiyi(int jiebanbanci) {
        mTextView=(TextView)rootView.findViewById(R.id.tv11);
        mJiebanshiyi=mJiaoBanManager.selectJieBanShiYiInfo(jiebanbanci);
        mTextView.setText(mJiebanshiyi);
    }

    private void writInfoToDB(List<JSONObject> dataList) {
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(Communals.sharedPreferencesforlogIn,Context.MODE_PRIVATE);
        String  mName=sharedPreferences.getString("xingming", "");
        int jiebangonghao=sharedPreferences.getInt("gonghao", 0);
        String time= DateTimeUtil.getSystemtDateTimeToSQL3GMT0();
        JSONObject jsonObject=new JSONObject();
        JSONObject jsonObject1=new JSONObject();
        jsonObject=mDataList.get(0);
        int banci= MyApplication.getmBanCi();
        Log.d("banci", "writInfoToDB: "+banci);
        try {

            int gonghao= (int) jsonObject.get("gonghao");
            String mingcheng= (String) jsonObject.get("mingcheng");
            String shangpinbianhao = (String) jsonObject.get("shangpinbianhao");
            int jiaobankucunliang = jsonObject.getInt("jiaobankucun");
            jsonObject1.put("banci",banci);
            jsonObject1.put("mingcheng",mingcheng);
            jsonObject1.put("jiebanshiyi",mJiebanshiyi);
            jsonObject1.put("jiebanshijian",time);
            jsonObject1.put("xingming",mName);
            jsonObject1.put("gongghao",gonghao);
            jsonObject1.put("jiebangonghao",jiebangonghao);
            jsonObject1.put("shangpinbianhao",shangpinbianhao);
            jsonObject1.put("jiebankucun",jiaobankucunliang);
            jsonObject1.put("xiaoshoushuliang","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyResult myResult=new MyResult();
        myResult=mJiaoBanManager.writInfoToDB(jsonObject1);
        if (myResult.getCode()==1){
            Toast.makeText(mContext,"插入成功",Toast.LENGTH_SHORT).show();
        }
    }

    private void initRecyclerView(int jiebanbanci) {
        mDataList=mJiaoBanManager.readJiaoBanShangPinByJieBan(jiebanbanci);
        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.recyclerViewJiebao);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mJiaoJieRecyclerAdapter=new JiaoJieRecyclerAdapter(mContext,mDataList,true);
        mRecyclerView.setAdapter(mJiaoJieRecyclerAdapter);
    }

}
