package cn.it.sales.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.it.sales.application.MyApplication;

/**
 * Created by Administrator on 2016/6/3.
 * 本Activity是其他Activity的基类
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加本Activity到列表中
        MyApplication.getMyApplication().addActivity(this);
    }

    @Override
    public void finish() {
        //从列表中移除本Activity
        MyApplication.getMyApplication().removeActivity(this);

        super.finish();
    }
}
