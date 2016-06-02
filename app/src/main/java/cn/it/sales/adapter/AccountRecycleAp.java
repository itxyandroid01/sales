package cn.it.sales.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.it.sales.R;
import cn.it.sales.Result.MyResult;
import cn.it.sales.ball.SalesJiLuManager;
import cn.it.sales.bean.JiLuXiaoShou;
import cn.it.sales.bean.ShangPin;
import cn.it.sales.diglog.SalesDialog;

/**
 * Created by Administrator on 2016/5/18.
 */
public class AccountRecycleAp extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

//    OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener=null;

    Context mContext;
    List<ShangPin> mList;


    public AccountRecycleAp(Context context, List<ShangPin> list){
        mContext=context;
        mList=list;
    }
    public void setDataListAndReFresh(List<ShangPin> dataList){
        mList =dataList;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.account_item,null);
        AccountViewHolder viewHolder =new AccountViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final AccountViewHolder viewHolder= (AccountViewHolder) holder;
        final ShangPin shangPin= mList.get(position);
        //生成的销售记录
        final JiLuXiaoShou jiLuXiaoShou=new JiLuXiaoShou();

        int ks=shangPin.getKuCunShuLiang();
        int ls=shangPin.getLingShouJia();
        int zk=shangPin.getZheKeLv();
        int bh=shangPin.getLeiBieBianHao();

        viewHolder.mTextViewleibie.setText("" + bh);
        viewHolder.mTextViewshangpin.setText(shangPin.getMingCheng());
        viewHolder.mTextViewkucun.setText("" + ks);
        viewHolder.mTextViewlingshou.setText("" + ls);
        viewHolder.mTextViewzhekou.setText("" + zk);

        viewHolder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getLayoutPosition();
                SalesJiLuManager.OnSalesListener salesListener=new SalesJiLuManager.OnSalesListener() {
                    @Override
                    public void onSelectSales(int position) {
                        SalesJiLuManager salesJiLuManager =new SalesJiLuManager();
                        MyResult myResult=salesJiLuManager.makeOneSale(shangPin,jiLuXiaoShou);
//                        Log.d("qa",""+shangPin.getLeiBieBianHao()+"\n"+jiLuXiaoShou.getXiaoShouShuLiang());
                        if(myResult.getCode()==1){
                            Toast.makeText(mContext,"记录成功",Toast.LENGTH_SHORT).show();
                        }
                        if (myResult.getCode()==-1) {
                            Toast.makeText(mContext,""+myResult.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onSelectCancle() {

                    }
                };

//                ShangPin shangPin1=mList.get(position);
                SalesDialog salesDialog=new SalesDialog(mContext,position,shangPin,jiLuXiaoShou,salesListener);
                salesDialog.show();

            }
        });
    }


    @Override
    public int getItemCount() {
           return mList.size();
    }


    class AccountViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewshangpin,mTextViewkucun,mTextViewlingshou,mTextViewzhekou,mTextViewleibie;
        View mItemView;
        ImageView mImageView;
        public AccountViewHolder(View itemView) {
            super(itemView);
            mItemView=itemView;
            mImageView=(ImageView) itemView.findViewById(R.id.imageshangpintupian);
            mTextViewleibie=(TextView) itemView.findViewById(R.id.textleibie);
            mTextViewshangpin= (TextView) itemView.findViewById(R.id.textshangpin);
            mTextViewkucun=(TextView) itemView.findViewById(R.id.textkucun);
            mTextViewlingshou=(TextView) itemView.findViewById(R.id.textlingshou);
            mTextViewzhekou=(TextView) itemView.findViewById(R.id.textzhekou);
        }
    }

}
