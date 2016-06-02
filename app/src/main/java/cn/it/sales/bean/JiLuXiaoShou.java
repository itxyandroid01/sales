package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/18.
 */
public class JiLuXiaoShou {
    private int mShangPinBianHao;//商品编号
    private int mGongHao;//工号
    private int mBanCi;//班次
    private String mXiaoShouShiJian;//销售时间
    private int mXiaoShouShuLiang;//销售数量
    private int mLingShouJia;//零售价
    private int mXiaoShouJiage;//销售价格
    private int mZheKouLv;//折扣价
    private int mXiaoShouQianShuLiang;//销售前数量
    private String mCanYuHuoDong;//参与活动
    private int mShangChuan;//上传
    private String mShangChuanShiJian;//上传时间
    private int _id;//id号
    public JiLuXiaoShou(String mShangPinBiaoHao, int mGonghao, int mbanci, String mxiaoshoushijian, int xiaoshoushuliang, int lingshoujia, int xiaoshoujiage, int zhekoulv, int xiaoshouqianshuliang, String canyuhuodong, int shangchuan, String shangchuanshijian, int id, String shangpinmingcheng) {

    }

    public JiLuXiaoShou() {

    }

    public JiLuXiaoShou(int shangPinBianHao, int gongHao, int banCi, String xiaoShouShiJian, int xiaoShouShuLiang, int lingShouJia, int xiaoShouJiage, int zheKouLv, int xiaoShouQianShuLiang, String canYuHuoDong, int shangChuan, String shangChuanShiJian, int _id) {
        mShangPinBianHao = shangPinBianHao;
        mGongHao = gongHao;
        mBanCi = banCi;
        mXiaoShouShiJian = xiaoShouShiJian;
        mXiaoShouShuLiang = xiaoShouShuLiang;
        mLingShouJia = lingShouJia;
        mXiaoShouJiage = xiaoShouJiage;
        mZheKouLv = zheKouLv;
        mXiaoShouQianShuLiang = xiaoShouQianShuLiang;
        mCanYuHuoDong = canYuHuoDong;
        mShangChuan = shangChuan;
        mShangChuanShiJian = shangChuanShiJian;
        this._id = _id;
    }

    public int getShangPinBianHao() {
        return mShangPinBianHao;
    }

    public void setShangPinBianHao(int shangPinBianHao) {
        mShangPinBianHao = shangPinBianHao;
    }

    public int getGongHao() {
        return mGongHao;
    }

    public void setGongHao(int gongHao) {
        mGongHao = gongHao;
    }

    public int getBanCi() {
        return mBanCi;
    }

    public void setBanCi(int banCi) {
        mBanCi = banCi;
    }

    public String getXiaoShouShiJian() {
        return mXiaoShouShiJian;
    }

    public void setXiaoShouShiJian(String xiaoShouShiJian) {
        mXiaoShouShiJian = xiaoShouShiJian;
    }

    public int getXiaoShouShuLiang() {
        return mXiaoShouShuLiang;
    }

    public void setXiaoShouShuLiang(int xiaoShouShuLiang) {
        mXiaoShouShuLiang = xiaoShouShuLiang;
    }

    public int getLingShouJia() {
        return mLingShouJia;
    }

    public void setLingShouJia(int lingShouJia) {
        mLingShouJia = lingShouJia;
    }

    public int getXiaoShouJiage() {
        return mXiaoShouJiage;
    }

    public void setXiaoShouJiage(int xiaoShouJiage) {
        mXiaoShouJiage = xiaoShouJiage;
    }

    public int getZheKouLv() {
        return mZheKouLv;
    }

    public void setZheKouLv(int zheKouLv) {
        mZheKouLv = zheKouLv;
    }

    public int getXiaoShouQianShuLiang() {
        return mXiaoShouQianShuLiang;
    }

    public void setXiaoShouQianShuLiang(int xiaoShouQianShuLiang) {
        mXiaoShouQianShuLiang = xiaoShouQianShuLiang;
    }

    public String getCanYuHuoDong() {
        return mCanYuHuoDong;
    }

    public void setCanYuHuoDong(String canYuHuoDong) {
        mCanYuHuoDong = canYuHuoDong;
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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
