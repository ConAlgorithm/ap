/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import catfish.base.DescriptionParser;
import catfish.base.Logger;
import catfish.base.business.common.CatfishEnum;

/**
 * 枚举属性扩展
 * @author baowzh
 *
 */
public class EnumUtil {

    private static ConcurrentMap<String, String> enumNameMap = new ConcurrentHashMap<>();
    private static ConcurrentMap<String, String> enumTextMap = new ConcurrentHashMap<>();

    /**
     * 获取枚举名称
     * @param clazz
     * @param value
     * @return
     */
    public static <T extends CatfishEnum<?>> String getName(Class<T> clazz, Integer value) {
        return get(clazz, value, new Callback() {

            @Override
            public void set(String key, CatfishEnum<?> enumObj) {
                enumNameMap.putIfAbsent(key, enumObj.toString());
            }

            @Override
            public String get(String key) {
                if (enumNameMap.containsKey(key)) {
                    return enumNameMap.get(key);
                }
                return null;
            }
        });
    }

    /**
     * 获取枚举描述
     * @param clazz
     * @param value
     * @return
     */
    public static <T extends CatfishEnum<?>> String getText(Class<T> clazz, Integer value) {
        return get(clazz, value, new Callback() {

            @Override
            public void set(String key, CatfishEnum<?> enumObj) {
                enumTextMap.putIfAbsent(key, DescriptionParser.getDescription(enumObj));
            }

            @Override
            public String get(String key) {
                if (enumTextMap.containsKey(key)) {
                    return enumTextMap.get(key);
                }
                return null;
            }
        });
    }

    /**
     * 获取枚举属性
     * @param clazz
     * @param value
     * @param callback
     * @return
     */
    private static <T extends CatfishEnum<?>> String get(Class<T> clazz, Integer value,
                                                         Callback callback) {
        if (clazz == null) {
            Logger.get().warn("argument clazz is null.");
            return null;
        }

        if (value == null) {
            return null;
        }

        String key1 = clazz.getName();
        String key2 = value.toString();
        String key = key1 + "." + key2;

        // 判断枚举是否已缓存
        String result = callback.get(key);
        if (result != null) {
            return result;
        }

        // 未缓存枚举加入至缓存
        CatfishEnum<?>[] enumArray = (CatfishEnum<?>[]) clazz.getEnumConstants();
        String tmpKey;
        for (CatfishEnum<?> enumObj : enumArray) {
            key2 = enumObj.getValue().toString();
            tmpKey = key1 + "." + key2;
            callback.set(tmpKey, enumObj);
        }

        return callback.get(key);
    }

    /**
     * 回调方法
     * @author baowzh
     *
     */
    interface Callback {
        String get(String key);

        void set(String key, CatfishEnum<?> enumObj);
    }
}
