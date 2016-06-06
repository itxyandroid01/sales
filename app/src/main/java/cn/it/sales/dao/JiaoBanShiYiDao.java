package cn.it.sales.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.it.sales.Result.MyResult;
import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.JiaoBanShangPin;
import cn.it.sales.bean.JiaoBanShiYi;
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

    //生成的交班信息记录到本地数据库
   public MyResult updateJiaoBanInfoToDb(List<JiaoBanShangPin> list, JiaoBanShiYi jiaoBanShiYi){
       List<String> sqlList = new ArrayList<String>();
       //获取本班班次
       int banci=MyApplication.getmBanCi();
       //根据本班班次补充交班事宜
      String sql=" update t_jiaoban_shiyi set jiaobanshiyi="+jiaoBanShiYi.getJiaoBanShiYi()+"where  banci="+banci;
       sqlList.add(sql);
       //补充交班时候商品销售数量和销售金额
       //定义一个变量计算总金额
       int xiaoshouzongjine = 0;
       for (JiaoBanShangPin jiaoBanShangPin :list){
      String sql1="update t_jiaoban_shangpin set xiaoshoushuliang="+jiaoBanShangPin.getXiaoShouShuLiang() +
              " ,xiaoshoujine="+jiaoBanShangPin.getXiaoShouJinE()+",jiaobankucunliang="+
              jiaoBanShangPin.getJiaoBanKuCunLiang()+"  where shangpinbianhao="+jiaoBanShangPin.getShangPinBianHao();
           xiaoshouzongjine += jiaoBanShangPin.getXiaoShouJinE();
       sqlList.add(sql1);
       }
       //补充交班金额，交班状态
       String sql2="update t_jiaoban set xiaoshoujine="+xiaoshouzongjine+",jiaobanzhuangtai=2  where banci= "+banci;
       sqlList.add(sql2);
       int coder=mDBHelpe.batchExecuteSQL(sqlList);
       MyResult myResult=new MyResult();
       myResult.setCode(coder);
       return myResult;
   }
}
