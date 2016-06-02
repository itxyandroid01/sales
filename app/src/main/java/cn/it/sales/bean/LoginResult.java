package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/6/1.
 */
public class LoginResult {

private String mBaiDu;

    public String getBaiDu() {
        return mBaiDu;
    }

    public void setBaiDu(String baiDu) {
        mBaiDu = baiDu;
    }
    public LoginResult(String baiDu){
        this.mBaiDu=baiDu;
    }


    public String toString() {

        return mBaiDu;
    }
}
