package com.muchen.main;

import com.muchen.common.domain.RegistryInfo;
import com.muchen.common.domain.ServiceInfo;
import com.muchen.common.register.RegistryFactory;
import com.muchen.common.register.RegistryService;
import com.muchen.server.annotation.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 框架的引导器。
 */

/**
 * 服务端注册服务，通过注解方式来声明服务。
 * 在zk上注册服务名及bean。还有服务的提供者，
 * 用于服务的路由与熔断。当前服务路由才用轮询
 * 的方式，如果有节点提供的服务连续3次不可用，则
 * 熔断。
 */
@Slf4j
@Service
public class BootStrap implements ApplicationContextAware, InitializingBean {

    private ApplicationContext ctx;

    @Value("${registry.connectString:127.0.0.1:2181}")
    private String connectString;

    @Value("${registry.type:zookeeper}")
    private String registryType;

    @Value("${registry.namespace:muchen}")
    private String nameSpace;

    @Value("${registry.sessiontimeout:3000}")
    private int sessionTimeOut;

    @Value("${registry.retrytimes:3}")
    private int retryTime;

    private void init() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //获取所有服务
        Map<String, Object> services = ctx.getBeansWithAnnotation(ExportService.class);
        //根据配置组装注册中心中心
        RegistryService registryService = RegistryFactory.REGISTRY_FACTORY.createRegistry(buildRegistry());
        exportService(services, registryService);
        //启动服务端口。
        startService();
    }

    private RegistryInfo buildRegistry() {
        return new RegistryInfo.RegistryBuilder(connectString).nameSpace(nameSpace).retryTimes(retryTime).type(registryType).sessionTimeOut(sessionTimeOut).build();
    }

    private void exportService(Map<String, Object> services, RegistryService registryService) throws Exception {

        if (MapUtils.isNotEmpty(services)) {
            services.forEach((k, v) -> {
                ExportService exportService = v.getClass().getAnnotation(ExportService.class);
                ServiceInfo info = buildExportService(exportService);
                try {
                    registryService.register(info);
                } catch (Exception e) {
                    log.error("register failed.",e);
                }
            });
        }
    }


    private ServiceInfo buildExportService(ExportService exportService) {
        ServiceInfo info = new ServiceInfo();
        info.setGroup(exportService.group());
        info.setIsRateLimit(exportService.rateLimiter());
        info.setServiceName(exportService.value());
        return info;
    }


    //netty
    private void startService(){

    }
}
