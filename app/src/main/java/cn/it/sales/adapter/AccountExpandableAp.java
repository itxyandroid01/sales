package cn.it.sales.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.it.sales.R;

/**
 * Created by Administrator on 2016/5/18.
 */
public class AccountExpandableAp extends BaseExpandableListAdapter {
    List<String> mGroupArray;
    List<List<String>> mChildArray;
    Activity mActivity;
    public AccountExpandableAp(Activity activity, List<String> list1, List<List<String>> list2) {
        mGroupArray = list1;
        mChildArray = list2;
        mActivity = activity;
    }
//获取父类的条目长度
    @Override
    public int getGroupCount() {
        return mGroupArray.size();
    }
    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildArray.get(groupPosition).size();
    }
    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return mGroupArray.get(groupPosition);
    }
//获得子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildArray.get(groupPosition).get(childPosition);
    }
    //得到子item的ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
//获取子类的条目id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    //设置父item组件
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.expandable_tem,null);
        }
        ImageView imageView=(ImageView) convertView.findViewById(R.id.Imagezu);
        if(!isExpanded){
           imageView.setImageResource(R.mipmap.jt01);
        }else{
            imageView.setImageResource(R.mipmap.jt02);
        }
//
//        imageView.setImageResource(R.mipmap.ic_launcher);
        TextView textView=(TextView) convertView.findViewById(R.id.textzu);
        textView.setText(mGroupArray.get(groupPosition));
        return convertView;
    }
    //设置子item的组件
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String s=mChildArray.get(groupPosition).get(childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.expandable_ziitem,null);
        }
        TextView textView=(TextView) convertView.findViewById(R.id.textzi);
        textView.setText(s);
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
