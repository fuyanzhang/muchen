package com.muchen.server.test;

import com.muchen.common.domain.RegistryInfo;
import com.muchen.common.domain.ServiceInfo;
import com.muchen.common.register.RegistryFactory;
import com.muchen.common.register.RegistryService;
import org.junit.Test;

/**
 * Author:yanzhang.fu
 * Date:2018/7/23
 * Description:
 * Modified By：
 **/
public class TestLookUp {

    @Test
    public void test() throws Exception {

        RegistryService registryService = RegistryFactory.REGISTRY_FACTORY.createRegistry(buildRegistry());
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setServiceName("test1");
        registryService.lookup(serviceInfo);
    }


    private RegistryInfo buildRegistry() {
        return new RegistryInfo.RegistryBuilder("127.0.0.1:2181").nameSpace("muchen").retryTimes(3).type("zookeeper").sessionTimeOut(5000).build();
    }
}
