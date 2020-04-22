package com.neu.config;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统设置
 */
public class SystemConfig {
    // 前台相关配置
    public final static String Shopping_WX_INDEX_NEW = "shopping_wx_index_new";
    public final static String Shopping_WX_INDEX_HOT = "shopping_wx_index_hot";
    public final static String Shopping_WX_INDEX_BRAND = "shopping_wx_index_brand";
    public final static String Shopping_WX_INDEX_TOPIC = "shopping_wx_index_topic";
    public final static String Shopping_WX_INDEX_CATLOG_LIST = "shopping_wx_catlog_list";
    public final static String Shopping_WX_INDEX_CATLOG_GOODS = "shopping_wx_catlog_goods";
    public final static String Shopping_WX_SHARE = "shopping_wx_share";
    // 运费相关配置
    public final static String Shopping_EXPRESS_FREIGHT_VALUE = "shopping_express_freight_value";
    public final static String Shopping_EXPRESS_FREIGHT_MIN = "shopping_express_freight_min";
    // 订单相关配置
    public final static String Shopping_ORDER_UNPAID = "shopping_order_unpaid";
    public final static String Shopping_ORDER_UNCONFIRM = "shopping_order_unconfirm";
    public final static String Shopping_ORDER_COMMENT = "shopping_order_comment";
    // 商场相关配置
    public final static String Shopping_MALL_NAME = "shopping_mall_name";
    public final static String Shopping_MALL_ADDRESS = "shopping_mall_address";
    public final static String Shopping_MALL_PHONE = "shopping_mall_phone";
    public final static String Shopping_MALL_QQ = "shopping_mall_qq";

    //所有的配置均保存在该 HashMap 中
    private static Map<String, String> SYSTEM_CONFIGS = new HashMap<>();

    private static String getConfig(String keyName) {
        return SYSTEM_CONFIGS.get(keyName);
    }

    private static Integer getConfigInt(String keyName) {
        return Integer.parseInt(SYSTEM_CONFIGS.get(keyName));
    }

    private static Boolean getConfigBoolean(String keyName) {
        return Boolean.valueOf(SYSTEM_CONFIGS.get(keyName));
    }

    private static BigDecimal getConfigBigDec(String keyName) {
        return new BigDecimal(SYSTEM_CONFIGS.get(keyName));
    }

    public static Integer getNewLimit() {
        return getConfigInt(Shopping_WX_INDEX_NEW);
    }

    public static Integer getHotLimit() {
        return getConfigInt(Shopping_WX_INDEX_HOT);
    }

    public static Integer getBrandLimit() {
        return getConfigInt(Shopping_WX_INDEX_BRAND);
    }

    public static Integer getTopicLimit() {
        return getConfigInt(Shopping_WX_INDEX_TOPIC);
    }

    public static Integer getCatlogListLimit() {
        return getConfigInt(Shopping_WX_INDEX_CATLOG_LIST);
    }

    public static Integer getCatlogMoreLimit() {
        return getConfigInt(Shopping_WX_INDEX_CATLOG_GOODS);
    }

    public static boolean isAutoCreateShareImage() {
        return getConfigBoolean(Shopping_WX_SHARE);
    }

    public static BigDecimal getFreight() {
        return getConfigBigDec(Shopping_EXPRESS_FREIGHT_VALUE);
    }

    public static BigDecimal getFreightLimit() {
        return getConfigBigDec(Shopping_EXPRESS_FREIGHT_MIN);
    }

    public static Integer getOrderUnpaid() {
        return getConfigInt(Shopping_ORDER_UNPAID);
    }

    public static Integer getOrderUnconfirm() {
        return getConfigInt(Shopping_ORDER_UNCONFIRM);
    }

    public static Integer getOrderComment() {
        return getConfigInt(Shopping_ORDER_COMMENT);
    }

    public static String getMallName() {
        return getConfig(Shopping_MALL_NAME);
    }

    public static String getMallAddress() {
        return getConfig(Shopping_MALL_ADDRESS);
    }

    public static String getMallPhone() {
        return getConfig(Shopping_MALL_PHONE);
    }

    public static String getMallQQ() {
        return getConfig(Shopping_MALL_QQ);
    }

    public static void setConfigs(Map<String, String> configs) {
        SYSTEM_CONFIGS = configs;
    }

    public static void updateConfigs(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            SYSTEM_CONFIGS.put(entry.getKey(), entry.getValue());
        }
    }
}