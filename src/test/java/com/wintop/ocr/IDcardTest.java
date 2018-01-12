package com.wintop.ocr.alibabaapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wintop.ocr.entity.IDcard;
import com.wintop.ocr.util.HttpUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class IDcardTest {

    public static void main(String[] args) {

        IDcard id = IDcard.parseIdCardFace("url");
        if(id!=null)
            System.out.println(id.getName()+" "+id.getNum()+" "+id.getSex()+" "+id.getAddress()+" "+id.getNationality()+" "+id.getBirth());
    }
}