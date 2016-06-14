package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/23.
 */
public class JiaoBanShangPin {
    private int mBanCi;//班次
    private int mGongHao;//工号
    private String mShangPinBianHao;//商品编号
    private int mJieBanKuCunLiang;//接班库存量
    private int mXiaoShouShuLiang;//销售数量
    private int mXiaoShouJinE;//销售金额
    private int mJiaoBanKuCunLiang;//交班库存量
    private  String mMingcCheng;

    public String getMingcCheng() {
        return mMingcCheng;
    }

    public JiaoBanShangPin(int jieBanKuCunLiang, int xiaoShouShuLiang, int jiaoBanKuCunLiang, String mingcCheng) {
        mJieBanKuCunLiang = jieBanKuCunLiang;
        mXiaoShouShuLiang = xiaoShouShuLiang;
        mJiaoBanKuCunLiang = jiaoBanKuCunLiang;
        mMingcCheng = mingcCheng;
    }

    public void setMingcCheng(String mingcCheng) {
        mMingcCheng = mingcCheng;
    }

    public JiaoBanShangPin() {
    }

    public int getBanCi() {
        return mBanCi;
    }

    public void setBanCi(int banCi) {
        mBanCi = banCi;
    }

    public int getGongHao() {
        return mGongHao;
    }

    public void setGongHao(int gongHao) {
        mGongHao = gongHao;
    }

    public String getShangPinBianHao() {
        return mShangPinBianHao;
    }

    public void setShangPinBianHao(String shangPinBianHao) {
        mShangPinBianHao = shangPinBianHao;
    }

    public int getJieBanKuCunLiang() {
        return mJieBanKuCunLiang;
    }

    public void setJieBanKuCunLiang(int jieBanKuCunLiang) {
        mJieBanKuCunLiang = jieBanKuCunLiang;
    }

    public int getXiaoShouShuLiang() {
        return mXiaoShouShuLiang;
    }

    public void setXiaoShouShuLiang(int xiaoShouShuLiang) {
        mXiaoShouShuLiang = xiaoShouShuLiang;
    }

    public int getXiaoShouJinE() {
        return mXiaoShouJinE;
    }

    public void setXiaoShouJinE(int xiaoShouJinE) {
        mXiaoShouJinE = xiaoShouJinE;
    }

    public int getJiaoBanKuCunLiang() {
        return mJiaoBanKuCunLiang;
    }

    public void setJiaoBanKuCunLiang(int jiaoBanKuCunLiang) {
        mJiaoBanKuCunLiang = jiaoBanKuCunLiang;
    }

    public JiaoBanShangPin(int banCi, int gongHao, String shangPinBianHao, int jieBanKuCunLiang, int xiaoShouShuLiang, int xiaoShouJinE, int jiaoBanKuCunLiang) {
        mBanCi = banCi;
        mGongHao = gongHao;
        mShangPinBianHao = shangPinBianHao;
        mJieBanKuCunLiang = jieBanKuCunLiang;
        mXiaoShouShuLiang = xiaoShouShuLiang;
        mXiaoShouJinE = xiaoShouJinE;
        mJiaoBanKuCunLiang = jiaoBanKuCunLiang;
    }

    private int shangpinjiage;

    public int getShangpinjiage() {
        return shangpinjiage;
    }

    public void setShangpinjiage(int shangpinjiage) {
        this.shangpinjiage = shangpinjiage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public JiaoBanShangPin(String name, int shangpinjiage, int xiaoshoushuliang) {
        this.name = name;
        this.shangpinjiage = shangpinjiage;
        this.mXiaoShouShuLiang = xiaoshoushuliang;

    }
}
