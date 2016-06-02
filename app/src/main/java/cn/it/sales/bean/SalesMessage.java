package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/30.
 */
public class SalesMessage {
    private String mShangPinMingCheng;
    private int mXiaoShouShuLiang;
    private int mXiaoShouJiaGe;
    private String mXiaoShouShiJIan;
    public SalesMessage(){

    }

    public String getShangPinMingCheng() {
        return mShangPinMingCheng;
    }

    public void setShangPinMingCheng(String shangPinMingCheng) {
        mShangPinMingCheng = shangPinMingCheng;
    }

    public int getXiaoShouShuLiang() {
        return mXiaoShouShuLiang;
    }

    public void setXiaoShouShuLiang(int xiaoShouShuLiang) {
        mXiaoShouShuLiang = xiaoShouShuLiang;
    }

    public int getXiasoShouJiaGe() {
        return mXiaoShouJiaGe;
    }

    public void setXiasoShouJiaGe(int xiasoShouJinEr) {
        mXiaoShouJiaGe = xiasoShouJinEr;
    }

    public String getXiaoShouShiJIan() {
        return mXiaoShouShiJIan;
    }

    public void setXiaoShouShiJIan(String xiaoShouShiJIan) {
        mXiaoShouShiJIan = xiaoShouShiJIan;
    }

    public SalesMessage(String shangPinMingCheng, int xiaoShouShuLiang, int xiasoShouJiaGe, String xiaoShouShiJIan) {
        mShangPinMingCheng = shangPinMingCheng;
        mXiaoShouShuLiang = xiaoShouShuLiang;
        mXiaoShouJiaGe = xiasoShouJiaGe;
        mXiaoShouShiJIan = xiaoShouShiJIan;
    }
}
