package com.liji.jkidney.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.liji.jkidney.model.info.M_Common;
import com.liji.jkidney.model.info.M_HealthyInfoShow;
import com.liji.jkidney.model.info.M_HealthyKnowledgeShow;
import com.liji.jkidney.model.info.M_J_HealthInfoClassicfy;
import com.liji.jkidney.model.post.MAddress;

import org.json.JSONArray;
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


    public static <T> List<T> parseResponseArray(String jsonString, Class<T> cls, int type) throws JSONException {
        if (type == 0) {
            M_Common baseData = JSONUtils.deserialize(jsonString, M_Common.class);
            if (baseData.getCode() != 200) {
                throw new JSONException(baseData.getMsg());
            }
            return getObjectList(baseData.getNewslist(), cls);
        } else if (type == 1) {//健康资讯总类
            M_J_HealthInfoClassicfy classicfy = JSONUtils.deserialize(jsonString, M_J_HealthInfoClassicfy.class);
            if (classicfy.isStatus()) {
                return getObjectList(classicfy.getTngou(), cls);
            }
        } else if (type == 2) {//健康知识总类
            M_J_HealthInfoClassicfy classicfy = JSONUtils.deserialize(jsonString, M_J_HealthInfoClassicfy.class);
            if (classicfy.isStatus()) {
                return getObjectList(classicfy.getTngou(), cls);
            }
        } else if (type == 3) {

        }
        return null;
    }

    /**
     * 获取健康咨询详情
     *
     * @param jsonString
     * @return
     * @throws JSONException
     */
    public static String getInfoDetailUrl(String jsonString) throws JSONException {
        M_HealthyInfoShow baseData = JSONUtils.deserialize(jsonString, M_HealthyInfoShow.class);
        if (!baseData.isStatus()) {
            //后台有异常，当未做提示；测试阶段可提示数据异常，正式环境不做任何处理
            throw new JSONException("数据异常");
        }
        return baseData.getUrl();
    }

    /**
     * 获取健康知识详情
     *
     * @param jsonString
     * @return
     * @throws JSONException
     */
    public static String getKnowledgeDetailUrl(String jsonString) throws JSONException {
        M_HealthyKnowledgeShow baseData = JSONUtils.deserialize(jsonString, M_HealthyKnowledgeShow.class);
        if (!baseData.isStatus()) {
            //后台有异常，当未做提示；测试阶段可提示数据异常，正式环境不做任何处理
            throw new JSONException("数据异常");
        }
        return baseData.getUrl();
    }


    public static <T> List<T> getObjectList(String jsonString, Class<T> cls) {
        return JSON.parseArray(jsonString, cls);
    }


    /**
     * 解析附近的地址信息
     *
     * @param jsonString
     * @return
     */
    public static List<MAddress> getAddressList(String jsonString) {
        List<MAddress> addressList = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray array = new JSONArray(object.getString("data"));
            for (int i = 0; i < array.length(); i++) {
                JSONObject result = array.getJSONObject(i);
                MAddress address = new MAddress();
                address.setTitle(result.getString("title"));
                address.setAddress(result.getString("address"));
                address.setId(result.getString("id"));
                address.set_distance(Double.parseDouble(result.getString("_distance")));
                address.setCategory(result.getString("category"));
                addressList.add(address);
            }
        } catch (JSONException e) {
            JLogUtils.E(e);
        }

        return addressList;
    }

}
