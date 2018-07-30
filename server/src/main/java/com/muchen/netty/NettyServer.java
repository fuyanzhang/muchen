package com.muchen.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Author:yanzhang.fu
 * Date:2018/7/30
 * Description: 启动netty服务。
 * Modified By：
 **/
@Service
public class NettyServer {

    @Value("${muchen.port:8800}")
    private int port;

    @Value("${muchen.bossThread:1}")
    private int bossThread;

    @Value("${muchen.wokerThread:5}")
    private int wokerThread;

    public void start() {
        ServerBootstrap b = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup(bossThread, new DefaultThreadFactory("NettyServerBoss", true));
        EventLoopGroup workerGroup = new NioEventLoopGroup(wokerThread, new DefaultThreadFactory("NettyServerWorker", true));

        b.group(bossGroup,workerGroup);
    }

}
