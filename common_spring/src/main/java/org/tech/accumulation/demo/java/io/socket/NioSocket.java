package org.tech.accumulation.demo.java.io.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * NIO形式，实现TCP连接
 * 服务端给client端发请求
 * @author peiheng.jiang create on 2019/9/8
 */
@Slf4j
public class NioSocket {

    public static final int PORT = 4343;

    public static void demo() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 60L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        // server side runnable
        executor.execute(() -> {
            try {
                Selector selector = Selector.open();
                ServerSocketChannel channel = ServerSocketChannel.open();
                channel.bind(new InetSocketAddress(InetAddress.getLocalHost(), PORT));
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_ACCEPT);
                while (true) {
                    selector.select();  // wait for channel ready
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIter = selectionKeys.iterator();
                    while (keyIter.hasNext()) {
                        SelectionKey key = keyIter.next();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(Charset.defaultCharset().encode("Hello world!!!"));
                        keyIter.remove();
                    }
                }
            } catch (Exception e) {
                log.error("Server side exception, e:", e);
            }
        });

        // client side
        try {
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), PORT);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            bufferedReader.lines().forEach(s -> log.info("Client accept info:{}", s));
        } catch (Exception e) {
            log.error("Client side exception, e:", e);
        }
    }

    public static void main(String[] args) {
        demo();
    }
}
