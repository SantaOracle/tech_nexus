package org.tech.accumulation.demo.java.io.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO形式，实现TCP连接
 * 服务端给客户端发送信息
 * @author peiheng.jiang create on 2019/9/8
 */
@Slf4j
public class BioSocket {

    public static final int PORT = 4343;

    public static void demo() {
        // init server
        Thread serverThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                while (true) {
                    // wait for connect
                    Socket socket = serverSocket.accept();
                    Thread serverHandleThread = new Thread(() -> {
                        try {
                            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                            printWriter.println("Hello world!");
                            printWriter.flush();
                        } catch (Exception e) {
                            log.error("Server handle thread exception, e:", e);
                        }
                    });
                    serverHandleThread.start();
                }
            } catch (Exception e) {
                log.error("Server thread exception, e:", e);
            }
        });
        serverThread.start();

        // init client
        try {
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), PORT);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            bufferedReader.lines().forEach(s -> log.info("Client accept info:{}", s));
        } catch (IOException e) {
            log.error("Client accept info error, e:", e);
        }
    }

    public static void main(String[] args) {
        demo();
    }
}
