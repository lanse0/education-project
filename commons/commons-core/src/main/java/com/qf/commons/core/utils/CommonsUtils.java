package com.qf.commons.core.utils;

/**
 * 通用工具包
 * author 14526
 * create_time 2023/3/7
 */
public class CommonsUtils {

    /**
     * 根据id 返回一个四位数的字符串 5 -> 0005
     *
     * @param id
     * @param count 返回字符串的位数
     * @return
     */
    public static String id2Str(Integer id, Integer count) {
        StringBuilder sid = new StringBuilder(id + "");
        while (sid.length() < count) {
            sid.insert(0, "0");
        }
        return sid.toString();
    }
}
