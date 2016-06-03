package cn.it.sales.dao;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import cn.it.sales.application.MyApplication;
import cn.it.sales.db.MyOpenHelp;

/**
 * Created by Administrator on 2016/5/24.
 */
public class JiaoBanShiYiDao {
    MyOpenHelp mDBHelpe= MyApplication.getDb1Help();

    public String selectJieBanShiYiInfo(int jiebanbanci) {
        String sql="select * from t_jiaoban_shiyi ";
        List<Map<String,Object>> mapList=mDBHelpe.examine(sql);
        for (Map<String,Object> item:mapList){
            int banci= (int) item.get("banci");
            if (banci==jiebanbanci){
                String jiebanshiyi  = (String) item.get("jiaobanshiyi");
                return jiebanshiyi;
            }
        }
        return "查找失败";
    }

    public void writJiaoBanInfoToDB(List<JSONObject> dataList) {

    }
}
