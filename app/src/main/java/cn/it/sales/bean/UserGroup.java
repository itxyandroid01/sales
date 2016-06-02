package cn.it.sales.bean;

/**
 * Created by Administrator on 2016/5/4.
 */
public class UserGroup {
    private long id;
    private long userid;
    private long groupId=-1;
    public void setId(long id){
        this.id=id;
    }
    public long getId(){
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public UserGroup(long id, long userid, long groupId) {
        super();
        this.id = id;
        this.userid = userid;
        this.groupId = groupId;
    }
    public UserGroup(){

    }
    public UserGroup(int mPosition) {
        // TODO Auto-generated constructor stub

        setGroupId(mPosition);
    }
}
