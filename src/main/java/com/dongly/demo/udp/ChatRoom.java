package com.dongly.demo.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class ChatRoom {

    public static void main(String[] args) {

        Thread send = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 DatagramSocket sendSocket = new DatagramSocket()) {

                String line;
                while ((line = reader.readLine()) != null) {
                    if ("exit".equals(line)) {
                        break;
                    }
                    byte[] buf = line.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket packet = new DatagramPacket(buf, buf.length,
                            InetAddress.getByName("192.168.31.110"), 10010);
                    sendSocket.send(packet);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        send.start();

        Thread receive = new Thread(() -> {
            try (DatagramSocket receiveSocket = new DatagramSocket(10010)) {
                while (true) {

                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    receiveSocket.receive(packet);

                    String msg = new java.lang.String(buf, 0, packet.getLength(), StandardCharsets.UTF_8);

                    System.out.println("from :" + packet.getAddress().getHostAddress() + " data is : " + msg);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        receive.start();
    }


}
