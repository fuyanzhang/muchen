package com.muchen.common.zk;

import com.muchen.common.domain.RegistryInfo;
import com.muchen.common.domain.ServiceInfo;
import com.muchen.common.register.RegistryService;
import com.muchen.common.serializable.ServiceInfoEncode;
import com.muchen.common.utils.LocalhostIpFetcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

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

    public void register(ServiceInfo serviceInfo) throws Exception {
        log.info("start to register service {}", serviceInfo);
        String tmpPath = ServiceInfoEncode.encode(serviceInfo);

        String path = "/" + serviceInfo.getServiceName() + "/provider" + "/" + LocalhostIpFetcher.fetchLocalIP() + "/" + tmpPath;

        Boolean isExist = !(client.checkExists().forPath(path) == null);
        if (isExist) {
            if (serviceInfo.getIsOverwrite()) {
                client.delete().forPath(path);
                client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
            }
        } else {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        }
        log.info("register service success. the path is {}", path);

    }

    public void unRegister(ServiceInfo serviceInfo) {

    }

    public List<ServiceInfo> lookup(ServiceInfo serviceInfo) throws Exception {
        log.info("lookup service...,serviceInfo is {}", serviceInfo);

        String serviceRootPath = "/" + serviceInfo.getServiceName() + "/provider";
        if (!(client.checkExists().forPath(serviceRootPath) == null)) {
            List<String> children = client.getChildren().forPath(serviceRootPath);

        } else {
            log.error("service {} not found.", serviceInfo);
        }

        return null;
    }

    private CuratorFramework getClient(RegistryInfo registryInfo) {

        log.info("start to create zk client...");
        String connectString = registryInfo.getUrl();
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
        builder.connectString(connectString);
        builder.retryPolicy(new ExponentialBackoffRetry(1000, registryInfo.getRetryTime()));
        builder.namespace(registryInfo.getNameSpace());
        builder.sessionTimeoutMs(registryInfo.getSessionTimeOut());
        client = builder.build();
        client.start();
        log.info("success to create zk client...");
        return client;
    }


}
