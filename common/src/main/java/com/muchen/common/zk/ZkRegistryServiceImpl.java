package com.muchen.common.zk;

import com.muchen.common.domain.RegistryInfo;
import com.muchen.common.domain.ServiceInfo;
import com.muchen.common.register.RegistryService;
import com.muchen.common.serializable.ServiceInfoEncode;
import com.muchen.common.utils.LocalhostIpFetcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
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
        if (checkPathExist(path)) {
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
        List<ServiceInfo> services = new ArrayList<ServiceInfo>();
        String serviceRootPath = "/" + serviceInfo.getServiceName() + "/provider";
        if (checkPathExist(serviceRootPath)) {
            List<String> children = client.getChildren().forPath(serviceRootPath);

            for (String ip : children) {
                String childPath = serviceRootPath + "/" + ip;
                List<String> strServices = client.getChildren().forPath(childPath);
                if (CollectionUtils.isEmpty(strServices)) {
                    continue;
                } else {
                    ServiceInfo service = ServiceInfoEncode.decode(strServices.get(0));
                    service.setIp(ip);
                    services.add(service);
                }

            }
        } else {
            log.error("service {} not found.", serviceInfo);
        }

        return services;
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


    private Boolean checkPathExist(String path) {
        try {
            Stat stat = client.checkExists().forPath(path);
            if (stat == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            log.error("check path exist faild. path is {}, exception is {}", path, e);
            return false;
        }
    }
}
