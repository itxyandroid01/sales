package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/18.
 * 需 添加openHelper方法调用 db内容
 *
 */
public class ShangPin {


    private String mShangPinBianHao;//商品编号
    private int mLeiBieBianHao;//类别编号
    private String mMingCheng;//名称
    private String mQuanCheng;//全称
    private String mMiaoShu;//描述
    private String mGuiGe;//规格
    private String mXingHao;//型号
    private int mLingShouJia;//零售价
    private int mZheKeLv;//折扣率
    private int mKuCunShuLiang;//库存数量
    private String mXiaoShouJianYi;//销售建议
    private String mTuPianMingCheng;//图片名称
    private String mZhiChiHuoDong;//支持活动
    private String mHuoJiaWeiZhi;//货架位置

    public String getShangPinBianHao() {
        return mShangPinBianHao;
    }

    public int getLeiBieBianHao() {
        return mLeiBieBianHao;
    }

    public String getMingCheng() {
        return mMingCheng;
    }

    public String getQuanCheng() {
        return mQuanCheng;
    }

    public String getMiaoShu() {
        return mMiaoShu;
    }

    public String getGuiGe() {
        return mGuiGe;
    }

    public String getXingHao() {
        return mXingHao;
    }

    public int getLingShouJia() {
        return mLingShouJia;
    }

    public int getZheKeLv() {
        return mZheKeLv;
    }

    public int getKuCunShuLiang() {
        return mKuCunShuLiang;
    }

    public String getXiaoShouJianYi() {
        return mXiaoShouJianYi;
    }

    public String getTuPianMingCheng() {
        return mTuPianMingCheng;
    }

    public String getZhiChiHuoDong() {
        return mZhiChiHuoDong;
    }

    public String getHuoJiaWeiZhi() {
        return mHuoJiaWeiZhi;
    }

    public ShangPin(){

    }
    public ShangPin(String mingCheng,int kucun,int lingshou,int zhekou){
        mMingCheng=mingCheng;
        mKuCunShuLiang=kucun;
        mLingShouJia=lingshou;
        mZheKeLv=zhekou;
    }

    public void setShangPinBianHao(String shangPinBianHao) {
        mShangPinBianHao = shangPinBianHao;
    }

    public void setLeiBieBianHao(int leiBieBianHao) {
        mLeiBieBianHao = leiBieBianHao;
    }

    public void setMingCheng(String mingCheng) {
        mMingCheng = mingCheng;
    }

    public void setQuanCheng(String quanCheng) {
        mQuanCheng = quanCheng;
    }

    public void setMiaoShu(String miaoShu) {
        mMiaoShu = miaoShu;
    }

    public void setGuiGe(String guiGe) {
        mGuiGe = guiGe;
    }

    public void setXingHao(String xingHao) {
        mXingHao = xingHao;
    }

    public void setLingShouJia(int lingShouJia) {
        mLingShouJia = lingShouJia;
    }

    public void setZheKeLv(int zheKeLv) {
        mZheKeLv = zheKeLv;
    }

    public void setKuCunShuLiang(int kuCunShuLiang) {
        mKuCunShuLiang = kuCunShuLiang;
    }

    public void setXiaoShouJianYi(String xiaoShouJianYi) {
        mXiaoShouJianYi = xiaoShouJianYi;
    }

    public void setTuPianMingCheng(String tuPianMingCheng) {
        mTuPianMingCheng = tuPianMingCheng;
    }

    public void setZhiChiHuoDong(String zhiChiHuoDong) {
        mZhiChiHuoDong = zhiChiHuoDong;
    }

    public void setHuoJiaWeiZhi(String huoJiaWeiZhi) {
        mHuoJiaWeiZhi = huoJiaWeiZhi;
    }

    public ShangPin(String shangPinBianHao,
                    int leiBieBianHao,
                    String mingCheng,
                    String quanCheng,
                    String miaoShu,
                    String guiGe,
                    String xingHao,
                    int lingShouJia,
                    int zheKeLv,
                    int kuCunShuLiang,
                    String xiaoShouJianYi,
                    String tuPianMingCheng,
                    String zhiChiHuoDong,
                    String huoJiaWeiZhi) {
        mShangPinBianHao = shangPinBianHao;
        mLeiBieBianHao = leiBieBianHao;
        mMingCheng = mingCheng;
        mQuanCheng = quanCheng;
        mMiaoShu = miaoShu;
        mGuiGe = guiGe;
        mXingHao = xingHao;
        mLingShouJia = lingShouJia;
        mZheKeLv = zheKeLv;
        mKuCunShuLiang = kuCunShuLiang;
        mXiaoShouJianYi = xiaoShouJianYi;
        mTuPianMingCheng = tuPianMingCheng;
        mZhiChiHuoDong = zhiChiHuoDong;
        mHuoJiaWeiZhi = huoJiaWeiZhi;
    }


}

