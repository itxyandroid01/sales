package cn.it.sales.dao;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.it.sales.Result.MyResult;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.JiaoBan;
import cn.it.sales.db.MyOpenHelp;

/**
 * Created by Administrator on 2016/5/24.
 */
public class JiaoBanDao {
    MyOpenHelp mDBHelpe= MyApplication.getDb1Help();
//    int mDuiBanBanCi=MyApplication.getmDuiBanBanCi();
    public void writeJiaoBanShangPinToDB(List<JiaoBan> list) {
        //先把List中每一条数据转换成一条SQL语句，然后批量插入到数据库
        List<String> sqlList = new ArrayList<String>();
        for (JiaoBan item : list) {
            String sql = String.format(
                    "insert into t_jiaoban (" +
                            "banci,   gonghao," +
                            "xingming," +
                            "jiebangonghao,    jiebanshijian," +
                            "xiaoshoushuliang,    xiaoshoujine," +
                            "jiaobangonghao,    jiaobanshijian,"+
                            "shangchuan,    shangchuanshijian)" +
                            " values (%d,%d,    '%s',    %d,'%s',    %d,%d,  %d,'%s',   %d,'%s')",
                    item.getBanCi(), item.getGongHao(),
                    item.getXingMing(),
                    item.getJieBanGongHao(), item.getJieBanShiJian(),
                    item.getXiaoShouShuLiang(), item.getXiaoShouJinE(),
                    item.getJiaoBanGongHao(),item.getJiaoBanShiJian(),
                    item.getShangChuan(),item.getShangChuanShiJian());
            sqlList.add(sql);
        }
        mDBHelpe.batchExecuteSQL(sqlList);
    }

    public MyResult readJiaoBanByBanci(int gonghao, int banci,String xingming){

        MyResult myResult=new MyResult();
        String sql="select * from t_jiaoban order by banci";
        List<Map<String,Object>> mapList=mDBHelpe.examine(sql);
        //获取对班的班次
        int jiebanbanci=(int) mapList.get(mapList.size()-1).get("banci");
        for (Map<String,Object>  item:mapList){
            int gonghao1 = (int) item.get("gonghao");
            int i = (int) item.get("banci");
            int jiaobanzhuangtai = (int) item.get("jiaobanzhuangtai");

            if (gonghao1 == gonghao) {
                if (jiaobanzhuangtai == 1) {
                    myResult.setCodeAndMessage(2, "已经上班了，请不要重复接班");
                } else if (jiaobanzhuangtai == 2) {
                    if (banci == i) {
                        myResult.setCodeAndMessage(1, "本班次已交班");
                    } else {
                        writeBanCiToDb(gonghao, banci, xingming);
                        myResult.setCodeAndMessage(0, "正常上班");
                    }
                }
            }else if(jiaobanzhuangtai==1){
                myResult.setCodeAndMessage(2, "对班还没有交班，请不要着急");
            }else {
                writeBanCiToDb(gonghao,banci,xingming);
                myResult.setCodeAndMessage(0,"你是第一次来上班，加油！");
            }
            if(item.isEmpty()){}
        }
        Log.d("ceshi", "" + myResult.getMessage());
//        int duibanbanci=(int) mapList.get(mapList.size()-1).get("banci");
//        Log.d("ceshi", "duibanbanhao" + duibanbanci);
        return myResult;
    }

    private void writeBanCiToDb(int gonghao, int banci, String xingming) {
        String sql="insert into t_jiaoban(banci,gonghao,jiaobanzhuangtai,xingming)" +
                " values ("+banci+","+gonghao+",1,'"+xingming+"')";
        mDBHelpe.examine(sql);

    }

    public void writJiaoBanInfoToDB(List<JSONObject> dataList) {
    }

    public void InsertDataToDB(JSONObject jsonObject1) {
        List<String> list1=new ArrayList<String>() ;
        try {
            int banci= (int) jsonObject1.get("banci");
            int gonghao= (int) jsonObject1.get("gonghao");
            String xingming= (String) jsonObject1.get("xingming");
            String jiebangonghao= (String) jsonObject1.get("jiebangonghao");
            String jiebanshijian= (String) jsonObject1.get("jiebanshijian");
            int xiaoshoushuliang= (int) jsonObject1.get("xiaoshoushuliang");
            String shangpinbianhao= (String) jsonObject1.get("shangpinbianhao");
            int jiaobanshuliang= (int) jsonObject1.get("jiaobankucunliang");

            String sql1 = String.format("insert into  t_jiaoban (" +
                            "banci,gonghao,xingming,jiebangonghao" +
                            "jiebanshijian,jiaobaogonghao)" +
                            "values(%d ,%d, %s,%d,%d,%s,%d)",
                    banci,jiebangonghao, xingming, jiebangonghao,
                    jiebanshijian, gonghao
            );
            String sql2=String.format("insert into t_jiaoban_shangpin(" +
                    "banci,gonghao,shangpinbianhao,jiebankucunliang)" +
                    "values(%d,%d,'%s',%d)",banci,jiebangonghao,jiaobanshuliang);

            String sql3=String.format("insert into  t_jiaoban_shiyi (banci,gonghao,jiaobanshiyi) values(banci,gonghao)");
            list1.add(sql1);
            list1.add(sql2);
            list1.add(sql3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mDBHelpe.batchExecuteSQL(list1);
    }
}
