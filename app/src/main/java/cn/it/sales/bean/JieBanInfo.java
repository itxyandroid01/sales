package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/6/3.
 */
public class JieBanInfo {
    private int mBanCi;//班次
    private int mGongHao;//工号
    private String mXingMing;//姓名
    private int mJieBanGongHao;//接班工号
    private String mJieBanShiJian;//接班时间
    private int mXiaoShouShuLiang;//销售数量
    private int mXiaoShouJinE;//销售金额
    private int mJiaoBanGongHao;//交班工号
    private String mJiaoBanShiJian;//交班时间
    private int mShangChuan;//上传
    private String mShangChuanShiJian;//上传时间
    private String mDianPu;//店铺
    private int mJiaoBanZhuangTai;//交班状态
    private String mShangPinBianHao;//商品编号
    private int mJieBanKuCunLiang;//接班库存量
    //20161001,1001,"张丹",1000,"",20,5000,1001,"",1,"","牛顿国际",2,"10001001",300,200,"好好工作，没什么事宜","没有事宜",
    // 100200,"名称","全称","没有描述","规格","型号"
    private int mJiaoBanKuCunLiang;//交班库存量
    private String mJieBanShiYi;//接班事宜
    private String mJiaoBanShiYi;//交班事宜
    private int mLeiBieBianHao;//类别编号
    private String mMingCheng;//名称
    private String mQuanCheng;//全称
    private String mMiaoShu;//描述
    private String mGuiGe;//规格
    private String mXingHao;//型号
    //100,8,200,"销售建议","图片名称","支持活动","货架位置","类别名称",1,10001,"销售时间",88,66,"没有活动",1
    private int mLingShouJia;//零售价
    private int mZheKeLv;//折扣率
    private int mKuCunShuLiang;//库存数量
    private String mXiaoShouJianYi;//销售建议
    private String mTuPianMingCheng;//图片名称
    private String mZhiChiHuoDong;//支持活动
    private String mHuoJiaWeiZhi;//货架位置
    private String mLeibiemingcheng;//类别名称
    private int mJiBie;//级别
    private int mFuleibiebianhao;//父类别编号
    private String mXiaoShouShiJian;//销售时间
    private int mXiaoShouJiage;//销售价格
    private int mXiaoShouQianShuLiang;//销售前数量
    private String mCanYuHuoDong;//参与活动
    private int _id;//id号

