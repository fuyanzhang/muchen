package com.muchen.server.test;

import com.muchen.server.annotation.ExportService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

/**
 * Author:yanzhang.fu
 * Date:2018/7/6
 * Description:
 * Modified By：
 **/
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//        "classpath*:META-INF/spring/spring*.xml"})
public class TestAnnotationMain {

    @Test
    public void test1() throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/test-spring-server.xml");

        Map<String,Object> map = ctx.getBeansWithAnnotation(ExportService.class);
        System.out.println(map.size());
        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);

            System.out.println(v.getClass().getInterfaces().length);
        });
        System.in.read();
    }
}
