package com.dongly.demo.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * TCP协议发送数据
 */
public class ClientDemo {

    public static void main(String[] args) {

        // 创建发送端的Socket对象
        try (Socket socket = new Socket("192.168.31.110", 10010);
             // 获取输出流写数据
             OutputStream outputStream = socket.getOutputStream()) {
            outputStream.write("发送TCP数据".getBytes(StandardCharsets.UTF_8));

            InputStream inputStream = socket.getInputStream();
            byte[] buf = new byte[1024];
            int read = inputStream.read(buf);
            String msg = new String(buf, 0, read, StandardCharsets.UTF_8);

            System.out.println("Client : " + msg);

            outputStream.close();
            inputStream.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
