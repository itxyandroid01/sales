package cn.it.sales.ball;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.it.sales.application.MyDebug;
import cn.it.sales.bean.ShangPinLeiXing2;
import cn.it.sales.dao.ShangPinLeiXingDao;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ShangPinLeiXingManager {
    private static final String TAG = "ShangPinLeiXingManager";

   ShangPinLeiXingDao mShangPinLeiXingDao=new ShangPinLeiXingDao();

    //读商品分类的方法
    public void readShangPinLeiXing(){
        mShangPinLeiXingDao.readShangPinLeiXing();
    }

    //解析并写入商品分类的方法
//    public  void paserAndWriteShangPinLeiXin(String jsonString){
//        if(MyDebug.DEMO_ParseAndWriteShangPinLeiXing){
//            jsonString=makeShanPinLeiXinJSONString();
//        }
//    }
//    private String makeShanPinLeiXinJSONString() {
//        //
//        String jsonString="";
//        List<ShangPinLeiXing2> list=new ArrayList<ShangPinLeiXing2>();
//
//        ShangPinLeiXing2 item2;
//
//        item2=new ShangPinLeiXing2(1001,"服装",1,0,"");
//
//        Gson m=new Gson();
//
//
//        return jsonString;
//    }
    /***
     * 从JSON字符串中解析商品分类,并写入到本地数据库
     * @param jsonString: 从服务器中下载的JSON字符串，
     */
    public  void paserAndWriteShangPinLeiXin(String jsonString){
        if(MyDebug.DEMO_ParseAndWriteShangPinLeiXing){
            //模拟一条从服务器下载的JSON字符串
            jsonString=makeShangPinLeiXinJSONString();
        }
        //解析JSON字符串
        Gson gson=new Gson();

        // json字符串转为带泛型的list
        List<ShangPinLeiXing2> list = gson.fromJson(jsonString,
                new TypeToken<List<ShangPinLeiXing2>>() {
                }.getType());
        //测试一下显示的是否正确
        for(ShangPinLeiXing2 item : list){
            Log.d(TAG, "item: "+item.getLeibiebianhao()+item.getLeibiemingcheng());
        }
        //把List数据写入数据库
        ShangPinLeiXingDao dao = new ShangPinLeiXingDao();
        dao.writeShangPinLeiXingToDB(list);
    }
    private String makeShangPinLeiXinJSONString() {
        //生成一个能存放很多条商品分类数据的List=》
        // 造很多条数据放入List=》
        // 把List转换成JSON字符串
        String jsonString="";
        //准备一个List
        List<ShangPinLeiXing2> list=new ArrayList<ShangPinLeiXing2>();

        ShangPinLeiXing2 item;
        //开始造N条数据，并且放入List
        //大类
        item= new ShangPinLeiXing2(1001,"服装",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1001000+i,"服装:"+i,2,1001,"");
            list.add(item);
        }
        item= new ShangPinLeiXing2(1002,"鞋类",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1002000+i,"鞋类:"+i,2,1002,"");
            list.add(item);
        }
        item= new ShangPinLeiXing2(1003,"帽子",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1003000+i,"帽子:"+i,2,1003,"");
            list.add(item);
        }
        item= new ShangPinLeiXing2(1004,"袜子",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1004000+i,"袜子:"+i,2,1004,"");
            list.add(item);
        }
        item= new ShangPinLeiXing2(1005,"大类5",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1005000+i,"大类5:"+i,2,1005,"");
            list.add(item);
        }
        item= new ShangPinLeiXing2(1006,"大类6",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1006000+i,"大类6:"+i,2,1006,"");
            list.add(item);
        }
        item= new ShangPinLeiXing2(1005,"大类7",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1007000+i,"大类7:"+i,2,1007,"");
            list.add(item);
        }
        item= new ShangPinLeiXing2(1008,"大类8",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1008000+i,"大类8:"+i,2,1008,"");
            list.add(item);
        }
        item= new ShangPinLeiXing2(1005,"大类9",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1009000+i,"大类9:"+i,2,1009,"");
            list.add(item);
        }
        item= new ShangPinLeiXing2(1010,"大类10",1,0,"");
        list.add(item);
        //大类中的小类
        for(int i=1;i<=10;i++){
            item= new ShangPinLeiXing2(1010000+i,"大类10:"+i,2,1010,"");
            list.add(item);
        }

        //把List转换为JSON字符串
        Gson gson=new Gson();
        jsonString= gson.toJson(list);
        Log.d(TAG, "jsonString: " + jsonString);
        return jsonString;
    }

}
