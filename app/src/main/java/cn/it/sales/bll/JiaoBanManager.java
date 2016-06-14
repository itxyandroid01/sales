package cn.it.sales.bll;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.it.sales.Result.MyResult;
import cn.it.sales.application.MyDebug;
import cn.it.sales.bean.JiaoBanShangPin;
import cn.it.sales.bean.JiaoBanShiYi;
import cn.it.sales.bean.JieBanInfo;
import cn.it.sales.dao.JiaoBanDao;
import cn.it.sales.dao.JiaoBanShangPinDao;
import cn.it.sales.dao.JiaoBanShiYiDao;

/**
 * Created by Administrator on 2016/5/24.
 */
public class JiaoBanManager {

    //读交班商品信息
    public List<JiaoBanShangPin> readJiaoBanShangPinInfo(){
        JiaoBanShangPinDao jiaoBanShangPinDao=new JiaoBanShangPinDao();
        List<JiaoBanShangPin>   list=jiaoBanShangPinDao.readJiaoBanShangPinInfo();
        return list;
    }

//模拟数据写入交班商品
//    private List<JiaoBanShangPin> newJiaoBanShangPinDemo() {
//        List<JiaoBanShangPin> list=new ArrayList<JiaoBanShangPin>();
//        for (int i=1;i<10;i++){
//            JiaoBanShangPin jiaoBanShangPin=new JiaoBanShangPin(1001,001,""+1001001+(1000+i),1000+i,800+i,200,200+i);
//            list.add(jiaoBanShangPin);
//        }
//        Log.d("qa",""+list);
//        JiaoBanShangPinDao jiaoBanShangPinDao =new JiaoBanShangPinDao();
//        jiaoBanShangPinDao.writeJiaoBanShangPinToDB(list);
//        return list;
//    }

    //根据接班班次查询接班事宜
    public String selectJieBanShiYiInfo(int jiebanbanci) {
        JiaoBanShiYiDao jiaoBanShiYiDao=new JiaoBanShiYiDao();
        String jieBanShiYi=jiaoBanShiYiDao.selectJieBanShiYiInfo(jiebanbanci);
        return jieBanShiYi;
    }

    //根据班次查询时候接班
    public MyResult readJiaobanByBanCi(int gonghao, int banci,String xingming) {
        JiaoBanDao jiaoBanDao = new JiaoBanDao();
        return jiaoBanDao.readJiaoBanByBanci(banci, gonghao, xingming);
    }
    //查询对班班次
    public int selectJieBanBanCi() {
        JiaoBanShangPinDao jiaoBanShangPinDao=new JiaoBanShangPinDao();
        return jiaoBanShangPinDao.selectJieBanBanCi();
    }

     //根据接班班次读接班数据并显示在界面
    public List<JiaoBanShangPin> readJiaoBanShangPinByJieBan(int jiebanbanci) {
        JiaoBanShangPinDao jiaoBanShangPinDao=new JiaoBanShangPinDao();
        return jiaoBanShangPinDao.readJiaoBanShangPinByJieBan(jiebanbanci);

    }

    //接班记录写入到本地数据库
    public MyResult writInfoToDB(JieBanInfo jieBanInfo) {
        JiaoBanDao jiaoBanDao = new JiaoBanDao();
        return  jiaoBanDao.InsertDataToDB(jieBanInfo);
    }
    //从服务器下载的数据放入到本地数据库
    public  void paserAndWriteShangPinLeiXin(String jsonString){
        if(MyDebug.DEMO_ParseAndWritejiebaninfo){
            //模拟一条从服务器下载的JSON字符串
            jsonString=makejiebanJSONString();
        }
        //解析JSON字符串
        Gson gson=new Gson();

        // json字符串转为带泛型的list
        List<JieBanInfo> list = gson.fromJson(jsonString,
                new TypeToken<List<JieBanInfo>>() {
                }.getType());
        //测试一下显示的是否正确
        for(JieBanInfo item : list){
            Log.d("ceshijieban", "item: "+item.getLeibiemingcheng()+item.getLeiBieBianHao());
        }
        //把List数据写入数据库
        JiaoBanDao dao = new JiaoBanDao();
        dao.writeJiebaninfoToDB(list);
    }

    //模拟下载的数据
    private String makejiebanJSONString() {
        //生成一个能存放很多店铺接班数据的List=》
        String jsonString="";
        //准备一个List
        List<JieBanInfo> list=new ArrayList<JieBanInfo>();
        JieBanInfo item;
        //开始造N条数据，并且放入List
        for(int i=0;i<=10;i++){
            item= new JieBanInfo(20161001+i,1001+i,"张丹"+i,1000+i,"",20+i,5000+i,1001+i,"",1+i,"","牛顿国际"+i,2,"10001001"+i,
                    300+i,200+i,"好好工作，没什么事宜","没有事宜",100200+i,"名称","全称","没有描述","规格","型号",
                    100+i,8,200+i,"销售建议"+i,"图片名称"+i,"支持活动"+i,"货架位置"+i,"类别名称"+i,1,10001+i,"",88+i,66+i,"没有活动",1+i);
            list.add(item);
        }
        for(int i=0;i<=10;i++){
            item= new JieBanInfo(20161001+i,1001+i,"张丹"+i,1000+i,"",20+i,5000+i,1001+i,"",1+i,"","牛顿国际"+i,2,"10001001"+i,
                    300+i,200+i,"好好工作，没什么事宜","没有事宜",100200+i,"名称","全称","没有描述","规格","型号",
                    100+i,8,200+i,"销售建议"+i,"图片名称"+i,"支持活动"+i,"货架位置"+i,"类别名称"+i,2,10001+i,"",88+i,66+i,"没有活动",1+i);
            list.add(item);
        }

        //把List转换为JSON字符串
        Gson gson=new Gson();
        jsonString= gson.toJson(list);
        Log.d("ceshi", "jsonString: " + jsonString);
        return jsonString;
    }


    //生成的交班信息记录到本地数据库
    public MyResult updateJiaoBanInfoToDb(JiaoBanShiYi jiaoBanShiYi, List<JiaoBanShangPin> list) {
        JiaoBanShiYiDao dao=new JiaoBanShiYiDao();
        return dao.updateJiaoBanInfoToDb(list,jiaoBanShiYi);
    }
}
