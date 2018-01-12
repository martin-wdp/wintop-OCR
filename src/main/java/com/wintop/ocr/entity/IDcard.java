package com.wintop.ocr.entity;

import java.io.Serializable;

/**
 * 身份证正反面信息entity
 */
public class IDcard implements Serializable{

    /*身份证号码*/
    private String num;
    /*姓名*/
    private String name;
    /*性别*/
    private String sex;
    /*地址*/
    private String address;
    /*出生日期*/
    private String birth;
    /*民族*/
    private String nationality;
    /*证件有效起期*/
    private String start_date;
    /*证件有效止期*/
    private String end_date;
    /*发证机关*/
    private String issue;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}