package com.dongly.demo.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ClientSend {

    public static void main(String[] args) {


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             DatagramSocket socket = new DatagramSocket()) {

            String line;
            byte[] buf;
            DatagramPacket packet;
            while ((line = reader.readLine()) != null) {


                buf = line.getBytes(StandardCharsets.UTF_8);
                packet = new DatagramPacket(buf, buf.length,
                        InetAddress.getByName("192.168.31.110"), 10010);

                socket.send(packet);
                if ("exit".equals(line)) break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
