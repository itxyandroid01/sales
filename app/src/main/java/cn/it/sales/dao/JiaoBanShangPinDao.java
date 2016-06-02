package cn.it.sales.dao;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.JiaoBanShangPin;
import cn.it.sales.db.MyOpenHelp;

/**
 * Created by Administrator on 2016/5/24.
 */
public class JiaoBanShangPinDao {
    MyOpenHelp mDBHelpe;

    public JiaoBanShangPinDao() {
        mDBHelpe = MyApplication.getDb1Help();
    }

    public void writeJiaoBanShangPinToDB(List<JiaoBanShangPin> list) {
//        int row = 0;
        //先把List中每一条数据转换成一条SQL语句，然后批量插入到数据库
        List<String> sqlList = new ArrayList<String>();
        for (JiaoBanShangPin item : list) {
            String sql = String.format(
                    "insert into t_jiaoban_shangpin (" +
                            "banci,   gonghao," +
                            "shangpinbianhao," +
                            "jiebankucunliang,    xiaoshoushuliang," +
                            "xiaoshoujine,    jiaobankucunliang," +
                            " values (%d,%d,    '%s',    %d,%d,    %d,%d,  '%s')",
                    item.getBanCi(), item.getGongHao(),
                    item.getShangPinBianHao(),
                    item.getJieBanKuCunLiang(), item.getXiaoShouShuLiang(),
                    item.getXiaoShouJinE(), item.getJiaoBanKuCunLiang());
            sqlList.add(sql);
        }
        mDBHelpe.batchExecuteSQL(sqlList);
//            return row;

    }

    public List<JiaoBanShangPin> readJiLuXiaoShouByXiaoShouShuLiang(String sql) {
        List<Map<String, Object>> mapList = mDBHelpe.examine(sql);

        return mapListToXiaoShouList(mapList);
    }

    private List<JiaoBanShangPin> mapListToXiaoShouList(List<Map<String, Object>> mapList) {

        List<JiaoBanShangPin> list = new ArrayList<>();
        if (mapList == null) {
            return list;
        }

        for (Map<String, Object> item : mapList) {
            JiaoBanShangPin jiaoBanShangPin = new JiaoBanShangPin();
//            jiaoBanShangPin.setName((String) item.get("mingcheng"));
//            jiaoBanShangPin.setShangpinjiage((int) item.get("lingshoujia"));
            jiaoBanShangPin.setXiaoShouShuLiang((int) item.get("xiaoshoushuliang"));
//            jiaoBanShangPin.setGonghao((int) item.get("gonghao"));
//            jiaoBanShangPin.setJiebankucunliang((int) item.get("jiebankucunliang"));
//            jiaoBanShangPin.setBanci((int) item.get("banci"));
//            jiaoBanShangPin.setXiaoshoujine((int) item.get("xiaoshoujine"));
//            jiaoBanShangPin.setJiaobankucunliang((int) item.get("jiaobankucunliang"));
//
//            jiaoBanShangPin.setShangpinbianhao((String) item.get(item.get("shangpinbianhao")));

            list.add(jiaoBanShangPin);
        }
        return list;
    }

    public List<JSONObject> readJiaoBanShangPinInfo(){
        List<JSONObject> objectList=new ArrayList<JSONObject>();
        String sql="select   xs.shangpinbianhao,sp.mingcheng,    " +
                "   xs.jiebankucunliang,xs.jiaobankucunliang,xs.xiaoshoushuliang    " +
                "      from t_jiaoban_shangpin xs , t_shangpin sp " +
                "   where xs.shangpinbianhao = sp.shangpinbianhao" ;
        List<Map<String,Object>> mapList=mDBHelpe.examine(sql);
        for(Map<String,Object> item : mapList){

            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("mingcheng",item.get("mingcheng"));
                jsonObject.put("xiaoshoushuliang",item.get("xiaoshoushuliang"));
                jsonObject.put("jiaobankucunliang",item.get("jiaobankucunliang"));
                jsonObject.put("jiebankucunliang",item.get("jiebankucunliang"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            objectList.add(jsonObject);
        }
        return objectList;
    }
    public List<JiaoBanShangPin> readJiaoBanShangPinList() {
        String sql="select *  from t_jiaoban_shangpin order by banci";
        List<Map<String,Object>>  mapList=mDBHelpe.examine(sql);
        return mapListToShangPinList(mapList);
    }

    private List<JiaoBanShangPin> mapListToShangPinList(List<Map<String, Object>> mapList) {
        List<JiaoBanShangPin> list =new ArrayList<JiaoBanShangPin>();
        if(mapList ==null){
            return list;
        }
        for(Map<String,Object> item : mapList){
            JiaoBanShangPin jiaoBanShangPin=new JiaoBanShangPin();
            jiaoBanShangPin.setBanCi((Integer) item.get("banci"));
            jiaoBanShangPin.setGongHao((Integer) item.get("gonghao"));
            jiaoBanShangPin.setShangPinBianHao((String) item.get("shangpinbianhao"));
            jiaoBanShangPin.setJieBanKuCunLiang((Integer) item.get("jiebankucunliang"));
            jiaoBanShangPin.setXiaoShouShuLiang((Integer) item.get("xiaoshoushuliang"));
            jiaoBanShangPin.setXiaoShouJinE((Integer) item.get("xiaoshoujine"));
            jiaoBanShangPin.setJiaoBanKuCunLiang((Integer) item.get("jiaobankucunliang"));
            list.add(jiaoBanShangPin);
        }
//        Log.d("qa1", "" + list);
        return list;
    }

    public List<JSONObject> readJiaoBanShangPinByJieBan(int banci) {
        List<JSONObject> objectList=new ArrayList<JSONObject>();
            String sql="select   xs.shangpinbianhao,sp.mingcheng,    " +
                    "                  xs.jiebankucunliang,xs.jiaobankucunliang,xs.xiaoshoushuliang  , xs.banci  " +
                    "                     from t_jiaoban_shangpin xs , t_shangpin sp ,t_jiaoban jb" +
                    "                 where xs.shangpinbianhao = sp.shangpinbianhao ";
        List<Map<String,Object>> mapList=mDBHelpe.examine(sql);
        for(Map<String,Object> item : mapList){
            int banci1= (int) item.get("banci");
            if (banci==banci1){
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("mingcheng",item.get("mingcheng"));
                jsonObject.put("xiaoshoushuliang",item.get("xiaoshoushuliang"));
                jsonObject.put("jiaobankucunliang",item.get("jiaobankucunliang"));
                jsonObject.put("jiebankucunliang",item.get("jiebankucunliang"));
                jsonObject.put("banci",banci);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            objectList.add(jsonObject);
        }}
        return objectList;
    }
    public int selectJieBanBanCi() {
        String sql="select * from t_jiaoban_shangpin order by banci";
        List<Map<String,Object>> mapList=mDBHelpe.examine(sql);
        int jiebanbanci= (int) mapList.get(mapList.size()-1).get("banci");
        Log.d("ceshi", "" + jiebanbanci);
        return jiebanbanci;
    }

    public void writeJiaoBanShangPinInfoToDB(List<JSONObject> dataList) {
    }
}
