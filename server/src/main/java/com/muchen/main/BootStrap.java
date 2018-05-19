package com.muchen.main;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 框架的引导器。
 */
public class BootStrap {

    @Autowired
    private CuratorFramework client;
    private void init(){
        //服务注册。

    }
}
