package com.liji.jkidney.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;
import java.util.Map;

public class JSONUtils {
    
    private static SerializeConfig mapping = new SerializeConfig();
    
    static {
        //序列化
        // 设定JSON转化日期的格式
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        
    }
    
    /**
     * 对象序列化成JSON字符串
     *
     * @param obj
     * @return
     */
    public static String serialize(Object obj) {
        // json字符串中使用单引号
        return JSON.toJSONString(obj, SerializerFeature.UseSingleQuotes);
    }
    
    /**
     * JSON字符串反序列化成对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T deserialize(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }
    
    public static Map<String, Object> deserialize(String jsonStr) {
        return deserialize(jsonStr, Map.class);
    }
    
}
