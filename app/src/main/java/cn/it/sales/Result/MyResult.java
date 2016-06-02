package cn.it.sales.Result;

/**
 * Created by Administrator on 2016/5/4.
 */
public class MyResult {
    private int code=0;
    private String message="";


    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    private  long groupid;
    public MyResult(){

    }
    public MyResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setCodeAndMessage(int code,String message){
        this.code=code;
        this.message=message;
    }
    public void setCodeAndGroup(int i, long groupId) {
        // TODO Auto-generated method stub
        this.code = i;
        this.groupid=groupId;
    }

}
