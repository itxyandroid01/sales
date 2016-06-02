package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/23.
 */
public class JiaoBan {
    private int mBanCi;//班次
    private int mGongHao;//工号
    private String mXingMing;//名称
    private int mJieBanGongHao;//接班工号
    private String mJieBanShiJian;//接班时间
    private int mXiaoShouShuLiang;//销售数量
    private int mXiaoShouJinE;//销售金额
    private int mJiaoBanGongHao;//交班工号
    private String mJiaoBanShiJian;//交班时间
    private int mShangChuan;//上传
    private String mShangChuanShiJian;//上传时间
    private int mJiaoBanZhuangTai;//交班状态
    private  int mJieBanBanCi;//接班班次

    public int getJieBanBanCi() {
        return mJieBanBanCi;
    }

    public void setJieBanBanCi(int jieBanBanCi) {
        mJieBanBanCi = jieBanBanCi;
    }

    public int getJiaoBanZhuangTai() {
        return mJiaoBanZhuangTai;
    }

    public void setJiaoBanZhuangTai(int jiaoBanZhuangTai) {
        mJiaoBanZhuangTai = jiaoBanZhuangTai;
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

    public String getXingMing() {
        return mXingMing;
    }

    public void setXingMing(String xingMing) {
        mXingMing = xingMing;
    }

    public int getJieBanGongHao() {
        return mJieBanGongHao;
    }

    public void setJieBanGongHao(int jieBanGongHao) {
        mJieBanGongHao = jieBanGongHao;
    }

    public String getJieBanShiJian() {
        return mJieBanShiJian;
    }

    public void setJieBanShiJian(String jieBanShiJian) {
        mJieBanShiJian = jieBanShiJian;
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

    public int getJiaoBanGongHao() {
        return mJiaoBanGongHao;
    }

    public void setJiaoBanGongHao(int jiaoBanGongHao) {
        mJiaoBanGongHao = jiaoBanGongHao;
    }

    public String getJiaoBanShiJian() {
        return mJiaoBanShiJian;
    }

    public void setJiaoBanShiJian(String jiaoBanShiJian) {
        mJiaoBanShiJian = jiaoBanShiJian;
    }

    public int getShangChuan() {
        return mShangChuan;
    }

    public void setShangChuan(int shangChuan) {
        mShangChuan = shangChuan;
    }

    public String getShangChuanShiJian() {
        return mShangChuanShiJian;
    }

    public void setShangChuanShiJian(String shangChuanShiJian) {
        mShangChuanShiJian = shangChuanShiJian;
    }

    public JiaoBan(int banCi, int gongHao, String xingMing, int jieBanGongHao, String jieBanShiJian, int xiaoShouShuLiang, int xiaoShouJinE, int jiaoBanGongHao, String jiaoBanShiJian, int shangChuan, String shangChuanShiJian, int jiaoBanZhuangTai, int jieBanBanCi) {
        mBanCi = banCi;
        mGongHao = gongHao;
        mXingMing = xingMing;
        mJieBanGongHao = jieBanGongHao;
        mJieBanShiJian = jieBanShiJian;
        mXiaoShouShuLiang = xiaoShouShuLiang;
        mXiaoShouJinE = xiaoShouJinE;
        mJiaoBanGongHao = jiaoBanGongHao;
        mJiaoBanShiJian = jiaoBanShiJian;
        mShangChuan = shangChuan;
        mShangChuanShiJian = shangChuanShiJian;
        mJiaoBanZhuangTai = jiaoBanZhuangTai;
        mJieBanBanCi = jieBanBanCi;
    }

    public JiaoBan() {
    }
}
