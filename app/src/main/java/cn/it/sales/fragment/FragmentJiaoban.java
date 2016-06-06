package cn.it.sales.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.Result.MyResult;
import cn.it.sales.adapter.JiaoJieRecyclerAdapter;
import cn.it.sales.bean.JiaoBanShangPin;
import cn.it.sales.bean.JiaoBanShiYi;
import cn.it.sales.bll.JiaoBanManager;
import cn.it.sales.bll.SalesJiLuManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentJiaoban extends Fragment {
    View view;
    EditText mEditText;
    Context mContext;
    Button mButton;
    JiaoBanShiYi jiaoBanShiYi;
    JiaoJieRecyclerAdapter mJiaobanAdapter;
    RecyclerView mRecyclerView;
    List<JSONObject> mDataList;
    public FragmentJiaoban() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.sales_jiaoban, container, false);
        mContext=getActivity();
        //初始化RecyclerView
        initRecyclerView();
        //初始化确认加班
        initButton();
        return view;
    }
    private void initRecyclerView() {
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerViewJiaoban);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        JiaoBanManager jiaoBanManager=new JiaoBanManager();
        //获取RecyclerView的数据源
        mDataList=jiaoBanManager.readJiaoBanShangPinInfo();
        //传递给适配器【false表示不是接班】
        mJiaobanAdapter=new JiaoJieRecyclerAdapter(mContext,mDataList,false);
        mRecyclerView.setAdapter(mJiaobanAdapter);
    }

    private void initButton() {
        mButton= (Button) view.findViewById(R.id.bt21);
        mEditText= (EditText) view.findViewById(R.id.editText21);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的交班事宜
                String text = mEditText.getText().toString();
                //判断交班事宜是否为空
                if (TextUtils.isEmpty(text)){
                    Toast.makeText(mContext,"请输入交班事项",Toast.LENGTH_SHORT).show();
                }else {

                    SalesJiLuManager salesJiLuManager=new SalesJiLuManager();
                    //读取商品的销售信息
                    List<JiaoBanShangPin> list=salesJiLuManager.readXiaoShouInfo();
                    //商品事宜放到实体类
                    JiaoBanShiYi jiaoBanShiYi=new JiaoBanShiYi(text);
                    JiaoBanManager jiaoBanManager=new JiaoBanManager();
                    //把交班信息放入本地数据库
                    MyResult myResult=jiaoBanManager.updateJiaoBanInfoToDb(jiaoBanShiYi,list);
                    int code=myResult.getCode();
                    //根据回调参数判断是否成功
                    if (code>0){
                        Toast.makeText(mContext,"交班成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mContext,"交班失败",Toast.LENGTH_SHORT).show();
                    }
                }


    }
});

    }


}
