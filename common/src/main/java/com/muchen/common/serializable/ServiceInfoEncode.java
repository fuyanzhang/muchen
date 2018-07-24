package com.muchen.common.serializable;

import com.muchen.common.domain.ServiceInfo;
import com.muchen.common.utils.LocalhostIpFetcher;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Author:yanzhang.fu
 * Date:2018/7/4
 * Description: 服务的序列化和反序列化
 * Modified By：
 **/
public class ServiceInfoEncode {

    public static String encode(ServiceInfo serviceInfo) throws UnsupportedEncodingException {
        String last = "";
        if (null == serviceInfo.getIsRateLimit()) {
            last = String.join(",", "ratelimit=false");
        } else {
            last = String.join(",", "ratelimit=" + serviceInfo.getIsRateLimit());
        }
        if (StringUtils.isEmpty(serviceInfo.getGroup())) {
            serviceInfo.setGroup("default");
        }
        String path = String.join("/", serviceInfo.getServiceName(), "provider", LocalhostIpFetcher.fetchLocalIP(), serviceInfo.getGroup(), last);
        String encodePath = URLEncoder.encode(path, "UTF-8");
        return encodePath;
    }

    public static ServiceInfo decode(String str) throws UnsupportedEncodingException {

        String path = URLDecoder.decode(str, "UTF-8");
        String arr[] = path.split("/");
        ServiceInfo info = new ServiceInfo();
        info.setServiceName(arr[0]);
        info.setGroup(arr[3]);
        info.setIsRateLimit(new Boolean(arr[4].split("=")[1]));
        return info;
    }

}
