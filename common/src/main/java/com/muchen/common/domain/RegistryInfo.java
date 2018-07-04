package com.muchen.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:yanzhang.fu
 * Date:2018/7/4
 * Description: 注册中心信息
 * Modified By：
 **/
@Data
public class RegistryInfo implements Serializable {

    private static final long serialVersionUID = 5316143487565922684L;

    /**
     * 注册中心类型，可以是zk，可以是etcd，Eureka，Consul等。
     */
    private RegistryType type;

    /**
     * 注册中心ip ip:port,ip:port
     */
    private String url;

    /**
     * sessionTimeOut 时间，单位 ：毫秒
     */
    private int sessionTimeOut;

    /**
     * 服务注册的根路径空间
     */
    private String nameSpace;

    /**
     * 重试次数
     */
    private int retryTime;

    public static class RegistryBuilder {
        private RegistryType type;

        private String url;

        private int sessionTimeOut;

        private String nameSpace;

        private int retryTime;

        public RegistryBuilder(String url) {
            this.url = url;
        }

        public RegistryBuilder type(String type) {
            this.type = RegistryType.getRegistryType(type);
            return this;
        }

        public RegistryBuilder sessionTimeOut(int sessionTimeOut) {
            this.sessionTimeOut = sessionTimeOut;
            return this;
        }

        public RegistryBuilder nameSpace(String nameSpace) {
            this.nameSpace = nameSpace;
            return this;
        }

        public RegistryBuilder retryTimes(int retryTime) {
            this.retryTime = retryTime;
            return this;
        }

        public RegistryInfo build() {
            return new RegistryInfo(this);
        }

    }

    public RegistryInfo(RegistryBuilder builder) {
        this.type = builder.type;
        this.url = builder.url;
        this.nameSpace = builder.nameSpace;
        this.retryTime = builder.retryTime;
        this.sessionTimeOut = builder.sessionTimeOut;

    }

}
