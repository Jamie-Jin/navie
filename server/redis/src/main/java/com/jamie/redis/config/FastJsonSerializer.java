package com.jamie.redis.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * 使用FastJson对Redis的键值对进行序列化和反序列化
 * @param <T>
 */
public class FastJsonSerializer<T> implements RedisSerializer<T> {
    // utf-8编码方式
    public static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

    // Value值的类型
    private Class<T> clazz;

    public FastJsonSerializer(Class<T> clazz){
        super();
        this.clazz = clazz;
    }

    /**
     * 序列化方法
     * @param t
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t){
            return new byte[0];
        }
        // 使用FastJson的JSON方法, 将对象转换为二进制数，使用UTF-8编码
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    /**
     * 反序列化方法
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0){
            return null;
        }
        // 先将二进制数转换为字符串
        String str = new String(bytes, DEFAULT_CHARSET);

        // 将得到的JSON字符串转换为对象
        return JSON.parseObject(str, clazz);
    }
}
