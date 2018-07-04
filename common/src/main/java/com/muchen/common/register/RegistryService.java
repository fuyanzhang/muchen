package com.muchen.common.register;

import com.muchen.common.domain.ServiceInfo;

import java.util.List;

/**
 * Author:yanzhang.fu
 * Date:2018/7/4
 * Description: 注册中心注册接口
 * Modified By：
 **/
public interface RegistryService {

    /**
     * 注册服务
     * @param serviceInfo
     */
    void register(ServiceInfo serviceInfo);

    /**
     * 下线服务
     * @param serviceInfo
     */
    void unRegister(ServiceInfo serviceInfo);

    /**
     * 查找服务
     * @param serviceInfo
     * @return
     */
    List<ServiceInfo> lookup(ServiceInfo serviceInfo);
}
