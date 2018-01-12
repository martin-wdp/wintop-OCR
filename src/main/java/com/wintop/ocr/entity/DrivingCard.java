package com.wintop.ocr.entity;

import java.io.Serializable;

/**
 * 行驶执照entity
 */
public class DrivingCard implements Serializable{

    /*所有人名称*/
    private String owner;
    /*车牌号码*/
    private String plate_num;
    /*车辆类型*/
    private String vehicle_type;
    /*车辆识别代号*/
    private String vin;
    /*发动机号码*/
    private String engine_num;
    /*注册日期/车辆登记日期*/
    private String register_date;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPlate_num() {
        return plate_num;
    }

    public void setPlate_num(String plate_num) {
        this.plate_num = plate_num;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngine_num() {
        return engine_num;
    }

    public void setEngine_num(String engine_num) {
        this.engine_num = engine_num;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }
}