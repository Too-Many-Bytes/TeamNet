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
        if (msg.equals())
        System.out.println(msg);
        ctx.channel().writeAndFlush(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
    public static ArrayList<String> getMsgfromDB1(){
        ArrayList<String> ans = new ArrayList<>();
        return ans;
    }
    public static ArrayList<String> getMsgfromDB2(){
        ArrayList<String> ans = new ArrayList<>();
        return ans;
    }




    public static ArrayList<ArrayList<String>> getAllMsg(int id_from,int id_to){
        ArrayList<String> msg1 = new ArrayList<>();
        ArrayList<String> msg2 = new ArrayList<>();

    }
    public void allMsg(){
        ArrayList<String> messages = new ArrayList<>();
        // в этот массив добавить все нужные строки таблицы

    }



}
