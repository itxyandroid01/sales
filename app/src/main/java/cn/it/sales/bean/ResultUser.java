package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/4.
 */
public class ResultUser  {
    int result;
    String username ;
    String password ;
    long groupid;
    String message;
    public ResultUser(){

    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultUser(int result, String username, String password, long groupid, String message, int nick) {
        this.result = result;
        this.username = username;
        this.password = password;
        this.groupid = groupid;
        this.message = message;
    }

    public ResultUser(int result, String message) {
        this.result = result;
        this.message = message;
    }
    public ResultUser(int result,long groupid){
        this.result=result;
        this.groupid=groupid;
    }
}
