package cn.it.sales.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cn.it.sales.bean.JpushInfo;
import cn.jpush.android.api.JPushInterface;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/5/12.
 */
public class MyReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(JPushInterface.ACTION_MESSAGE_RECEIVED)){
            Bundle bundle =intent.getExtras();
            String title =bundle.getString(JPushInterface.EXTRA_TITLE);
            String message=bundle.getString(JPushInterface.EXTRA_MESSAGE);

            JpushInfo jpushInfo =new JpushInfo();
            jpushInfo.setName(title);
            jpushInfo.setMessage(message);
            EventBus.getDefault().post(jpushInfo);
        }
    }
}
