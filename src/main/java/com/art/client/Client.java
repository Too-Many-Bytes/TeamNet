package com.art.client;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        NetWork client = new NetWork();
        NetWork client2 = new NetWork();
        Thread.sleep(9000);
        client.sendMessage("Карен лох");
        //Thread.sleep(500);
        client2.sendMessage("Подтверждаю");
    }

}
