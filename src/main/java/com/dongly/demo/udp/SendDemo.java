package com.dongly.demo.udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * UDP协议发送数据:
 * A: 创建发送端Socket对象
 * B: 创建数据，并把数据打包
 * C: 调用Socket的发送方法发送数据包
 * D: 释放资源
 */
public class SendDemo {

    public static void main(String[] args) {

        // A: 创建发送端Socket对象
        try (DatagramSocket socket = new DatagramSocket()) {

            // B: 创建数据，并把数据打包
            byte[] buf = "发送第一个消息".getBytes(StandardCharsets.UTF_8);
            int length = buf.length;
            InetAddress address = InetAddress.getByName("192.168.31.110");
            int port = 10010;
            DatagramPacket datagramPacket = new DatagramPacket(buf, length, address, port);

            //调用Socket的发送方法发送数据包
            socket.send(datagramPacket);

            // 释放资源 1.5新特性
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
