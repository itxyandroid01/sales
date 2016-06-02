package cn.it.sales.ball;

import java.util.ArrayList;
import java.util.List;

import cn.it.sales.application.MyDebug;
import cn.it.sales.bean.ShangPin;
import cn.it.sales.dao.ShangPinDao;

/**
 * Created by Administrator on 2016/5/19.
 */
public class ShangPinManager {
    /**
     * 根据类别编号从数据库中检索出本类别编号的所有商品，并放入List中
     */
    public List<ShangPin> readShangPinListByLeiBieBianHao(int leiBieBianHao){
        List<ShangPin> list=null ;
        //添加演示数据到数据库表中
        if(MyDebug.DEMO_ParseAndWriteShangPin){
            demoNewShangPin(leiBieBianHao);
        }
        //读出指定类别编号的商品，并放到List中
        ShangPinDao dao = new ShangPinDao();
        list=dao.readShangPinByLeiBieBianHao(leiBieBianHao);
        return list;
    }

    /***
     * 在本子类别的商品演示数据
     * @param leiBieBianHao
     */
    private void demoNewShangPin(int leiBieBianHao){
        //造10条指定类别的商品放入List中
        List<ShangPin> list = new ArrayList<ShangPin>();
        for(int i=1;i<10;i++){
            ShangPin item=new ShangPin(
                    ""+leiBieBianHao+(1000+i),
                    leiBieBianHao,
                    "商品名称" + i,
                    "商品全称" + i,
                    "商品描述"+i,
                    "规格"+i,
                    "型号"+i,
                100+i,
                80+i,
                100+i,
            "无销售建议",
            "shangping/"+leiBieBianHao+(1000+i)+".png",
            "不参与活动",
            "自己找");
            list.add(item);
        }
        //调用DAO层的方法，批量添加数据
        ShangPinDao dao = new ShangPinDao();
        dao.writeShangPinToDB(list);
    }
    public void sendDiogformShanPin(ShangPin shangPin,int position){
            ShangPinDao dao=new ShangPinDao();
        dao.getDioginfo(shangPin,position);   }
}
