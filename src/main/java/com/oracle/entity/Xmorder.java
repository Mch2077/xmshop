package com.oracle.entity;
public class Xmorder {
    private String oid;//订单号
    private Integer customerid;//客户ID
    private Integer addressid;//地址ID
    private Double totalprice;//总价格
    private String remarks;//备注
    private String status;//状态
    private java.util.Date odate;//订单时间
    public Xmorder() {
        super();
    }
    public Xmorder(String oid,Integer customerid,Integer addressid,Double totalprice,String remarks,String status,java.util.Date odate) {
        super();
        this.oid = oid;
        this.customerid = customerid;
        this.addressid = addressid;
        this.totalprice = totalprice;
        this.remarks = remarks;
        this.status = status;
        this.odate = odate;
    }
    public String getOid() {
        return this.oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getCustomerid() {
        return this.customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Integer getAddressid() {
        return this.addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public Double getTotalprice() {
        return this.totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.Date getOdate() {
        return this.odate;
    }

    public void setOdate(java.util.Date odate) {
        this.odate = odate;
    }

}
