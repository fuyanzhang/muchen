package com.muchen.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Author:yanzhang.fu
 * Date:2018/7/26
 * Description:
 * Modified By：
 **/
@ChannelHandler.Sharable
public class TestServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        ByteBuf bb = (ByteBuf) msg;
        System.out.println(bb.toString(CharsetUtil.UTF_8));
//        ctx.fireChannelRead(msg);
    }
}
