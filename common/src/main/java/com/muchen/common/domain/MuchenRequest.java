package com.muchen.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Author:yanzhang.fu
 * Date:2018/7/30
 * Description: 远程请求对象
 * Modified By：
 **/
@Data
public class MuchenRequest implements Serializable {
    private static final long serialVersionUID = 4867300966372514638L;

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 参数
     */
    private Map<String, Object> param;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 上下文
     */
    private Map<String, Object> context;

    /**
     * 调用的方法
     */
    private String method;

    /**
     * 扩展的属性及参数
     */
    private Map<String,Object> extParam;


}
