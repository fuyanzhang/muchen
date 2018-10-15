package com.muchen.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Author:yanzhang.fu
 * Date:2018/7/26
 * Description:
 * Modified By：
 **/
public class NettyClient {

    private String host;
    private int port;

    public NettyClient(String host,int port){
        this.host = host;
        this.port = port;
    }

    private void start() throws InterruptedException {
        TestClientHandler handler = new TestClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(port)).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(handler);
            }
        });
        ChannelFuture f = b.connect().sync();
        f.channel().closeFuture().sync();
        group.shutdownGracefully();

    }
    public static void main(String[] args) throws InterruptedException {
        new NettyClient("10.234.203.111",1234).start();
    }
}
