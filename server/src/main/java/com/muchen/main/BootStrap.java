package com.muchen.main;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;

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
public class BootStrap {

    @Autowired
    private CuratorFramework client;
    private void init(){
        //服务注册。


    }
}
