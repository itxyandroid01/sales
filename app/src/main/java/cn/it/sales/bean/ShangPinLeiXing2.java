package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ShangPinLeiXing2 {
    //2级分类的基本信息
   private int mLeibiebianhao;//类别编号
    private String mLeibiemingcheng;//；类别名称
    private int mJiBie;//级别
    private int mFuleibiebianhao;//父类别编号
    private String mTupianmingcheng;//图片编号

    public int getLeibiebianhao() {
        return mLeibiebianhao;
    }

    public void setLeibiebianhao(int leibiebianhao) {
        mLeibiebianhao = leibiebianhao;
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

    public String getTupianmingcheng() {
        return mTupianmingcheng;
    }

    public void setTupianmingcheng(String tupianmingcheng) {
        mTupianmingcheng = tupianmingcheng;
    }

    public ShangPinLeiXing2(int leibiebianhao, String leibiemingcheng, int jiBie, int fuleibiebianhao, String tupianmingcheng) {
        mLeibiebianhao = leibiebianhao;
        mLeibiemingcheng = leibiemingcheng;
        mJiBie = jiBie;
        mFuleibiebianhao = fuleibiebianhao;
        mTupianmingcheng = tupianmingcheng;
    }
}
