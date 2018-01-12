package com.wintop.ocr.entity;

import java.io.Serializable;

/**
 * 车牌entity
 */
public class LicensePlate implements Serializable{

    /*身份证号码*/
    private String plateNum;

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }
}