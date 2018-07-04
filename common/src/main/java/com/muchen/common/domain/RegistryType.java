package com.muchen.common.domain;

import java.util.stream.Stream;

/**
 * Author:yanzhang.fu
 * Date:2018/7/4
 * Description: 使用的注册中心类型
 * Modified By：
 **/
public enum RegistryType {

    ZOOKEEPER("zookeeper"), ETCD("etcd"), CONSUL("consul"), EUREKA("eureka");
    private String name;

    RegistryType(String name) {
        this.name = name;
    }

    /**
     * 通过name查找枚举
     */
    public static RegistryType getRegistryType(String name) {
        for (RegistryType v : RegistryType.values()) {
            if (v.name.equals(name)) {
                return v;
            } else {
                continue;
            }
        }
        return RegistryType.ZOOKEEPER;
    }
}
