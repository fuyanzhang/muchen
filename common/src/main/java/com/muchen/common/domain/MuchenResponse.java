package com.muchen.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:yanzhang.fu
 * Date:2018/7/30
 * Description: 远程的响应消息体
 * Modified By：
 **/
@Data
public class MuchenResponse implements Serializable {

    private static final long serialVersionUID = -8568056777469981943L;


    /**
     * 消息id
     */
    private String messageId;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 返回数据
     */
    private Object result;


}
