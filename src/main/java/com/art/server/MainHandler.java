package com.art.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;

public class MainHandler extends SimpleChannelInboundHandler<String> {
    private static final ArrayList<Channel> channels= new ArrayList<>();
    private String clientName;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Чёрт подключился");
        channels.add(ctx.channel());
        clientName = "Клиент №"+channels.size();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Чёрт отключился");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if (msg.equals("Chats")){
            ArrayList<String> users = getAllChats();
            for (int i=0;i< users.size();i++){
                ctx.channel().writeAndFlush(users.get(i));
                Thread.sleep(100);
            }
        }
        else if (msg.equals("msg")){
            ArrayList<String> msg1 = getAllMsg();
            for (int i=0;i< msg1.size();i++){
                ctx.channel().writeAndFlush(msg1.get(i));
                Thread.sleep(100);
            }
        }
        else {
            System.out.println(msg);
            ctx.channel().writeAndFlush(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }



    public static ArrayList<String> getAllChats(){ // это нужно карену написать
        ArrayList<String> users = new ArrayList<>();
        users.add("Artem");
        users.add("Igor");
        users.add("Karen");
        return users;
    }


    public static ArrayList<String> getAllMsg(){ // это нужно карену написать
        ArrayList<String> msg = new ArrayList<>();
        msg.add("Привет");
        msg.add("Как дела?");
        return msg;

    }

}
