package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ShangChuanRenWu {
    private int mBianHao;
    private String mMiaoShu;
    private String mRenWuShiJian;
    private int mWanChengQingKuang;

    public int getBianHao() {
        return mBianHao;
    }

    public void setBianHao(int bianHao) {
        mBianHao = bianHao;
    }

    public String getMiaoShu() {
        return mMiaoShu;
    }

    public void setMiaoShu(String miaoShu) {
        mMiaoShu = miaoShu;
    }

    public String getRenWuShiJian() {
        return mRenWuShiJian;
    }

    public void setRenWuShiJian(String renWuShiJian) {
        mRenWuShiJian = renWuShiJian;
    }

    public int getWanChengQingKuang() {
        return mWanChengQingKuang;
    }

    public void setWanChengQingKuang(int wanChengQingKuang) {
        mWanChengQingKuang = wanChengQingKuang;
    }

    public ShangChuanRenWu(int bianHao, String miaoShu, String renWuShiJian, int wanChengQingKuang) {
        mBianHao = bianHao;
        mMiaoShu = miaoShu;
        mRenWuShiJian = renWuShiJian;
        mWanChengQingKuang = wanChengQingKuang;
    }
}
