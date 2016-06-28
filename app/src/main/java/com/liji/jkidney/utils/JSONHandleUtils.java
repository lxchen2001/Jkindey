package com.liji.jkidney.utils;

import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.liji.jkidney.model.M_Common;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @2Do:
 * @Author M2_
 * @Version v 1.0
 * @Date [2015/10/19]
 */
public class JSONHandleUtils {

    public static <T> T parseResponse(String jsonString, Class<T> cls) throws JSONException {
        M_Common baseData = JSONUtils.deserialize(jsonString, M_Common.class);
        if (baseData.getCode() != 200) {
            //后台有异常，当未做提示；测试阶段可提示数据异常，正式环境不做任何处理
            throw new JSONException(TextUtils.isEmpty(baseData.getMsg()) ? "数据异常" : baseData.getMsg());
        }
        return JSONUtils.deserialize(baseData.getNewslist(), cls);
    }

    public static M_Common parseResponse(String jsonString) throws JSONException {
        M_Common baseData = JSONUtils.deserialize(jsonString, M_Common.class);
        if (baseData.getCode() != 200) {
            //后台有异常，当未做提示；测试阶段可提示数据异常，正式环境不做任何处理
            throw new JSONException(TextUtils.isEmpty(baseData.getMsg()) ? "数据异常" : baseData.getMsg());
        }
        return baseData;
    }

    public static <T> List<T> parseResponseArray(String jsonString, Class<T> cls) throws JSONException {
        M_Common baseData = JSONUtils.deserialize(jsonString, M_Common.class);
        if (baseData.getCode() != 200) {
            throw new JSONException(baseData.getMsg());
        }
        return getObjectList(baseData.getNewslist(), cls);
    }


    public static <T> List<T> getObjectList(String jsonString, Class<T> cls) {
        return JSON.parseArray(jsonString, cls);
    }

}
