package com.muchen.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Author:yanzhang.fu
 * Date:2018/7/26
 * Description:
 * Modified By：
 **/
@ChannelHandler.Sharable
public class TestClientHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8));
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client receive msg"+msg);
    }
}
