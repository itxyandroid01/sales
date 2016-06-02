package cn.it.sales.dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.it.sales.application.MyApplication;
import cn.it.sales.bean.ShangPin;
import cn.it.sales.db.MyOpenHelp;

/**
 * Created by Administrator on 2016/5/19.
 */
public class ShangPinDao {
    private static final String TAG = "ShangPinDao";
    MyOpenHelp mDBHelp;
    public ShangPinDao(){
        mDBHelp= MyApplication.getDb1Help();
    }

    /***
     *  读出指定类别编号的全部商品到List中
     * @param leiBieBianHao
     * @return
     */
    public List<ShangPin>  readShangPinByLeiBieBianHao(int leiBieBianHao){
         //生成查询S语句
        String sql="select * from t_shangpin where leibiebianhao= "+leiBieBianHao;
        //执行查询语句，返回查询结果数据集
        List<Map<String,Object>> mapList=mDBHelp.examine(sql);
//        Log.d("qwq",""+mapList);
        //把查询到的数据集转换成自己类型的集合
//        return mapList;
        return mapListToShangPinList(mapList);
    }

    public List<ShangPin>  mapListToShangPinList( List<Map<String,Object>> mapList){
        List<ShangPin> list =new ArrayList<ShangPin>();
        if(mapList ==null){
            return list;
        }
        //开始逐条转换
        for(Map<String,Object> item : mapList){
            ShangPin shangPin=new ShangPin();
            shangPin.setShangPinBianHao((String) item.get("shangpinbianhao"));
            shangPin.setLeiBieBianHao((Integer) item.get("leibiebianhao"));
            shangPin.setMingCheng((String) item.get("mingcheng"));
            shangPin.setQuanCheng((String) item.get("quancheng"));
            shangPin.setMiaoShu((String) item.get("miaoshu"));
            shangPin.setGuiGe((String) item.get("guige"));
            shangPin.setXingHao((String) item.get("xinghao"));
            shangPin.setLingShouJia((Integer) item.get("lingshoujia"));
            shangPin.setZheKeLv((Integer) item.get("zhekelv"));
            shangPin.setKuCunShuLiang((Integer) item.get("kucunshuliang"));
            shangPin.setXiaoShouJianYi((String) item.get("xiaoshoujianyi"));
            shangPin.setTuPianMingCheng((String) item.get("tupianmingcheng"));
            shangPin.setZhiChiHuoDong((String) item.get("zhichihuodong"));
            shangPin.setHuoJiaWeiZhi((String) item.get("huojiaweizhi"));

            list.add(shangPin);
//            Log.d("qwq", "" + list);
            //........自己补充
        }
        return list;
    }
    /***
     * 把List中的商品信息保存到数据中
     * @param list
     * @return
     */

    public int writeShangPinToDB(List<ShangPin> list){
        int row=0;
        //先把List中每一条数据转换成一条SQL语句，然后批量插入到数据库
        List<String> sqlList=new ArrayList<String>();
        //把表中原有的数据删除
        int leiBieBianHao=list.get(0).getLeiBieBianHao();
        sqlList.add("delete from t_shangpin where leibiebianhao= "+leiBieBianHao);
        //把每一条数据变成一条SQL语句
        for(ShangPin item :list) {
            String sql = String.format(
                    "insert into t_shangpin("+
                            "shangpinbianhao,leibiebianhao,mingcheng,quancheng," +
                            "miaoshu,guige,xinghao,lingshoujia," +
                            "zhekelv,kucunshuliang,xiaoshoujianyi," +
                            "tupianmingcheng,zhichihuodong,huojiaweizhi) " +
                            "values ('%s',%d,'%s','%s'," +
                            "'%s','%s','%s',%d," +
                            "%d,%d,'%s',"+
                            "'%s','%s','%s')",
                    item.getShangPinBianHao(),
                    item.getLeiBieBianHao(),
                    item.getMingCheng(),
                    item.getQuanCheng(),

                    item.getMiaoShu(),
                    item.getGuiGe(),
                    item.getXingHao(),
                    item.getLingShouJia(),

                    item.getZheKeLv(),
                    item.getKuCunShuLiang(),
                    item.getXiaoShouJianYi()
                    ,
                    item.getTuPianMingCheng(),
                    item.getZhiChiHuoDong(),
                    item.getHuoJiaWeiZhi());
            sqlList.add(sql);
        }

        //调用DBHelp的方法，批量执行SQL语句
        row=mDBHelp.batchExecuteSQL(sqlList);
        return row;
    }
    public JSONObject getDioginfo(ShangPin shangPin,int position){
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("mingcheng",shangPin.getMingCheng().toString());
            jsonObject.put("position",position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        shangPin.getMingCheng();
        return null;

    }

}
