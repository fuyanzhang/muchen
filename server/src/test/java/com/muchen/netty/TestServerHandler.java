package com.muchen.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
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
        ctx.write("i receive message ,send you ack");
        Channel c = ctx.channel();
        c.writeAndFlush("i receive message , send you ack");
//        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
//        ctx.fireChannelReadComplete();
    }
}
