package cn.it.sales.dao;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.it.sales.Result.MyResult;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.JieBanInfo;
import cn.it.sales.db.MyOpenHelp;


/**
 * Created by Administrator on 2016/5/24.
 */
public class JiaoBanDao {
    MyOpenHelp mDBHelpe= MyApplication.getDb1Help();

    //查询 是否接班
    public MyResult readJiaoBanByBanci(int gonghao, int banci,String xingming){

        MyResult myResult=new MyResult();
        String sql="select * from t_jiaoban order by banci";
        List<Map<String,Object>> mapList=mDBHelpe.examine(sql);
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

                        myResult.setCodeAndMessage(0, "正常上班");
                    }
                }
            }else if(jiaobanzhuangtai==1){
                myResult.setCodeAndMessage(2, "对班还没有交班，请不要着急");
            }else {
                myResult.setCodeAndMessage(0,"你是第一次来上班，加油！");
            }
            if(item.isEmpty()){}
        }
        return myResult;
    }

    //插入接班表中的接班记录
    private void writeBanCiToDb(int gonghao, int banci, String xingming) {
        String sql="insert into t_jiaoban(banci,gonghao,jiaobanzhuangtai,xingming)" +
                " values ("+banci+","+gonghao+",1,'"+xingming+"')";
        mDBHelpe.examine(sql);

    }

    //生成接班记录到本地数据库
    public MyResult InsertDataToDB(JSONObject jsonObject1) {
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

            //插入接班表中的接班记录
            writeBanCiToDb(gonghao,banci,xingming);

            String sql1 = String.format("insert into  t_jiaoban (" +
                            "banci,gonghao,xingming,jiebangonghao," +
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
        int row=mDBHelpe.batchExecuteSQL(list1);
        MyResult myResult=new MyResult();
        myResult.setCode(row);
        return myResult;
    }
    //服务器下载的数据解析并放到本地数据库
    public void writeJiebaninfoToDB(List<JieBanInfo> list) {
        List <String> sqlList=new ArrayList<String>();
        String[] biaoming={"t_jiaoban","t_jiaoban_shangpin", "t_jiaoban_shiyi",
                "t_jl_xiaoshou","t_shangpin","t_shangpingleibie"
        };
        for (String biao:biaoming){
            sqlList.add("delete from "+biao);
        }
        for (JieBanInfo item:list){
            int mBanCi=item.getBanCi();
            int mGongHao=item.getGongHao();
            String mXingMing=item.getXingMing();
            int mJieBanGongHao=item.getJieBanGongHao();
            String mJieBanShiJian=item.getJieBanShiJian();
            int mXiaoShouShuLiang=item.getXiaoShouShuLiang();
            int mXiaoShouJinE=item.getXiaoShouJinE();
            int mJiaoBanGongHao=item.getJiaoBanGongHao();
            String mJiaoBanShiJian=item.getJiaoBanShiJian();
            int mShangChuan=item.getShangChuan();
            String mShangChuanShiJian=item.getShangChuanShiJian();
            String mDianPu=item.getDianPu();
            int mJiaoBanZhuangTai=item.getJiaoBanZhuangTai();
            String mShangPinBianHao=item.getShangPinBianHao();
            int mJieBanKuCunLiang=item.getJieBanKuCunLiang();
            int mJiaoBanKuCunLiang=item.getJiaoBanKuCunLiang();
            String mJieBanShiYi=item.getJieBanShiYi();
            String mJiaoBanShiYi=item.getJiaoBanShiYi();
            int mLeiBieBianHao=item.getLeiBieBianHao();
            String mMingCheng=item.getMingCheng();
            String mQuanCheng=item.getQuanCheng();
            String mMiaoShu=item.getMiaoShu();
            String mGuiGe=item.getGuiGe();
            String mXingHao=item.getXingHao();
            int mLingShouJia=item.getLingShouJia();
            int mZheKeLv=item.getZheKeLv();
            int mKuCunShuLiang=item.getKuCunShuLiang();
            String mXiaoShouJianYi=item.getXiaoShouJianYi();
            String mTuPianMingCheng=item.getTuPianMingCheng();
            String mZhiChiHuoDong=item.getZhiChiHuoDong();
            String mHuoJiaWeiZhi=item.getHuoJiaWeiZhi();
            String mLeibiemingcheng=item.getLeibiemingcheng();
            int mJiBie=item.getJiBie();
            int mFuleibiebianhao=item.getFuleibiebianhao();
            String mXiaoShouShiJian=item.getXiaoShouShiJian();
            int mXiaoShouJiage=item.getXiaoShouJiage();
            int mXiaoShouQianShuLiang=item.getXiaoShouQianShuLiang();
            String mCanYuHuoDong=item.getCanYuHuoDong();
            Log.d("wuyude",mCanYuHuoDong);
            String sql=String.format("insert into t_jiaoban "+
                            "(banci,gonghao,xingming,jiebangonghao,jiebanshijian，xiaoshoushuliang，xiaoshoujine，" +
                            "jiaobangonghao，jiaobanshijian，shangchuan，shangchuanshijian，" +
                            "dianpu，jiaobanzhuangtai) "+
                            "values (%d,%d,'%s',%d,'%s',%d,%d,%d'%s',%d,%d,%s,%d)",
                    mBanCi,mGongHao,mXingMing,mJieBanGongHao,mJieBanShiJian,mXiaoShouShuLiang,mXiaoShouJinE,
                    mJiaoBanGongHao,mJiaoBanShiJian,mShangChuan,mShangChuanShiJian,
                    mDianPu,mJiaoBanZhuangTai);
            String sql2=String.format("insert  into t_jiaoban_shangpin" +
                    "(banci,gonghao,shangpinbianhao," +
                    "jiebankucunliang,xiaoshoushuliang,xiaoshoujine," +
                    "jiaobankucunliang)"+
                    "values(%d,%d,'%s',%d,%d,%d,%d)",
                    mBanCi,mGongHao,mShangPinBianHao,
                    mJieBanKuCunLiang,mXiaoShouShuLiang,mXiaoShouJinE,
                    mJiaoBanKuCunLiang
                    );
            String sql3=String.format("insert  into t_jiaoban_shiyi" +
                    "(banci,gonghao,jiebanshiyi,jiaobanshiyi)"+
                    "values(%d,%d,'%s','%s')",
                    mBanCi,mGongHao,mJieBanShiYi,mJiaoBanShiYi
                    );
            String sql4=String.format("insert into t_jl_xiaoshou"+
                    "(shangpinbianhao,gonghao,banci" +
                    "xiaoshoushijian,xiaoshoushuliang,lingshoujia," +
                    "xiaoshoujiage,zhekoulv,xiaoshouqianshuliang," +
                    "canyuhuodong,shangchuan,shangchuanshijian," +
                    "dianpu,jiaojiezhuangtai)"+
                    "values('%s',%d,%d,'%s',%d,%d,%d,%d,%d,'%s',%d,'%s','%s',%d)",
                    mShangPinBianHao,mGongHao,mBanCi,
                    mXiaoShouShiJian,mXiaoShouShuLiang,mLingShouJia,
                    mXiaoShouJiage,mZheKeLv,mXiaoShouQianShuLiang,
                    mCanYuHuoDong,mShangChuan,mShangChuanShiJian,
                    mDianPu,mJiaoBanZhuangTai
            );
//            String sql5=String.format("insert  into  t_shangchuanrenwu" +
//                    "(bianhao,miaoshu,renwushijian,wanchengqingkuag)"+
//                    "values(%d,%s,%s,%d)",
//                    mMiaoShu,m
//                    );
            String sql5=String.format("insert into t_shangpin" +
                    "(shangpinbianhao,leibiebianhao,mingcheng,quancheng,"+
                    "miaoshu,guige,xinghao,lingshoujia,"+
                    "zhekulv,kucunshuliang,xaioshoujianyi,"+
                    "tupianmingcheng,zhichihuodong,huojiaweizhi)"+
                    "values(%s,%d,%s,%s,%s,%s,%s,%d,%d,%d,%s,%s,%s,%s)",
                    mShangPinBianHao,mLeiBieBianHao,mMingCheng,mQuanCheng,
                    mMiaoShu,mGuiGe,mXingHao,mLingShouJia,
                    mZheKeLv,mKuCunShuLiang,mXiaoShouJianYi,
                    mTuPianMingCheng,mZhiChiHuoDong,mHuoJiaWeiZhi
            );        ;
            String sql6=String.format("insert into shangpingleibei" +
            "(leibiebianhao,leibiemingcheng,jibie," +
                    "fuleibianhao,tupianmingcheng)"+
                    "values(%d,%s,%d,%d,%s)",
                    mLeiBieBianHao,mLeibiemingcheng,mJiBie,
                    mFuleibiebianhao,mTuPianMingCheng
                    );
            sqlList.add(sql);
            sqlList.add(sql2);
            sqlList.add(sql3);
            sqlList.add(sql4);
            sqlList.add(sql5);
            sqlList.add(sql6);
            mDBHelpe.batchExecuteSQL(sqlList);
        }

    }

}
