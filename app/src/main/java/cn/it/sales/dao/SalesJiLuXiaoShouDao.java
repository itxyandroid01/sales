package cn.it.sales.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.it.sales.Result.MyResult;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.JiLuXiaoShou;
import cn.it.sales.bean.JiaoBanShangPin;
import cn.it.sales.bean.SalesMessage;
import cn.it.sales.bean.ShangPin;
import cn.it.sales.db.MyOpenHelp;
import cn.it.sales.util.DateTimeUtil;

/**
 * Created by Administrator on 2016/5/20.
 */
public class SalesJiLuXiaoShouDao {
    MyOpenHelp mDBHelpe;

    public SalesJiLuXiaoShouDao() {
        mDBHelpe = MyApplication.getDb1Help();
    }

    public MyResult makeOneSaleWriteToDB(ShangPin shangPin, JiLuXiaoShou jiLuXiaoShou) {
        String sql = String.format("insert into t_jl_xiaoshou (shangpinbianhao,gonghao, " +
                        "banci, xiaoshoushijian,xiaoshoushuliang,lingshoujia," +
                        "xiaoshoujiage,zhekoulv,xiaoshouqianshuliang,canyuhuodong)" +
                        " values ('%s',%d,%d,%s,%d,%d,%d,'%s',%d,'%s')",
                shangPin.getShangPinBianHao(), jiLuXiaoShou.getGongHao(),

                jiLuXiaoShou.getBanCi(),

                DateTimeUtil.getSystemtDateTimeToSQL3GMT0(),
                jiLuXiaoShou.getXiaoShouShuLiang(),

                shangPin.getLingShouJia(), jiLuXiaoShou.getXiaoShouJiage(),

                shangPin.getZheKeLv(),

                shangPin.getKuCunShuLiang(),

                shangPin.getZhiChiHuoDong()
        );
        int id =mDBHelpe.executeInsertSQLReturnRowId(sql);
        MyResult myResult;
        if(id>0) {
            jiLuXiaoShou.set_id(id);
            myResult = new MyResult(1, "销售成功");
        }else{
            myResult = new MyResult(-1, "销售异常");
        }
        return myResult;

    }

//    public MyResult makeOneSaleWriteToDB(ShangPin shangPin, JiLuXiaoShou jiLuXiaoShou, String times) {
//
//        String sql = String.format(
//                "insert into t_jl_xiaoshou (" +
//                        "shangpinbianhao,   gonghao," +
//                        "banci," +
//                        "xiaoshoushijian,    xiaoshoushuliang," +
//                        "lingshoujia,    xiaoshoujiage," +
//                        "zhekoulv,    xiaoshouqianshuliang," +
//                        "canyuhuodong)" +
//                        " values ('%s',%d,  %d,'%s',%d,   %d,%d,'%s',%d,  '%s')",
//                shangPin.getShangPinBianHao(), jiLuXiaoShou.getGongHao(),
//
//                jiLuXiaoShou.getBanCi(),
//
//                times, jiLuXiaoShou.getXiaoShouShuLiang(),
//
//                shangPin.getLingShouJia(), jiLuXiaoShou.getXiaoShouJiage(),
//
//                shangPin.getZheKeLv(),
//
//                shangPin.getKuCunShuLiang(),
//
//                shangPin.getZhiChiHuoDong()
//        );
//
//        int id = mDBHelpe.executeInsertSQLReturnRowId(sql);
//        MyResult myResult;
//        if (id > 0) {
//            jiLuXiaoShou.set_id(id);
//            myResult = new MyResult(1, "销售成功");
//        } else {
//            myResult = new MyResult(-1, "销售异常");
//        }
//        return myResult;
//    }

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


