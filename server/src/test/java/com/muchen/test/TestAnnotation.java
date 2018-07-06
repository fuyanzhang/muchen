package com.muchen.test;

import com.muchen.server.annotation.ExportService;

/**
 * Author:yanzhang.fu
 * Date:2018/7/5
 * Description:
 * Modified By：
 **/
@ExportService("test2")
public class TestAnnotation {
    public void test(){
        System.out.println("hello world...");
    }
}
