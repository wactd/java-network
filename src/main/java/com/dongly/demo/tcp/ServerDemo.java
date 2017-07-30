package com.dongly.demo.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * TCP协议接收数据
 */
public class ServerDemo {

    public static void main(String[] args) {

        // 创建接收端Socket对象
        try (ServerSocket socket = new ServerSocket(10010)) {

            // 监听客户端连接, 返回对应的Socket对象
            Socket accept = socket.accept();

            // 获取输入流，得到数据
            InputStream inputStream = accept.getInputStream();
            byte[] b = new byte[1024];
            int read = inputStream.read(b);
            String msg = new String(b, 0, read);
            System.out.println("Server : " + accept.getInetAddress().getHostAddress() + " --- " + msg);
            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("我收到了".getBytes(StandardCharsets.UTF_8));

            outputStream.flush();

            accept.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
