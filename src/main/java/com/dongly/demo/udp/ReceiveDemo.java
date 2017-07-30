package com.dongly.demo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 * UDP协议接收数据:
 * A: 创建接收端Socket对象
 * B: 创建一个数据包（接收容器）
 * C: 调用Socket对象的接收方法接收数据包
 * D: 解析数据包
 * E: 释放资源
 */
public class ReceiveDemo {

    public static void main(String[] args) {

        // A: 创建接收端Socket对象
        try (DatagramSocket socket = new DatagramSocket(10010)) {

            // 创建一个数据包（接收容器）
            byte[] buf = new byte[1024];
            int length = buf.length;
            DatagramPacket p = new DatagramPacket(buf, length);

            // 调用Socket对象的接收方法接收数据包
            // 阻塞
            socket.receive(p);

            String address = p.getAddress().getHostAddress();
            String s = new String(p.getData(), 0, p.getLength(), StandardCharsets.UTF_8);

            System.out.println(address + " 发送数据: " + s);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
