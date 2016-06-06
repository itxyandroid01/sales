package cn.it.sales.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.json.JSONException;
import org.json.JSONObject;

import cn.it.sales.communal.Communals;
import cn.it.sales.bean.JpushInfo;
import cn.it.sales.bean.ResultUser;
import cn.it.sales.bean.User;
import cn.it.sales.bll.UserManager;
import cn.it.sales.util.ServerUtil;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/5/4.
 */
public class MyService extends Service{
    public MyService(){

    }
    @Override
    public IBinder onBind(Intent intent) {
        Binder binder=new SalesBinder(this);
        return binder;
    }
    public void selectUserMassageforWeb(User user){
        final String locationpasswoed=user.getPassWord();
        final long group=user.getGroupId();
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("username",user.getUserName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsontext=jsonObject.toString();

        ServerUtil.OnOKHttpListener listener=new ServerUtil.OnOKHttpListener() {
            @Override
            public void onSuccess(String jsonString) {
                try {
                    JSONObject jsonObject1=new JSONObject(jsonString);
                    int result=jsonObject1.getInt("result");
                    if(result==1){
                        String wedpassword=jsonObject1.getString("password");
                        if(wedpassword.equals(locationpasswoed)){
                            if(group==1){
                                User user1=new User();
                                user1.setResultcode(1);
                                user1.setGroupId(group);
                                EventBus.getDefault().post(user1);
                            }
                            if(group==2){
                                User user2=new User();
                                user2.setResultcode(1);
                                user2.setGroupId(group);
                                EventBus.getDefault().post(user2);
                            }
                        }else{
                            User user3=new User();
                            user3.setResultcode(-1);
                            user3.setMessage("密码被修改请从新验证");
                            EventBus.getDefault().post(user3);
                        }
                    }if(result==-1){
                        User user3 = new User();
                        user3.setResultcode(-1);
                        user3.setMessage("无此用户");
                        EventBus.getDefault().post(user3);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(boolean b) {

            }
        };
        try {
            ServerUtil.upJsonStringByPost(Communals.upweb,Communals.SelectCode,jsontext,listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void upLoadJsonInfo(User user){
        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("username",user.getUserName());
            jsonObject.put("password",user.getPassWord());
            jsonObject.put("name",user.getNick());
            jsonObject.put("phoneNumber",user.getphone());
           jsonObject.put("groupid",user.getGroupId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //把json对象转换成字符串
        String jsonText=jsonObject.toString();
        //异步回调
        ServerUtil.OnOKHttpListener listener=new ServerUtil.OnOKHttpListener() {
            //3.7
            @Override
            public void onSuccess(String jsonString) {
                //3.7.1
                try {
                    JSONObject jsonObject1=new JSONObject(jsonString);
                    int result=jsonObject1.getInt("result");
                    if(result==1){
                        //3.7.2.1
                        int result1=jsonObject1.getInt("result");
                        String username=jsonObject1.getString("username");
                        String password=jsonObject1.getString("password");
//                        Log.d("xxx",username);
                        String nick=jsonObject1.getString("nick");
                        long groupid=jsonObject1.getLong("groupid");
                        String message="注册成功";
                        ResultUser resultUser=new ResultUser(result1,username,password,groupid,message,nick);
                       //存入首选项
                        UserManager userManager = new UserManager();
                        userManager.resultToSharedPreference(resultUser);
                        EventBus.getDefault().post(resultUser);
                    }
                    if(result==-1){
                        int result2=jsonObject1.getInt("result");
                        String message="注册失败";
                        ResultUser resultUser=new ResultUser(result2,message);
                        EventBus.getDefault().post(resultUser);
                    }
                    if(result==-2){
                        //3.7.3
                        int result3 = jsonObject1.getInt("result");
                        String message = "用户名已存在";
                        ResultUser resultUser = new ResultUser(result3,message);
                        EventBus.getDefault().post(resultUser);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(boolean b) {

            }
        };
        try {
            ServerUtil.upJsonStringByPost(Communals.upweb,Communals.RegisterCode,jsonText,listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void selectUserNameAndPasswordForWeb(User user){
        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("username",user.getUserName());
            jsonObject.put("password",user.getPassWord());
            String jsonText=jsonObject.toString();
            ServerUtil.OnOKHttpListener listener;
            listener=new ServerUtil.OnOKHttpListener() {
                @Override
                public void onSuccess(String jsonString) {
                    try {
                        JSONObject jsonObject1=new JSONObject(jsonString);
                        int result=jsonObject1.getInt("result");
                        if(result==1){
                            long groupid=jsonObject1.getLong("groupid");
                            String username=jsonObject1.getString("username");
                            String password=jsonObject1.getString("password");

//                            Log.d("xw",""+groupid);
//                            ResultUser resultUser=new ResultUser(username,password,groupid,message,nick);
//                            //存入首选项
//                            UserManager userManager = new UserManager();
//                            userManager.resultToSharedPreference(resultUser);
                                ResultUser resultUser=new ResultUser(result,groupid);
                                EventBus.getDefault().post(resultUser);

                        }
                        if(result==-1){
                            String message="没有此用户";
                            ResultUser resultUser=new ResultUser(result,message);
                            EventBus.getDefault().post(resultUser);
                        }
                        if(result==-2){
                            String message="密码错误";
                            ResultUser resultUser=new ResultUser(result,message);
                            EventBus.getDefault().post(resultUser);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(boolean b) {

                }
            };
            try {
                ServerUtil.upJsonStringByPost(Communals.upweb, Communals.loginCode, jsonText, listener);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void upJpushsend(JpushInfo jpushInfo){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", jpushInfo.getName());
            jsonObject.put("content", jpushInfo.getMessage());
            jsonObject.put("alias", jpushInfo.getAlias());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json=jsonObject.toString();
        ServerUtil.OnOKHttpListener listener=new ServerUtil.OnOKHttpListener() {
            @Override
            public void onSuccess(String jsonString) {

            }

            @Override
            public void onFail(boolean b) {

            }
        };
        try {
            ServerUtil.upJsonStringByPost(Communals.upweb,Communals.jpush,json,listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
