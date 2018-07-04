package com.muchen.common.zk;

import com.muchen.common.domain.RegistryInfo;
import com.muchen.common.domain.ServiceInfo;
import com.muchen.common.register.RegistryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * Author:yanzhang.fu
 * Date:2018/7/4
 * Description:
 * Modified By：
 **/
@Slf4j
public class ZkRegistryServiceImpl implements RegistryService {

    private CuratorFramework client;

    public ZkRegistryServiceImpl(RegistryInfo registryInfo) {
        getClient(registryInfo);
    }

    public void register(ServiceInfo serviceInfo) {

    }

    public void unRegister(ServiceInfo serviceInfo) {

    }

    public List<ServiceInfo> lookup(ServiceInfo serviceInfo) {
        return null;
    }

    private CuratorFramework getClient(RegistryInfo registryInfo) {

        log.info("start to create zk client...");
        String connectString = registryInfo.getUrl();
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
        builder.connectString(connectString);
        builder.retryPolicy(new ExponentialBackoffRetry(1000, 3));
        builder.namespace("/muchen");
        client = builder.build();
        client.start();
        log.info("success to create zk client...");
        return client;
    }
}
