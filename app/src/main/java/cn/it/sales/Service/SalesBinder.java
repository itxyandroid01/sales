package cn.it.sales.Service;

import android.os.Binder;

import cn.it.sales.bean.JpushInfo;
import cn.it.sales.bean.User;

/**
 *
 * binder中没有返回值？
 * Created by Administrator on 2016/5/5.
 */
public class SalesBinder extends Binder {
    MyService mMyService;
    public  SalesBinder(MyService myService){
        mMyService=myService;
    }

    public void userRegister(User user){
        //上传用户信息继续3.2
            mMyService.userRegister(user);
    }

    public void selectUserMessage(User user){
        mMyService.selectUserMassageforWeb(user);
    }
    public  void selectUserNameAndPassword(User user){
        mMyService.selectUserNameAndPasswordForWeb(user);
    }
    public void upJpushsend(JpushInfo jpushInfo){
        mMyService.upJpushsend(jpushInfo);
    }

    //下载接班数据
    public void downloadShangpinInfo(){
        mMyService.downloadShangpinInfo();
    }
}
