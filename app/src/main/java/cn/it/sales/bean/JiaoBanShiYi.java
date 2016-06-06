package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/23.
 */
public class JiaoBanShiYi {
    private int mBanCi;//班次
    private int mGongHao;//工号
    private String mJieBanShiYi;//接班事宜
    private String mJiaoBanShiYi;//交班事宜

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

    public String getJieBanShiYi() {
        return mJieBanShiYi;
    }

    public void setJieBanShiYi(String jieBanShiYi) {
        mJieBanShiYi = jieBanShiYi;
    }

    public String getJiaoBanShiYi() {
        return mJiaoBanShiYi;
    }

    public void setJiaoBanShiYi(String jiaoBanShiYi) {
        mJiaoBanShiYi = jiaoBanShiYi;
    }

    public JiaoBanShiYi(int banCi, int gongHao, String jieBanShiYi, String jiaoBanShiYi) {
        mBanCi = banCi;
        mGongHao = gongHao;
        mJieBanShiYi = jieBanShiYi;
        mJiaoBanShiYi = jiaoBanShiYi;
    }

    public JiaoBanShiYi(String jiaoBanShiYi) {
        mJiaoBanShiYi = jiaoBanShiYi;
    }
}
