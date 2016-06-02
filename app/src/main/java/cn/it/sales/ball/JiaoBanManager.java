package cn.it.sales.ball;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.it.sales.Result.MyResult;
import cn.it.sales.bean.JiaoBanShangPin;
import cn.it.sales.dao.JiaoBanDao;
import cn.it.sales.dao.JiaoBanShangPinDao;
import cn.it.sales.dao.JiaoBanShiYiDao;

/**
 * Created by Administrator on 2016/5/24.
 */
public class JiaoBanManager {

    public List<JSONObject> readJiaoBanShangPinInfo(){
        List<JSONObject> list=new ArrayList<JSONObject>();
        JiaoBanShangPinDao jiaoBanShangPinDao=new JiaoBanShangPinDao();
            list=jiaoBanShangPinDao.readJiaoBanShangPinInfo();
        return list;
    }

    private List<JiaoBanShangPin> newJiaoBanShangPinDemo() {
        List<JiaoBanShangPin> list=new ArrayList<JiaoBanShangPin>();
        for (int i=1;i<10;i++){
            JiaoBanShangPin jiaoBanShangPin=new JiaoBanShangPin(1001,001,""+1001001+(1000+i),1000+i,800+i,200,200+i);
            list.add(jiaoBanShangPin);
        }
        Log.d("qa",""+list);
        JiaoBanShangPinDao jiaoBanShangPinDao =new JiaoBanShangPinDao();
        jiaoBanShangPinDao.writeJiaoBanShangPinToDB(list);
        return list;
    }

    public String selectJieBanShiYiInfo(int jiebanbanci) {
        JiaoBanShiYiDao jiaoBanShiYiDao=new JiaoBanShiYiDao();
        String jieBanShiYi=jiaoBanShiYiDao.selectJieBanShiYiInfo(jiebanbanci);
        return jieBanShiYi;
    }

    public MyResult readJiaobanByBanCi(int gonghao, int banci,String xingming) {
        JiaoBanDao jiaoBanDao = new JiaoBanDao();
        return jiaoBanDao.readJiaoBanByBanci(banci, gonghao, xingming);
    }

    public int selectJieBanBanCi() {
        JiaoBanShangPinDao jiaoBanShangPinDao=new JiaoBanShangPinDao();
        return jiaoBanShangPinDao.selectJieBanBanCi();
    }

    public List<JSONObject> readJiaoBanShangPinByJieBan(int jiebanbanci) {
        JiaoBanShangPinDao jiaoBanShangPinDao=new JiaoBanShangPinDao();
        return jiaoBanShangPinDao.readJiaoBanShangPinByJieBan(jiebanbanci);

    }

    public MyResult writInfoToDB(JSONObject dataList) {
        MyResult myResult=new MyResult();
        JiaoBanDao jiaoBanDao = new JiaoBanDao();
        jiaoBanDao.InsertDataToDB(dataList);

        myResult.setCode(1);

        return myResult;
//        JiaoBanDao jiaoBanDao=new JiaoBanDao();
//        jiaoBanDao.writJiaoBanInfoToDB(dataList);
//        JiaoBanShangPinDao jiaoBanShangPinDao=new JiaoBanShangPinDao();
//        jiaoBanShangPinDao.writeJiaoBanShangPinInfoToDB(dataList);
//        JiaoBanShiYiDao jiaoBanShiYiDao=new JiaoBanShiYiDao();
//        jiaoBanShiYiDao.writJiaoBanShiYiInfoToDB(dataList);

    }
}
