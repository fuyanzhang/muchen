package com.muchen.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:yanzhang.fu
 * Date:2018/7/4
 * Description:
 * Modified By：
 **/
@Data
public class ServiceInfo implements Serializable {

    private static final long serialVersionUID = 5167628152714102019L;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务分组
     */
    private String group;

    /**
     * 是否限流
     */
    private Boolean isRateLimit;

    /**
     * 服务的注册中心
     */
    private RegistryInfo registryInfo;

    /**
     *  是否需要更新，默认false
     */
    private Boolean isOverwrite = false;


}
