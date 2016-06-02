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
public class HotSales_Adapter_Activity extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        Context mContext;
        List<JiaoBanShangPin> mDataList;
        private int mDianJiAnNiu;

    /**
     * 判断是哪个按钮
     * @param i
     */
        public void dianJiAnNiu(int i){
            mDianJiAnNiu=i;
        }
    public HotSales_Adapter_Activity(){

    }

        public HotSales_Adapter_Activity(List<JiaoBanShangPin> dataList, Context context) {
            mDataList = dataList;
            mContext = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(mContext).inflate(
                    R.layout.salesfragment3_listview,parent,false);
            MyViewHolder viewHolder=new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder= (MyViewHolder) holder;
            JiaoBanShangPin itemData=mDataList.get(position);

            int chuShouShuLiang=itemData.getXiaoShouShuLiang();

            String name=itemData.getName();
            int shouJia=itemData.getShangpinjiage();
            int gongHao=itemData.getGongHao();

            switch (mDianJiAnNiu){
                case 1:
                    viewHolder.mTextView1.setText(""+name);
                    viewHolder.mTextView2.setText("出售数量"+chuShouShuLiang);
                    viewHolder.mTextView3.setText("商品价格"+shouJia);
                    break;
                case 2:
                    viewHolder.mTextView1.setText(""+name);
                    viewHolder.mTextView2.setText("出售数量"+chuShouShuLiang);
                    viewHolder.mTextView3.setText("商品价格"+shouJia);
                    break;
                case 3:
                    viewHolder.mTextView1.setText(""+name);
                    viewHolder.mTextView2.setText("出售数量"+chuShouShuLiang);
                    viewHolder.mTextView3.setText("商品价格"+shouJia);
                    break;
                case 4:
                    viewHolder.mTextView1.setText("工号"+gongHao);
                    viewHolder.mTextView2.setText("出售数量"+chuShouShuLiang);
                    viewHolder.mTextView3.setText("商品价格"+shouJia);
                    break;
                case 5:
                    viewHolder.mTextView1.setText("工号"+gongHao);
                    viewHolder.mTextView2.setText("出售数量"+chuShouShuLiang);
                    viewHolder.mTextView3.setText("商品价格"+shouJia);
                    break;
                case 6:

                    viewHolder.mTextView1.setText("工号"+gongHao);
                    viewHolder.mTextView2.setText("出售数量"+chuShouShuLiang);
                    viewHolder.mTextView3.setText("商品价格"+shouJia);
                    break;
                default:
                    viewHolder.mTextView1.setText(""+name);
                    viewHolder.mTextView2.setText("出售数量"+chuShouShuLiang);
                    viewHolder.mTextView3.setText("商品价格"+shouJia);
            }
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
        class  MyViewHolder extends RecyclerView.ViewHolder{
            TextView mTextView1,mTextView2,mTextView3;
            public MyViewHolder(View itemView) {
                super(itemView);
                mTextView1= (TextView) itemView.findViewById(R.id.textViewRecycler1);
                mTextView2= (TextView) itemView.findViewById(R.id.textViewRecycler2);
                mTextView3= (TextView) itemView.findViewById(R.id.textViewRecycler3);
            }
        }
    }
