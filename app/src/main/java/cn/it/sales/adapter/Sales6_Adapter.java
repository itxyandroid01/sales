package cn.it.sales.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.bean.JpushInfo;

/**
 * Created by Administrator on 2016/5/13.
 */
public class Sales6_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<JpushInfo> mList;
    public Sales6_Adapter(Context context,List<JpushInfo> list){
        mContext=context;
        mList=list;
    }
    public void setDataListMessage(List<JpushInfo> list){
        mList=list;
        this.notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.sales6_item,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        JpushInfo jpushInfo =mList.get(position);
        myViewHolder.mTextViewNick.setText(jpushInfo.getName());
        myViewHolder.mTextViewContent.setText(jpushInfo.getMessage());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewNick;
        TextView mTextViewContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNick = (TextView) itemView.findViewById(R.id.textnick);
            mTextViewContent = (TextView) itemView.findViewById(R.id.textcontent);
        }
    }
}
