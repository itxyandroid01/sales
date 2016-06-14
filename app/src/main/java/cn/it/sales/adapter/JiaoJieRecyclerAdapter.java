package cn.it.sales.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.bean.JiaoBanShangPin;

/**
 * Created by Administrator on 2016/5/23.
 */
public class JiaoJieRecyclerAdapter extends RecyclerView.Adapter<JiaoJieRecyclerAdapter.MyViewHolder> {
    List<JiaoBanShangPin> mDataList;
    Context mContext;
    //定义一个变量判断是否是接班
    boolean mIsJieBan;

    public JiaoJieRecyclerAdapter(Context context, List<JiaoBanShangPin> dataList, boolean isJieBan) {
        mContext=context;
        mDataList = dataList;
        mIsJieBan = isJieBan;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder( LayoutInflater.from(mContext).
                inflate(R.layout.jiaojie_item,parent,false ));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JiaoBanShangPin jiaoBanShangPin= mDataList.get(position);
        //如果是接班刷新接班数据显示
        if (mIsJieBan) {
                holder.mTextView1.setText(""+jiaoBanShangPin.getBanCi());
                holder.mTextView2.setText(jiaoBanShangPin.getMingcCheng());
                holder.mTextView3.setText(""+jiaoBanShangPin.getJiaoBanKuCunLiang());
                holder.mTextView4.setText(""+jiaoBanShangPin.getXiaoShouShuLiang());

       //否则，刷新交班数据显示
        } else {

                holder.mTextView1.setText(jiaoBanShangPin.getMingcCheng());
                holder.mTextView2.setText(""+jiaoBanShangPin.getJieBanKuCunLiang());
                holder.mTextView3.setText(""+jiaoBanShangPin.getJiaoBanKuCunLiang());
                holder.mTextView4.setText(""+jiaoBanShangPin.getXiaoShouShuLiang());

        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView1, mTextView2, mTextView3, mTextView4;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView1 = (TextView) itemView.findViewById(R.id.item_name);
            mTextView2 = (TextView) itemView.findViewById(R.id.item_jiebankucun);
            mTextView3 = (TextView) itemView.findViewById(R.id.item_jiaobankucun);
            mTextView4 = (TextView) itemView.findViewById(R.id.item_xiaoshoushuliang);
        }
    }
}
