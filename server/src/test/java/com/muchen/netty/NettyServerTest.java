package com.muchen.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServerTest {

    private int port;
    public static void main(String[] args) throws InterruptedException {
        new NettyServerTest(1234).start();
    }

    public NettyServerTest(int port){
        this.port = port;
    }

    private void start() throws InterruptedException {
        TestServerHandler handler = new TestServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap strap = new ServerBootstrap();
        strap.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(handler);
            }
        });
        try {
            ChannelFuture f = strap.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
