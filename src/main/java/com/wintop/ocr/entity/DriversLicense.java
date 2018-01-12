package com.wintop.ocr.entity;

import java.io.Serializable;

/**
 * 驾照entity
 */
public class DriversLicense implements Serializable{

    /*姓名字符串，识别不出来时，可能为"NoResult"/"InvalidInput*/
    private String name;
    /*驾驶证号，识别错误时，为"NoResult"/"InvalidInput*/
    private String num;
    /*驾驶证准驾车型*/
    private String vehicle_type;
    /*驾驶证有效期开始时间*/
    private String start_date;
    /*驾驶证有效期时长*/
    private String end_date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
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
}