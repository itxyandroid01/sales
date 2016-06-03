package cn.it.sales.bll;

import java.util.ArrayList;
import java.util.List;

import cn.it.sales.application.MyDebug;
import cn.it.sales.bean.JiaoBanShangPin;
import cn.it.sales.dao.SalesJiLuXiaoShouDao;

/**
 * Created by Administrator on 2016/5/23.
 */
public class HotSalesManager {
    SalesJiLuXiaoShouDao hotSales=new SalesJiLuXiaoShouDao();

    public List<JiaoBanShangPin> reaHotSalesShangPin(String sql){

        if(MyDebug.DEMO_TestSales){
            demoHotSales();
        }
        List<JiaoBanShangPin> J=hotSales.readJiLuXiaoShouByXiaoShouShuLiang(sql);

        return J ;
    }

    private void demoHotSales() {

        List<JiaoBanShangPin> list=new ArrayList<JiaoBanShangPin>();

        for (int i=1; i <10;i++){
            for (int j=1;j<10;j++){

                JiaoBanShangPin item=new JiaoBanShangPin(
                        1,
                        1,
                        "100100"+i+"100"+j,
                        2,
                        10*i+j,
                        1,
                        1

                );
                list.add(item);
            }
        }
        SalesJiLuXiaoShouDao dao = new SalesJiLuXiaoShouDao();
        dao.writeHotShangPin(list);
    }

}
