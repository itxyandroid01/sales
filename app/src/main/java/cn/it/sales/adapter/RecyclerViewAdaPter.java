package cn.it.sales.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.bean.SalesMessage;

/**
 * Created by Administrator on 2016/5/25.
 */
public class RecyclerViewAdaPter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    List<SalesMessage> mList1;


    public RecyclerViewAdaPter(Activity activity, List<SalesMessage> dataList) {
        mContext = activity;
        mList1 = dataList;
    }

//    public void setDataListAndReFresh(List<JiLuXiaoShou> dataListAndReFresh) {
//        mList1 =dataListAndReFresh;
//        this.notifyDataSetChanged();
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xiaoshoujilu, null);
        AccountViewHolder viewHolder = new AccountViewHolder(view);
        //将创建的View注册点击事件
//        view.setOnClickListener((View.OnClickListener) this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        final AccountViewHolder viewHolder = (AccountViewHolder) holder;

        final SalesMessage salesMessage = mList1.get(position);

        String mShangpinMingCheng = salesMessage.getShangPinMingCheng();
        int mXiaoShouShuLiang = salesMessage.getXiaoShouShuLiang();
        int mXiaoShouJinE = salesMessage.getXiasoShouJiaGe() * mXiaoShouShuLiang;
        String mXiaoShouShiJian = salesMessage.getXiaoShouShiJIan();

        viewHolder.mShangPinMingCheng.setText(mShangpinMingCheng);
        viewHolder.mXiaoShouShuLiang1.setText("        " + mXiaoShouShuLiang);
        viewHolder.mXiaoShouJinE2.setText("        " + mXiaoShouJinE);
        viewHolder.mXiaoShouShiJian3.setText(mXiaoShouShiJian);

//        viewHolder.mTextView1.setText(" ");
//        viewHolder.mTextView2.setText("  ");
//        viewHolder.mTextView3.setText("       ");
//        viewHolder.mTextView4.setText("                 ");

    }

    @Override
    public int getItemCount() {
        return mList1.size();
    }


    class AccountViewHolder extends RecyclerView.ViewHolder {
        //TextView mShangPinMingCheng;
        TextView mShangPinMingCheng, mXiaoShouShuLiang1, mXiaoShouJinE2, mXiaoShouShiJian3, mTextView1, mTextView2, mTextView3, mTextView4;

        public AccountViewHolder(View itemView) {
            super(itemView);
            mShangPinMingCheng = (TextView) itemView.findViewById(R.id.mShangPinMingCheng1);
            mXiaoShouShuLiang1 = (TextView) itemView.findViewById(R.id.mXiaoShouShuLiang1);
            mXiaoShouJinE2 = (TextView) itemView.findViewById(R.id.mXiaoShouJinE2);
            mXiaoShouShiJian3 = (TextView) itemView.findViewById(R.id.mXiaoShouShiJian3);

            //  mTextView1= (TextView) itemView.findViewById(R.id.mTextView1);
            //  mTextView2= (TextView) itemView.findViewById(R.id.mTextView2);
            //     mTextView3= (TextView) itemView.findViewById(R.id.mTextView3);
            //  mTextView4= (TextView) itemView.findViewById(R.id.mTextView4);
        }
    }
}
