package cn.it.sales.application;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.it.sales.R;
import cn.it.sales.ball.ShangPinLeiXingManager;
import cn.it.sales.ball.ShangPinManager;
import cn.it.sales.bean.ShangPinLeiXing1;
import cn.it.sales.dao.JiaoBanShangPinDao;
import cn.it.sales.db.MyOpenHelp;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MyApplication extends Application {

    public final static int JIZHANG1=1;
    public final static int JIZHANG2=2;
    //登录状态为1时，登录成功
    public static int DENG_LU_ZHUANG_TAI=1;
    private  static MyApplication mApplication;
    private static MyOpenHelp  mDB1Help;
    private static OkHttpClient mOkHttpClient;
    public static MyApplication getMyApplication(){
        return mApplication;
    }
    public static OkHttpClient getOkHttpClient(){
        if(mOkHttpClient==null){
            mOkHttpClient = new OkHttpClient();
        }
        return mOkHttpClient;
    }
    private static List<AppCompatActivity> mActivityList= new ArrayList<AppCompatActivity>();

    public void addActivity(AppCompatActivity activity){
        mActivityList.add(activity);
    }

    public void removeActivity(AppCompatActivity activity){
        mActivityList.remove(activity);
    }



    public void exitActivity(){
        try {
            for (int position =mActivityList.size()-1 ;position>=0;position--) {
                AppCompatActivity activity=mActivityList.get(position);
                if (activity != null ){
                    removeActivity(activity);
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int mBanci;

    public static int getmBanci() {
        return mBanci;
    }

    //商品分类数据
    private static  List<ShangPinLeiXing1> mShangPinLeiXing1List=new ArrayList<ShangPinLeiXing1>();
    //获取商品分类
    public static List<ShangPinLeiXing1> getShangPinLeiXing1List() {
        return mShangPinLeiXing1List;
    }
    //共用变量对班的班次
    public static int mDuiBanBanCi;

    public static int getmDuiBanBanCi(){
        return mDuiBanBanCi;
    }


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub

        //单实例的实例
        mApplication=this;

        //以备份的方式安装本地数据库的第一个实例库
        initDBHlepByFirst();

        //读公共基础数据
        readPublicBaseData();

        super.onCreate();
    }



    public void initDBHlepByFirst(){
        copyDBFirstToDBFile();
        mDB1Help = MyOpenHelp.getInstance(this);
        mDB1Help.getReadableDatabase();
    }

    private void copyDBFirstToDBFile() {
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;

        try {
            InputStream is =getResources().openRawResource(R.raw.sales);
            //把字节流转换为带缓存的字节流
            bis=new BufferedInputStream(is);

            //准备写的流
            //通过上下文提供的方法getDatabasePath，获取数据库文件"tcrecommd.dat
            // 所对应的文件；
            File dbFile=this.getDatabasePath("sales.db");
            //如何获取文件的绝对路径
            //dbFile.getAbsolutePath();

            //因为数据库文件不存在，需要手工创建(后续部分通过数据库管理工具，会自动创建)
            //获取dbFile的目录
            //String path1=dbFile.getPath();返回的不是目录部分
            String path=dbFile.getParent();
            //把路径封装成文件
            File pathFile=new File(path);
            //判断路径文件是否存在，并且确定是路径
            if(!pathFile.exists() || !pathFile.isDirectory()) {
                //pathFile.mkdir();只创建一级新的目录
                pathFile.mkdirs(); //可以多级新目录同时创建
            }
            //把文件转换为字节文件写入流
            //Constructs a new FileOutputStream that writes to file.
            // The file will be truncated if it exists,
            // and created if it doesn't exist.
            // 如果文件不存在则创建一个新文件，如果目录不存在则不会创建目录，如果文件存在则长度清零。
            FileOutputStream fOS=new FileOutputStream(dbFile);
            //把字节文件写入流转换为带缓存的写入流
            bos=new BufferedOutputStream(fOS);

            //原型read(byte[] buffer) throws IOException   //
            //throws：方法生命时使用，表示方法体类可能有异常抛出，
            //   谁调用这个方法时需要通过try捕获这种异常
            //throw:在方法体内部使用，抛出一个异常
            //
            //bis.read(buffer);
            //原型read(byte[] 	byteBuffer, int byteOffset, int byteCount)
            //在当前流的当前位置开始读长度为byteCount个字节，
            // 放到字节数组byteBuffer,从数组的第byteOffset个偏移位置开始存放；
            int bufLenght=1024*30;
            int readLeng=0;
            byte[] byteBuf=new byte[bufLenght];

            readLeng= bis.read(byteBuf, 0, bufLenght);
            while(readLeng>0){
                //一般规律：流读写中，如果类名中没有Reader、Writer字样的都是字节，否则是字符。
                //原型:write(byte[] buffer, int offset, int length)
                //buffer:需要写入的字节数组
                //offset:从数组的第offset个偏移位置开始
                //length:本次写入的长度
                bos.write(byteBuf, 0, readLeng);
                //开始下一轮的先读后写
                readLeng= bis.read(byteBuf, 0, bufLenght);
            }

            //把缓存内容写入物理介质
            bos.flush();
            //当读写发生异常时，此处不会执行，资源不能得到释放
            //释放流读流资源和写流资源
            //bis.close();
            //boStream.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            try {
                throw(new Exception("不让吃饭，没力气干活，停工"));
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }    //抛一个异常出去
            e.printStackTrace();
        } finally {
            //释放流读流资源和写流资源
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onTerminate() {
        // TODO Auto-generated method stub
        //关闭数据库
        mDB1Help.close();
        //关闭打开未能关闭的Activity
        exitActivity();

        super.onTerminate();
    }
    public static MyOpenHelp getDb1Help(){
        return mDB1Help;
    }
    public void initjpushClear(){
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this); //极光推送的初始化
        JPushInterface.setAlias(this, "aa", null);
    }
    public void initjpushSecurity(){
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this); //极光推送的初始化
        JPushInterface.setAlias(this,"bb",null);
    }

    /***
     * 读公共基础信息内容
     */
    private void readPublicBaseData(){

        //模拟从网络下载商品分类信息到到本地库
        if(MyDebug.DEMO_ParseAndWriteShangPinLeiXing){
            ShangPinLeiXingManager manager=new ShangPinLeiXingManager();
            manager.paserAndWriteShangPinLeiXin("");
        }

        //重新读商品分类信息到公共变量mShangPinLeiXing1List中
        ShangPinLeiXingManager shangPinLeiXingManager=new ShangPinLeiXingManager();
        shangPinLeiXingManager.readShangPinLeiXing();

        //向本地库商品表中，添加第1大类前7小类的商品
        if(MyDebug.DEMO_ParseAndWriteShangPin){
            ShangPinManager manager=new ShangPinManager();
            manager.readShangPinListByLeiBieBianHao(1001001);
            manager.readShangPinListByLeiBieBianHao(1001002);
            manager.readShangPinListByLeiBieBianHao(1001003);
            manager.readShangPinListByLeiBieBianHao(1001004);
            manager.readShangPinListByLeiBieBianHao(1001005);
            manager.readShangPinListByLeiBieBianHao(1001006);
            manager.readShangPinListByLeiBieBianHao(1001007);
        }

              if (MyDebug.DEMO_ParseAndSelectJiaoBanShangPin){
            JiaoBanShangPinDao jiaoBanShangPinDao=new JiaoBanShangPinDao();
            jiaoBanShangPinDao.readJiaoBanShangPinInfo();
        }
//        if (MyDebug.DEMO_ParseAndSelectJiaoBanShiYi){
//            JiaoBanShiYiDao jiaoBanShiYiDao=new JiaoBanShiYiDao();
//            jiaoBanShiYiDao.selectJieBanShiYiInfo();
//        }

        if (MyDebug.Demo_classify){
            JiaoBanShangPinDao jiaoBanShangPinDao=new JiaoBanShangPinDao();
           jiaoBanShangPinDao.readJiaoBanShangPinList();
        }

    }
}

