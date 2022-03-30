package com.art.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class NetWork {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private SocketChannel channel;
    private static final String HOST = "localhost";
    private static final int PORT = 8189;
    public NetWork(){
        new Thread(() ->{
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try{
                Bootstrap b = new Bootstrap();
                b.group(workerGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception{
                        channel = socketChannel;
                        socketChannel.pipeline().addLast(new StringDecoder(), new StringEncoder(), new SimpleChannelInboundHandler<String>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s){
                            System.out.println(s);
                        }

                        });

                    }
                });
                ChannelFuture future = b.connect(HOST,PORT).sync();
                while (true) {
                    if (in.ready()) {
                        String s = in.readLine();
                        if (s.equals("exit")) {
                            close();
                            break;
                        } else {
                            sendMessage(s);
                        }
                    }
                }
                future.channel().closeFuture().sync();
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
            }

        }).start();
    }
    public void sendMessage(String s){
        channel.writeAndFlush(s);
    }
    public void close(){
        channel.close();
    }
}
