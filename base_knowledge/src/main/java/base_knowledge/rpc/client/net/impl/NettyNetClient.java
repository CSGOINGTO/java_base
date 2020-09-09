package base_knowledge.rpc.client.net.impl;

import base_knowledge.rpc.client.net.NetClient;
import base_knowledge.rpc.discovery.ServiceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class NettyNetClient implements NetClient {
    @Override
    public byte[] sendRequest(byte[] data, ServiceInfo serviceInfo) {
        final String[] addInfoArray = serviceInfo.getAddress().split(":");
        SendHandler sendHandler = new SendHandler(data);
        byte[] respData = null;
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            final ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(sendHandler);
                        }
                    });
            bootstrap.connect(addInfoArray[0], Integer.parseInt(addInfoArray[1])).sync();
            respData = (byte[]) sendHandler.rspData();
            log.info("sendRequest get reply: {}", respData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        return new byte[0];
    }
}

class SendHandler extends ChannelInboundHandlerAdapter {

    private CountDownLatch countDownLatch;

    private Object readMessage;

    private byte[] data;

    public SendHandler(byte[] data) {
        this.data = data;
    }


    public Object rspData() {
        return null;
    }
}