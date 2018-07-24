package com.muchen.common;


import com.muchen.common.domain.ServiceInfo;
import com.muchen.common.serializable.ServiceInfoEncode;
import com.muchen.common.utils.LocalhostIpFetcher;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Author:yanzhang.fu
 * Date:2018/7/11
 * Description:
 * Modified By：
 **/
public class TestEncodeUtils {

    @Test
    public void testEncode() throws UnsupportedEncodingException {
        ServiceInfo info = new ServiceInfo();
        info.setServiceName("serviceName");
        info.setGroup("group");
        info.setIsRateLimit(true);
        info.setRegistryInfo(null);
        String path = ServiceInfoEncode.encode(info);
        String expectedPath=String.format("serviceName/provider/%s/group/ratelimit=true", LocalhostIpFetcher.fetchLocalIP());
        Assert.assertEquals(expectedPath,URLDecoder.decode(path,"UTF-8"));

        System.out.println(ServiceInfoEncode.decode(path));
    }
}
