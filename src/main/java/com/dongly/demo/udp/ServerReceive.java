package com.dongly.demo.udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class ServerReceive {

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket(10010)) {

            int length = 1024;
            byte[] buf;
            DatagramPacket p;

            while (true) {
                buf = new byte[length];
                p = new DatagramPacket(buf, length);
                socket.receive(p);
                String msg = new String(buf, 0, p.getLength(), StandardCharsets.UTF_8);

                System.out.println("from :" + p.getAddress().getHostAddress() + " data is : " + msg);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
