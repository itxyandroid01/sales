package cn.it.sales.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.it.sales.application.MyApplication;
import cn.it.sales.application.MyDebug;
import cn.it.sales.bean.ShangPinLeiXing1;
import cn.it.sales.bean.ShangPinLeiXing2;
import cn.it.sales.db.MyOpenHelp;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ShangPinLeiXingDao {
    private static final String TAG = "ShangPinLeiXingDao";
    MyOpenHelp mDBHelp;
    public ShangPinLeiXingDao(){
        mDBHelp= MyApplication.getDb1Help();
    }


    //本处的synchronized锁不住，
    public void  readShangPinLeiXing(){
        //先把MyApplication中商品分类列表的数据清除
        List<ShangPinLeiXing1> list = MyApplication.getShangPinLeiXing1List();
        synchronized (ShangPinLeiXingDao.class) {
            list.clear();

            //如果是测试版，则自己添加演示数据，否则通过DBHelp中读真实数据
            if (MyDebug.DEMO_ParseAndWriteShangPinLeiXing) {
                readShangPinLeiXingFromDemo();
            } else {
                readShangPinLeiXingFromDB();
            }
        }
    }

    private void readShangPinLeiXingFromDemo(){
        List<ShangPinLeiXing1> list = MyApplication.getShangPinLeiXing1List();
        ShangPinLeiXing1 item1;
        ShangPinLeiXing2 item2;
        item1=new ShangPinLeiXing1(1001,"衣服类",1,0,"");
        list.add(item1);
        for(int i=1;i<4;i++){
            item2=new ShangPinLeiXing2(1001000+i,"demo"+i,2,1001,"");
            item1.getShangPinLeiXing2List().add(item2);
        }
        item1=new ShangPinLeiXing1(1002,"鞋帽类",1,0,"");
        list.add(item1);
        for(int i=1;i<4;i++){
            item2=new ShangPinLeiXing2(1002000+i,"demo"+i,2,1002,"");
            item1.getShangPinLeiXing2List().add(item2);
        }
        item1=new ShangPinLeiXing1(1003,"旅游鞋",1,0,"");
        list.add(item1);
        for(int i=1;i<4;i++){
            item2=new ShangPinLeiXing2(1003000+i,"demo"+i,2,1003,"");
            item1.getShangPinLeiXing2List().add(item2);
        }
        item1=new ShangPinLeiXing1(1004,"艾迪达斯",1,0,"");
        list.add(item1);
        for(int i=1;i<4;i++){
            item2=new ShangPinLeiXing2(1004000+i,"demo"+i,2,1004,"");
            item1.getShangPinLeiXing2List().add(item2);
        }
    }
    private void readShangPinLeiXingFromDB(){
        List<ShangPinLeiXing1> list = MyApplication.getShangPinLeiXing1List();
        //采用一次性把数据全部读出来=>
        // 然后对读出的数据进行依次分拣归类=>
        // 把归类后的数据放入到getShangPinLeiXing1List中
        //String sql="select *  from t_shangpingleibie where 1=1";
        //生成查询的SQL语句
        String sql="select *  from t_shangpingleibie order by leibiebianhao";
        //执行SQL语句，返回查询结果集
        List<Map<String,Object> >  mapList=mDBHelp.examine(sql);
        //对集合中 每一条数据进行分拣归类
        for(Map<String,Object> mapItem : mapList){
            //根据父类别标号，判断每一条数据的分类类型
            int fuLeiBieBianHao =(int)mapItem.get("fuleibiebianhao");
            //如果父类别编号为0，则是第一级
            if(fuLeiBieBianHao ==0  ){
                //从map中读出一条数据的各个字段
                int leibiebianhao=(int)mapItem.get("leibiebianhao");
                String leibiemingcheng=(String)mapItem.get("leibiemingcheng");
                int jiBie=(int)mapItem.get("jibie");
                //int fuleibiebianhao=; //已经读取
                String tupianmingcheng=(String)mapItem.get("tupianmingcheng");
                //根据字段生成一个分类1的实例
                ShangPinLeiXing1 shangPinLeiXing1=new ShangPinLeiXing1(
                        leibiebianhao,leibiemingcheng,jiBie,fuLeiBieBianHao,tupianmingcheng
                );
                //把分类1数据添加到了总列表中
                list.add(shangPinLeiXing1);
            }else{   //二级目录
                //从map中读出一条数据的各个字段
                int leibiebianhao=(int)mapItem.get("leibiebianhao");
                String leibiemingcheng=(String)mapItem.get("leibiemingcheng");
                int jiBie=(int)mapItem.get("jibie");
                //int fuleibiebianhao=; //已经读取
                String tupianmingcheng=(String)mapItem.get("tupianmingcheng");
                //根据字段生成一个分类2的实例
                ShangPinLeiXing2 shangPinLeiXing2=new ShangPinLeiXing2(
                        leibiebianhao,leibiemingcheng,jiBie,fuLeiBieBianHao,tupianmingcheng
                );
                //二级目录只能添加到对应的一级目录下面
                //从一级编号中查找商品编号为fuLeiBieBianHao的数据，
                // 然后把shangPinLeiXing2归位带对应的列表中
                for(ShangPinLeiXing1 item :list){
                    //找到父类对应的一级目录项，
                    if(item.getLeibiebianhao()==fuLeiBieBianHao){
                        //添加到对应的位置
                        item.getShangPinLeiXing2List().add(shangPinLeiXing2);
                        break;
                    }
                }
            }
        }

    }

    /***
     * 把List中的商品分类信息保存到数据中
     * @param list
     * @return
     */
    public int writeShangPinLeiXingToDB(List<ShangPinLeiXing2> list){
        int row=0;
        //先把List中每一条数据转换成一条SQL语句，然后批量插入到数据库
        List <String> sqlList=new ArrayList<String>();
        //把表中原有的数据删除
        sqlList.add("delete from t_shangpingleibie ");
        //先把List中每一条数据转换成一条SQL语句,并保存到sqlList
        for(ShangPinLeiXing2 item : list){
            String sql=String.format("insert into t_shangpingleibie "+
                    //目前图片字段都没内容，因此不写入
                   // "(leibiebianhao,leibiemingcheng,jibie,fuleibiebianhao,tupianmingcheng)")
                   "(leibiebianhao,leibiemingcheng,jibie,fuleibiebianhao) "+
                   "values (%d,'%s',%d,%d)",
                    item.getLeibiebianhao(),item.getLeibiemingcheng(),
                    item.getJiBie(),item.getFuleibiebianhao());
            Log.d(TAG, "sql: "+sql);
            sqlList.add(sql);
        }
        //调用DBHelp的方法，批量执行SQL语句
        row=mDBHelp.batchExecuteSQL(sqlList);
        return row;
    }
}
