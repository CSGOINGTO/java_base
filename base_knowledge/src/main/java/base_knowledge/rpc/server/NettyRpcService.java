package base_knowledge.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyRpcService extends RpcServer{

    private Channel channel;

    public NettyRpcService(int port, String protocol, RequestHandler requestHandler) {
        super(port, protocol, requestHandler);
    }

    @Override
    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            final ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ChannelRequestHandler());
                        }
                    });
            final ChannelFuture future = serverBootstrap.bind(port).sync();
            log.info("完成服务端端口绑定与启动！");
            channel = future.channel();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    @Override
    public void stop() {
        this.channel.close();
    }

    private class ChannelRequestHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("激活");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("服务端收到消息：{}", msg);
            final ByteBuf msgBuf = (ByteBuf) msg;
            byte[] req = new byte[msgBuf.readableBytes()];
            msgBuf.readBytes(req);
            final byte[] res = requestHandler.handleRequest(req);
            log.info("发送相应：{}", msg);
            final ByteBuf respBuf = Unpooled.buffer(res.length);
            respBuf.writeBytes(res);
            ctx.write(respBuf);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }



}
