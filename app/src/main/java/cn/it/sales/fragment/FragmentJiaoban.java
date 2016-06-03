package cn.it.sales.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.adapter.JiaoJieRecyclerAdapter;
import cn.it.sales.bll.JiaoBanManager;
import cn.it.sales.bean.JiaoBanShiYi;

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
        initRecyclerView();
        initButton();
        return view;
    }
    private void initRecyclerView() {
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerViewJiaoban);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        JiaoBanManager jiaoBanManager=new JiaoBanManager();
        mDataList=jiaoBanManager.readJiaoBanShangPinInfo();
        mJiaobanAdapter=new JiaoJieRecyclerAdapter(mContext,mDataList,false);
        mRecyclerView.setAdapter(mJiaobanAdapter);
    }

    private void initButton() {
        mButton= (Button) view.findViewById(R.id.bt21);
        mEditText= (EditText) view.findViewById(R.id.editText21);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();
//                JiaoBanShangPin jiaoBanShangPin;
//                jiaoBanShangPin = mDataList.get("jiao");
//                jiaoBanShiYi = new JiaoBanShiYi(jiaoBanShangPin.getGongHao(), jiaoBanShangPin.getBanCi(), text, "");
//                Log.d("aa",""+jiaoBanShangPin);
                Toast.makeText(mContext,"你点击了这个按钮",Toast.LENGTH_SHORT).show();
    }
});

    }


}
