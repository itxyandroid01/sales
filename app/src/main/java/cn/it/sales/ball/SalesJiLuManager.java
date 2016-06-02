package cn.it.sales.ball;

import java.util.List;

import cn.it.sales.Result.MyResult;
import cn.it.sales.bean.JiLuXiaoShou;
import cn.it.sales.bean.SalesMessage;
import cn.it.sales.bean.ShangPin;
import cn.it.sales.dao.SalesJiLuXiaoShouDao;

/**
 * Created by Administrator on 2016/5/20.
 */
public class SalesJiLuManager {

//String mKaiShiShiJian, String mJieShuShiJian
public List<SalesMessage> readJiLuXiaoShou1(String mKaiShiShiJian, String mJieShuShiJian) {

    List<SalesMessage> list=null ;
    SalesJiLuXiaoShouDao salesJiLuXiaoShouDao=new SalesJiLuXiaoShouDao();
    list=salesJiLuXiaoShouDao.readJiLuXiaoShouMX(mKaiShiShiJian,mJieShuShiJian);

    return list;
}


    public List<SalesMessage> readJiLuXiaoShou3(int liebiebianhao, String kaiShiShiJian, String jieShuShiJian) {
        List<SalesMessage> list=null;
        SalesJiLuXiaoShouDao dao=new SalesJiLuXiaoShouDao();
        list=dao.readJiLuXS1(liebiebianhao, kaiShiShiJian, jieShuShiJian);
        return list;
    }


    public interface OnSalesListener{
        /**
         * 销售员在对话框中选择了销售 触发
         * @param position  本对话框显示的是第position条商品信息
         */

        public void onSelectSales(int position);

        /**
         * 销售员在对话框中选择了取消 触发
         */
        public void onSelectCancle();

    }
    public MyResult makeOneSale(ShangPin shangPin,JiLuXiaoShou jiLuXiaoShou){
        MyResult myResult = new MyResult();
      try {
            SalesJiLuXiaoShouDao dao = new SalesJiLuXiaoShouDao();
            dao.makeOneSaleWriteToDB(shangPin, jiLuXiaoShou);
          myResult.setCode(1);
        }catch (Exception e){
            myResult.setCode(-1);
        }
          return myResult;
    }




}
