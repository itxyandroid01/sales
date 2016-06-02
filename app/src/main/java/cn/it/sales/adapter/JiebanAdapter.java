package cn.it.sales.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class JiebanAdapter extends RecyclerView.Adapter<JiebanAdapter.MyViewHolder> {
    public JiebanAdapter(Context context, List<JSONObject> dataList) {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
