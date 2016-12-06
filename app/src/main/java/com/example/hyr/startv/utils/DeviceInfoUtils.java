package com.example.hyr.startv.utils;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import java.util.Date;

/**
 * Description:
 * 作者：hyr on 2016/11/9 14:11
 * 邮箱：2045446584@qq.com
 */
public class DeviceInfoUtils {

    public static String getSystemVersion() {

        return Build.VERSION.RELEASE;
    }


    public static String getCurrentSystemTimes() {
        Date date = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm");//24小时制
        String LgTime = sdformat.format(date);
        return LgTime;
    }


    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }


    public static String getDeviceInfo() {

        //"aid":"10201036","os":"Android","ov":"","rn":"480*800","dn":"LGL24","cr":"46000","as":"WIFI","uid":"dbcaa6c4482bc05ecb0bf39dabf207d2","clid":110025000
        String s = "{ \"aid\":\"10201036\",\"os\":\"Android\",\"ov\":\"" + getSystemVersion() +
            "\",\"rn\":\"480*800\",\"dn\":\"" + getPhoneModel() +
            "\",\"cr\":\"46000\",\"as\":\"WIFI\",\"uid\":\"dbcaa6c4482bc05ecb0bf39dabf207d2\",\"clid\":110025000}";

        return s;

    }
}
