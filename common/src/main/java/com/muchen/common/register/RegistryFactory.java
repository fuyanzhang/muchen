package com.muchen.common.register;

import com.muchen.common.domain.RegistryInfo;
import com.muchen.common.zk.ZkRegistryServiceImpl;

/**
 * Author:yanzhang.fu
 * Date:2018/7/4
 * Description: 注册中心工厂类,单例模式
 * Modified By：
 **/
public enum RegistryFactory {

    REGISTRY_FACTORY;

    /**
     * 暂时先使用zk的方式
     *
     * @param registryInfo
     * @return
     */
    public RegistryService createRegistry(RegistryInfo registryInfo) {

        RegistryService registryService = null;
        switch (registryInfo.getType()) {
            case ZOOKEEPER:
                registryService = new ZkRegistryServiceImpl(registryInfo);
                break;
            case ETCD:
            case CONSUL:
            case EUREKA:
                break;

        }
        return registryService;
    }
}
