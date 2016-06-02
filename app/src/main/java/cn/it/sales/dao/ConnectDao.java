package cn.it.sales.dao;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ConnectDao {
    public static final int  GE_REN_RE_XIAO_QIAN_10=1;
    public static final int  GE_REN_ZHI_XIAO_QIAN_10=2;
    public static final int  TONG_SHI_RE_XIAO_QIAN_10=3;
    public static final int  TONG_SHI_ZHI_XIAO_QIAN_10=4;

    String sql1="select sp.mingcheng,jb.xiaoshoushuliang,sp.lingshoujia from t_jiaoban_shangpin jb," +
            "t_shangpin sp where jb.shangpinbianhao =sp.shangpinbianhao order by jb.xiaoshoushuliang  limit 0,10";

    String sql2="select sp.mingcheng,jb.xiaoshoushuliang,sp.lingshoujia from t_jiaoban_shangpin jb," +
            "t_shangpin sp where jb.shangpinbianhao =sp.shangpinbianhao order by jb.xiaoshoushuliang desc limit 0,10";

    String sql3="select sp.mingcheng,jb.xiaoshoushuliang,jb.gonghao from t_jiaoban_shangpin jb,"+
            " t_shangpin sp where jb.shangpinbianhao =sp.shangpinbianhao order by jb.xiaoshoushuliang desc limit 0,10";

    String sql4="select sp.mingcheng,jb.xiaoshoushuliang,jb.gonghao from t_jiaoban_shangpin jb," +
            " t_shangpin sp where jb.shangpinbianhao =sp.shangpinbianhao order by jb.xiaoshoushuliang  limit 0,10";
    String[] sql={sql1,sql2,sql3,sql4};

    /**
     * 获取sql参数
     * @param i
     * @return
     */
   public String getSql(int i){

       return sql[i];
   }




}
