package cn.it.sales.dao;

import android.util.Log;

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
//修改1
    public JiaoBanShangPinDao() {
        mDBHelpe = MyApplication.getDb1Help();
    }
//模拟数据写入交班商品

//    public void writeJiaoBanShangPinToDB(List<JiaoBanShangPin> list) {
////        int row = 0;
//        //先把List中每一条数据转换成一条SQL语句，然后批量插入到数据库
//        List<String> sqlList = new ArrayList<String>();
//        for (JiaoBanShangPin item : list) {
//            String sql = String.format(
//                    "insert into t_jiaoban_shangpin (" +
//                            "banci,   gonghao," +
//                            "shangpinbianhao," +
//                            "jiebankucunliang,    xiaoshoushuliang," +
//                            "xiaoshoujine,    jiaobankucunliang," +
//                            " values (%d,%d,    '%s',    %d,%d,    %d,%d,  '%s')",
//                    item.getBanCi(), item.getGongHao(),
//                    item.getShangPinBianHao(),
//                    item.getJieBanKuCunLiang(), item.getXiaoShouShuLiang(),
//                    item.getXiaoShouJinE(), item.getJiaoBanKuCunLiang());
//            sqlList.add(sql);
//        }
//        mDBHelpe.batchExecuteSQL(sqlList);
////            return row;
//
//    }

    //读交班商品信息
    public List<JiaoBanShangPin> readJiaoBanShangPinInfo(){
        List<JiaoBanShangPin> list=new ArrayList<JiaoBanShangPin>();
        String sql="select   xs.shangpinbianhao,sp.mingcheng,    " +
                "   xs.jiebankucunliang,xs.jiaobankucunliang,xs.xiaoshoushuliang    " +
                "      from t_jiaoban_shangpin xs , t_shangpin sp " +
                "   where xs.shangpinbianhao = sp.shangpinbianhao" ;
        List<Map<String,Object>> mapList=mDBHelpe.examine(sql);
        for(Map<String,Object> item : mapList){
            JiaoBanShangPin jiaoBanShangPin=new JiaoBanShangPin();
            jiaoBanShangPin.setMingcCheng((String) item.get("mingcheng"));
            jiaoBanShangPin.setXiaoShouShuLiang((Integer) item.get("xiaoshoushuliang"));
            jiaoBanShangPin.setJiaoBanKuCunLiang((Integer) item.get("jiaobankucunliang"));
            jiaoBanShangPin.setJieBanKuCunLiang((Integer) item.get("jiebankucunliang"));
            list.add(jiaoBanShangPin);
        }
        return list;
    }
    //测试读取交班商品数据
    public List<JiaoBanShangPin> readJiaoBanShangPinList() {
        String sql="select *  from t_jiaoban_shangpin order by banci";
        List<Map<String,Object>>  mapList=mDBHelpe.examine(sql);
        return mapListToShangPinList(mapList);
    }

    //解析交班商品信息
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

    //根据接班班次读接班数据并显示在界面
    public List<JiaoBanShangPin> readJiaoBanShangPinByJieBan(int banci) {

        List<JiaoBanShangPin> list=new ArrayList<JiaoBanShangPin>();
            String sql="select   xs.shangpinbianhao,sp.mingcheng, " +
                    " xs.jiebankucunliang,xs.jiaobankucunliang,xs.xiaoshoushuliang  , xs.banci  " +
                    " from t_jiaoban_shangpin xs , t_shangpin sp " +
                    "  where xs.shangpinbianhao = sp.shangpinbianhao  and  xs.banci = "+banci;
        List<Map<String,Object>> mapList=mDBHelpe.examine(sql);
        for(Map<String,Object> item : mapList){
                int banci1= (int) item.get("banci");
                JiaoBanShangPin jiaoBanShangPin=new JiaoBanShangPin();
                jiaoBanShangPin.setBanCi(banci1);
                jiaoBanShangPin.setMingcCheng((String) item.get("mingcheng"));
                jiaoBanShangPin.setXiaoShouShuLiang((Integer) item.get("xiaoshoushuliang"));
                jiaoBanShangPin.setJiaoBanKuCunLiang((Integer) item.get("jiaobankucunliang"));
                jiaoBanShangPin.setJieBanKuCunLiang((Integer) item.get("jiebankucunliang"));
                jiaoBanShangPin.setShangPinBianHao((String) item.get("shangpinbianhao"));
            list.add(jiaoBanShangPin);
        }
        return list;
    }
    //查询对班班次
    public int selectJieBanBanCi() {
        String sql="select * from t_jiaoban_shangpin";
        List<Map<String,Object>> mapList=mDBHelpe.examine(sql);
        int jiebanbanci= (int) mapList.get(mapList.size()-1).get("banci");
        Log.d("ceshi", "" + jiebanbanci);
        return jiebanbanci;
    }

}
