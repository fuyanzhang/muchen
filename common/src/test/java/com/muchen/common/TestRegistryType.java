package com.muchen.common;

import com.muchen.common.domain.RegistryType;

/**
 * Author:yanzhang.fu
 * Date:2018/7/4
 * Description:
 * Modified By：
 **/
public class TestRegistryType {

    public static void main(String[] args) {
        RegistryType type = RegistryType.valueOf("ZOOKEEPER");
        System.out.println(type.name());
        RegistryType xx= RegistryType.getRegistryType("consul");
        System.out.println(xx.name());

    }
}
