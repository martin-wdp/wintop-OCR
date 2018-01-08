package com.wintop.insurance.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class IDcardParser {

    /*
     * 获取参数的json对象
     */
    private static JSONObject getParam(int type, JSONObject dataValue) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("dataType", type);
            obj.put("dataValue", dataValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    /*
     * 获取参数的json对象
     */
    private static JSONObject getParam(int type, String dataValue) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("dataType", type);
            obj.put("dataValue", dataValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {

        IDcard id = parseIdCardFace("http://img.yuntongauto.com/M00/00/BF/wKgWgVoKYomAZjt_AAJgfi3Aljg225.png");
        if(id!=null)
            System.out.println(id.getName()+" "+id.getNum()+" "+id.getSex()+" "+id.getAddress()+" "+id.getNationality()+" "+id.getBirth());
    }
    /**
     *
     * @param faceImgUrl 正面图片位地址
     * @return
     */
    public static IDcard parseIdCardFace(String faceImgUrl){
        IDcard result = null;
        String host = "https://dm-51.data.aliyun.com";
        String path = "/rest/160601/ocr/ocr_idcard.json";
        String method = "POST";
        String appcode = "7f18f9b3524b414cb7d47fca8637f571";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        // 对图像进行base64编码

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            URL imgUrl = new URL(faceImgUrl/*"http://img.yuntongauto.com/M00/00/24/wKgWgVjYnPeAaoTpAANCN2FviiI786.png"*/);
            URLConnection conn = imgUrl.openConnection();

            DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
            String imageName = Calendar.getInstance().getTimeInMillis() + ".jpg";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            dataInputStream.close();
            fileOutputStream.close();

            FileInputStream in =  new FileInputStream(new File(imageName));
            while(in.read(buffer) != -1){
                out.write(buffer);
            }in.close();

            String faceImgBase64 =  new String(Base64.encodeBase64(out.toByteArray()));
            // 拼装请求body的json字符串
            JSONObject requestObj = new JSONObject();
            JSONArray inputArray = new JSONArray();

            JSONObject faceObj = new JSONObject();
            JSONObject configObj = new JSONObject();
            configObj.put("side", "face");
            faceObj.put("image", getParam(50, faceImgBase64));
            faceObj.put("configure", getParam(50, configObj.toString()));
            inputArray.add(faceObj);

            requestObj.put("inputs", inputArray);

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, requestObj.toJSONString());
            String resBody = EntityUtils.toString(response.getEntity());
            System.out.println(response+" ");
            System.out.println(response.getHeaders("X-Ca-ErrorMessage"));
            System.out.println(resBody);
            //获取response的body
            if(response.getStatusLine().getStatusCode()==200){
                JSONObject resObj = JSONObject.parseObject(resBody);
                if(resObj!=null){
                    JSONArray outputs = resObj.getJSONArray("outputs");

                    JSONObject item =  outputs.getJSONObject(0);
                    String dataValueFirst = item.getJSONObject("outputValue").getString("dataValue");
                    result = JSONObject.parseObject(dataValueFirst,IDcard.class);
                }

            }else{
                result = null;
            }
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @param backImgUrl 反面图片位地址
     * @return
     */
    public static IDcard parseIdCardBack(String backImgUrl){
        IDcard result = null;
        String host = "https://dm-51.data.aliyun.com";
        String path = "/rest/160601/ocr/ocr_idcard.json";
        String method = "POST";
        String appcode = "7f18f9b3524b414cb7d47fca8637f571";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        // 对图像进行base64编码

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            URL imgUrl = new URL(backImgUrl/*"http://img.yuntongauto.com/M00/00/24/wKgWgVjYnPeAaoTpAANCN2FviiI786.png"*/);
            URLConnection conn = imgUrl.openConnection();
            DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
            String imageName = Calendar.getInstance().getTimeInMillis() + ".jpg";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            dataInputStream.close();
            fileOutputStream.close();

            FileInputStream in =  new FileInputStream(new File(imageName));
            while(in.read(buffer) != -1){
                out.write(buffer);
            }in.close();

            String backImgBase64 =  new String(Base64.encodeBase64(out.toByteArray()));


            // 拼装请求body的json字符串
            JSONObject requestObj = new JSONObject();
            JSONArray inputArray = new JSONArray();

            JSONObject backObj = new JSONObject();
            JSONObject configObj = new JSONObject();
            configObj.put("side", "back");
            backObj.put("image", getParam(50, backImgBase64));
            backObj.put("configure", getParam(50, configObj.toString()));
            inputArray.add(backObj);

            requestObj.put("inputs", inputArray);

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, requestObj.toJSONString());
            System.out.println(response);
            //获取response的body
            if(response.getStatusLine().getStatusCode()==200){
                JSONObject resObj = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
                if(resObj!=null){
                    JSONArray outputs = resObj.getJSONArray("outputs");

                    JSONObject item =  outputs.getJSONObject(0);
                    String dataValueFirst = item.getJSONObject("outputValue").getString("dataValue");
                    result = JSONObject.parseObject(dataValueFirst,IDcard.class);
                }

            }else{
                result = null;
            }
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }

        return result;
    }
}