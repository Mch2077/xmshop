package com.oracle.entity;
//用户的实体类
public class Users {
    private Integer uid;
    private String uname;
    private String upass;
    private String usex;
    private Integer uage;
    private String udepartment;
    private String realname;
    private String uimage;
    private Integer roleid;
    @Override
	public String toString() {
		return "账号:"+this.uname + "  密码:"+this.upass;
	}
	public Users() {
        super();
    }
    public Users(Integer uid,String uname,String upass,String usex,Integer uage,String udepartment,String realname,String uimage,Integer roleid) {
        super();
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;
        this.usex = usex;
        this.uage = uage;
        this.udepartment = udepartment;
        this.realname = realname;
        this.uimage = uimage;
        this.roleid = roleid;
    }
    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return this.upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUsex() {
        return this.usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public Integer getUage() {
        return this.uage;
    }

    public void setUage(Integer uage) {
        this.uage = uage;
    }

    public String getUdepartment() {
        return this.udepartment;
    }

    public void setUdepartment(String udepartment) {
        this.udepartment = udepartment;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUimage() {
        return this.uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public Integer getRoleid() {
        return this.roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

}
