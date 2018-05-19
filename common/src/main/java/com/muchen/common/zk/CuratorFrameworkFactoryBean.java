package com.muchen.common.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class CuratorFrameworkFactoryBean implements FactoryBean , InitializingBean {
    private final static Logger logger = LoggerFactory.getLogger(CuratorFrameworkFactoryBean.class);

    private CuratorFramework client;

    @Value("${connectString:127.0.0.1:2181")
    private String connectString;

    private RetryPolicy retryPolicy;


    private Integer sessionTimeout;

    @Value("${namespace:muchen}")
    private String namespace;

    public Object getObject() throws Exception {
        return client;
    }

    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {

        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
        builder.connectString(connectString);
        if (retryPolicy == null){
            retryPolicy = new ExponentialBackoffRetry(1000,3);
        }
        builder.retryPolicy(retryPolicy);
        if (sessionTimeout!=null){
            builder.sessionTimeoutMs(sessionTimeout);
        }
        builder.namespace(namespace);
        client = builder.build();
        client.start();
        logger.info("start zk client success,connect to {}",connectString);
    }

    public void destory(){
        client.close();
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