            jiaoBanShangPin.setName((String) item.get("mingcheng"));
            jiaoBanShangPin.setShangpinjiage((int) item.get("lingshoujia"));
            jiaoBanShangPin.setXiaoShouShuLiang((int) item.get("xiaoshoushuliang"));
//          jiaoBanShangPin.setGonghao((int) item.get("gonghao"));
//            jiaoBanShangPin.setJiebankucunliang((int) item.get("jiebankucunliang"));
//            jiaoBanShangPin.setBanci((int) item.get("banci"));
//            jiaoBanShangPin.setXiaoshoujine((int) item.get("xiaoshoujine"));
//            jiaoBanShangPin.setJiaobankucunliang((int) item.get("jiaobankucunliang"));
//            jiaoBanShangPin.setShangpinbianhao((String) item.get(item.get("shangpinbianhao")));
            list.add(jiaoBanShangPin);
        }
        return list;
    }

    public void writeHotShangPin(List<JiaoBanShangPin> list) {
        //先把List中每一条数据转换成一条SQL语句，然后批量插入到数据库
        List<String> sqlList = new ArrayList<String>();
        for (JiaoBanShangPin item : list) {
            String sql = String.format(
                    "insert into t_jiaoban_shangpin(" +
                            "banci,gonghao,shangpinbianhao,jiebankucunliang," +
                            "xiaoshoushuliang,xiaoshoujine,jiaobankucunliang) " +
                            "values (%d,%d,'%s'," +
                            "%d,%d,%d,%d )",
                    item.getBanCi(),
                    item.getGongHao(),
                    item.getShangPinBianHao(),

                    item.getJieBanKuCunLiang(),
                    item.getXiaoShouShuLiang(),
                    item.getXiaoShouJinE(),
                    item.getJiaoBanKuCunLiang()
            );
            sqlList.add(sql);

        }
        // mDBHelp.batchExecuteSQL("TRUNCATE TABLE t_jiaoban_shangpin");
        mDBHelpe.qingKongShuJu();
        mDBHelpe.batchExecuteSQL(sqlList);
    }

    //String mKaiShiShiJian, String mJieShuShiJian
    public List<SalesMessage> readJiLuXiaoShouMX(String mKaiShiShiJian, String mJieShuShiJian) {
        String sql = "select mingcheng,xiaoshoushuliang,xiaoshoujiage,xiaoshoushijian from t_jl_xiaoshou,t_shangpin where xiaoshoushijian Between '" + mKaiShiShiJian + "' and '" + mJieShuShiJian + "'" + " and t_jl_xiaoshou.shangpinbianhao=t_shangpin.shangpinbianhao";


        List<Map<String, Object>> mapList = mDBHelpe.examine(sql);
        return mapListToJiLuXiaoShouList(mapList);
    }

    private List<SalesMessage> mapListToJiLuXiaoShouList(List<Map<String, Object>> mapList) {
        List<SalesMessage> list = new ArrayList<>();
        if (mapList == null) {
            return list;
        }
        //开始逐条转换
        for (Map<String, Object> item : mapList) {
            String mXiangPinMingCheng = (String) item.get("mingcheng");
            int mXiaoShouShuLiang = (int) item.get("xiaoshoushuliang");
            int mXiaoShouJiaGe = (int) item.get("xiaoshoujiage");
            String mXiaoShouShiJian = (String) item.get("xiaoshoushijian");
            SalesMessage jiLuXiaoShou = new SalesMessage(mXiangPinMingCheng, mXiaoShouShuLiang, mXiaoShouJiaGe, mXiaoShouShiJian);
            list.add(jiLuXiaoShou);
        }
        return list;
    }

    public List<SalesMessage> readJiLuXS1(int shangPinMingCheng, String kaiShiShiJian, String jieShuShiJian) {
        String sql = "select mingcheng,xiaoshoushuliang,xiaoshoujiage,xiaoshoushijian from t_jl_xiaoshou,t_shangpin,t_shangpingleibie where xiaoshoushijian " +
                "Between '" + kaiShiShiJian + "' and '" + jieShuShiJian + "'" + " and t_shangpingleibie.leibiebianhao=t_shangpin.leibiebianhao and t_jl_xiaoshou.shangpinbianhao=t_shangpin.shangpinbianhao";
        List<Map<String, Object>> mapList1 = mDBHelpe.examine(sql);
        Log.d("sql", "readJiLuXS1: "+sql);
        Log.d("mapList1", "readJiLuXS1: "+mapList1);
        return mapListToJiLuXiaoShouList2(mapList1);
    }

    private List<SalesMessage> mapListToJiLuXiaoShouList2(List<Map<String, Object>> mapList1) {
        List<SalesMessage> list = new ArrayList<>();
        if (mapList1 == null) {
            return list;
        }
        //开始逐条转换
        for (Map<String, Object> item : mapList1) {
            String mXiangPinMingCheng = (String) item.get("mingcheng");
            int mXiaoShouShuLiang = (int) item.get("xiaoshoushuliang");
            int mXiaoShouJiaGe = (int) item.get("xiaoshoujiage");
            String mXiaoShouShiJian = (String) item.get("xiaoshoushijian");
            SalesMessage jiLuXiaoShou = new SalesMessage(mXiangPinMingCheng, mXiaoShouShuLiang, mXiaoShouJiaGe, mXiaoShouShiJian);
            list.add(jiLuXiaoShou);
        }
        return list;
    }

    public List<JiLuXiaoShou> makeReXiao() {
        String sql1 = "select * from t_jl_xiaoshou order by xiaoshoushuliang desc limit 0,10";
        List<Map<String, Object>> mapList1 = mDBHelpe.examine(sql1);
        return mapListToJiLuXiaoShouList4(mapList1);
    }

    private List<JiLuXiaoShou> mapListToJiLuXiaoShouList4(List<Map<String, Object>> mapList1) {
        List<JiLuXiaoShou> list = new ArrayList<>();
        if (mapList1 == null) {
            return list;
        }
        //开始逐条转换
        for (Map<String, Object> item : mapList1) {

            String mShangPinBiaoHao = (String) item.get("shangpinbianhao");
            int mGonghao = (int) item.get("gonghao");
            int mbanci = (int) item.get("banci");
            String mxiaoshoushijian = (String) item.get("xiaoshoushijian");
            int xiaoshoushuliang = (int) item.get("xiaoshoushuliang");
            int lingshoujia = (int) item.get("lingshoujia");
            int xiaoshoujiage = (int) item.get("xiaoshoujiage");
            int zhekoulv = (int) item.get("zhekoulv");
            int xiaoshouqianshuliang = (int) item.get("xiaoshouqianshuliang");
            String canyuhuodong = (String) item.get("canyuhuodong");
            int shangchuan = (int) item.get("shangchuan");
            String shangchuanshijian = (String) item.get("shangchuanshijian");
            int id = (int) item.get("_id");
            String shangpinmingcheng = (String) item.get("shangpinmingcheng");

            JiLuXiaoShou jiLuXiaoShou = new JiLuXiaoShou(
                    mShangPinBiaoHao,
                    mGonghao,
                    mbanci,
                    mxiaoshoushijian,
                    xiaoshoushuliang,
                    lingshoujia,
                    xiaoshoujiage,
                    zhekoulv,
                    xiaoshouqianshuliang,
                    canyuhuodong,
                    shangchuan,
                    shangchuanshijian,
                    id, shangpinmingcheng);


            list.add(jiLuXiaoShou);


        }
        return list;
    }

    public List<JiLuXiaoShou> readJiaoBanShangPinList() {
        String sql1 = "select * from t_jl_xiaoshou";
        List<Map<String, Object>> mapList1 = mDBHelpe.examine(sql1);
        return mapListToJiLuXiaoShouList5(mapList1);
    }

    private List<JiLuXiaoShou> mapListToJiLuXiaoShouList5(List<Map<String, Object>> mapList1) {
        List<JiLuXiaoShou> list = new ArrayList<>();
        if (mapList1 == null) {
            return list;
        }
        //开始逐条转换
        for (Map<String, Object> item : mapList1) {

            String mShangPinBiaoHao = (String) item.get("shangpinbianhao");
            int mGonghao = (int) item.get("gonghao");
            int mbanci = (int) item.get("banci");
            String mxiaoshoushijian = (String) item.get("xiaoshoushijian");
            int xiaoshoushuliang = (int) item.get("xiaoshoushuliang");
            int lingshoujia = (int) item.get("lingshoujia");
            int xiaoshoujiage = (int) item.get("xiaoshoujiage");
            int zhekoulv = (int) item.get("zhekoulv");
            int xiaoshouqianshuliang = (int) item.get("xiaoshouqianshuliang");
            String canyuhuodong = (String) item.get("canyuhuodong");
            int shangchuan = (int) item.get("shangchuan");
            String shangchuanshijian = (String) item.get("shangchuanshijian");
            int id = (int) item.get("_id");
            String shangpinmingcheng = (String) item.get("shangpinmingcheng");

            JiLuXiaoShou jiLuXiaoShou = new JiLuXiaoShou(
                    mShangPinBiaoHao,
                    mGonghao,
                    mbanci,
                    mxiaoshoushijian,
                    xiaoshoushuliang,
                    lingshoujia,
                    xiaoshoujiage,
                    zhekoulv,
                    xiaoshouqianshuliang,
                    canyuhuodong,
                    shangchuan,
                    shangchuanshijian,
                    id, shangpinmingcheng

            );


            list.add(jiLuXiaoShou);


        }
        return list;
    }

    public List<JiaoBanShangPin> insertIntoJiaoBanShangPin(int gongHao, String shangPinBianHao, int xiaoShouQianShuLiang, int xiaoShouShuLiang, int jieBanKuCunLiang, int xiaoShouJInE) {
        String sql = String.format("insert into t_jiaoban_shangpin(" +
                        "banci," +
                        "gonghao," +
                        "shangpinbianhao," +
                        "jiebankucunliang," +
                        "xiaoshoushuliang," +
                        "xiaoshoujine," +
                        "jiaobankucunliang" +
                        ") values(%d,%d,'%s',%d,%d,%d,%d);",
                0,
                gongHao,
                shangPinBianHao,
                xiaoShouQianShuLiang,
                xiaoShouShuLiang,
                xiaoShouJInE,
                jieBanKuCunLiang);
        mDBHelpe.examine(sql);

        return null;
    }

}