    public JieBanInfo(int banCi, int gongHao, String xingMing, int jieBanGongHao, String jieBanShiJian, int xiaoShouShuLiang,
                      int xiaoShouJinE, int jiaoBanGongHao, String jiaoBanShiJian, int shangChuan, String shangChuanShiJian,
                      String dianPu, int jiaoBanZhuangTai, String shangPinBianHao, int jieBanKuCunLiang, int jiaoBanKuCunLiang,
                      String jieBanShiYi, String jiaoBanShiYi, int leiBieBianHao, String mingCheng, String quanCheng, String miaoShu,
                      String guiGe, String xingHao, int lingShouJia, int zheKeLv, int kuCunShuLiang, String xiaoShouJianYi,
                      String tuPianMingCheng, String zhiChiHuoDong, String huoJiaWeiZhi, String leibiemingcheng, int jiBie,
                      int fuleibiebianhao, String xiaoShouShiJian, int xiaoShouJiage, int xiaoShouQianShuLiang, String canYuHuoDong, int _id) {
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
        mDianPu = dianPu;
        mJiaoBanZhuangTai = jiaoBanZhuangTai;
        mShangPinBianHao = shangPinBianHao;
        mJieBanKuCunLiang = jieBanKuCunLiang;
        mJiaoBanKuCunLiang = jiaoBanKuCunLiang;
        mJieBanShiYi = jieBanShiYi;
        mJiaoBanShiYi = jiaoBanShiYi;
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
        mLeibiemingcheng = leibiemingcheng;
        mJiBie = jiBie;
        mFuleibiebianhao = fuleibiebianhao;
        mXiaoShouShiJian = xiaoShouShiJian;
        mXiaoShouJiage = xiaoShouJiage;
        mXiaoShouQianShuLiang = xiaoShouQianShuLiang;
        mCanYuHuoDong = canYuHuoDong;
        this._id = _id;
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

    public String getDianPu() {
        return mDianPu;
    }

    public void setDianPu(String dianPu) {
        mDianPu = dianPu;
    }

    public int getJiaoBanZhuangTai() {
        return mJiaoBanZhuangTai;
    }

    public void setJiaoBanZhuangTai(int jiaoBanZhuangTai) {
        mJiaoBanZhuangTai = jiaoBanZhuangTai;
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

    public int getJiaoBanKuCunLiang() {
        return mJiaoBanKuCunLiang;
    }

    public void setJiaoBanKuCunLiang(int jiaoBanKuCunLiang) {
        mJiaoBanKuCunLiang = jiaoBanKuCunLiang;
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

    public int getLeiBieBianHao() {
        return mLeiBieBianHao;
    }

    public void setLeiBieBianHao(int leiBieBianHao) {
        mLeiBieBianHao = leiBieBianHao;
    }

    public String getMingCheng() {
        return mMingCheng;
    }

    public void setMingCheng(String mingCheng) {
        mMingCheng = mingCheng;
    }

    public String getQuanCheng() {
        return mQuanCheng;
    }

    public void setQuanCheng(String quanCheng) {
        mQuanCheng = quanCheng;
    }

    public String getMiaoShu() {
        return mMiaoShu;
    }

    public void setMiaoShu(String miaoShu) {
        mMiaoShu = miaoShu;
    }

    public String getGuiGe() {
        return mGuiGe;
    }

    public void setGuiGe(String guiGe) {
        mGuiGe = guiGe;
    }

    public String getXingHao() {
        return mXingHao;
    }

    public void setXingHao(String xingHao) {
        mXingHao = xingHao;
    }

    public int getLingShouJia() {
        return mLingShouJia;
    }

    public void setLingShouJia(int lingShouJia) {
        mLingShouJia = lingShouJia;
    }

    public int getZheKeLv() {
        return mZheKeLv;
    }

    public void setZheKeLv(int zheKeLv) {
        mZheKeLv = zheKeLv;
    }

    public int getKuCunShuLiang() {
        return mKuCunShuLiang;
    }

    public void setKuCunShuLiang(int kuCunShuLiang) {
        mKuCunShuLiang = kuCunShuLiang;
    }

    public String getXiaoShouJianYi() {
        return mXiaoShouJianYi;
    }

    public void setXiaoShouJianYi(String xiaoShouJianYi) {
        mXiaoShouJianYi = xiaoShouJianYi;
    }

    public String getTuPianMingCheng() {
        return mTuPianMingCheng;
    }

    public void setTuPianMingCheng(String tuPianMingCheng) {
        mTuPianMingCheng = tuPianMingCheng;
    }

    public String getZhiChiHuoDong() {
        return mZhiChiHuoDong;
    }

    public void setZhiChiHuoDong(String zhiChiHuoDong) {
        mZhiChiHuoDong = zhiChiHuoDong;
    }

    public String getHuoJiaWeiZhi() {
        return mHuoJiaWeiZhi;
    }

    public void setHuoJiaWeiZhi(String huoJiaWeiZhi) {
        mHuoJiaWeiZhi = huoJiaWeiZhi;
    }

    public String getLeibiemingcheng() {
        return mLeibiemingcheng;
    }

    public void setLeibiemingcheng(String leibiemingcheng) {
        mLeibiemingcheng = leibiemingcheng;
    }

    public int getJiBie() {
        return mJiBie;
    }

    public void setJiBie(int jiBie) {
        mJiBie = jiBie;
    }

    public int getFuleibiebianhao() {
        return mFuleibiebianhao;
    }

    public void setFuleibiebianhao(int fuleibiebianhao) {
        mFuleibiebianhao = fuleibiebianhao;
    }

    public String getXiaoShouShiJian() {
        return mXiaoShouShiJian;
    }

    public void setXiaoShouShiJian(String xiaoShouShiJian) {
        mXiaoShouShiJian = xiaoShouShiJian;
    }

    public int getXiaoShouJiage() {
        return mXiaoShouJiage;
    }

    public void setXiaoShouJiage(int xiaoShouJiage) {
        mXiaoShouJiage = xiaoShouJiage;
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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
