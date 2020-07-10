package com.oracle.entity;
//商品类型实体类
public class Producttype {
    private Integer id;//主键
    private String name;//类型名称
    public Producttype() {
        super();
    }
    public Producttype(Integer id,String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
