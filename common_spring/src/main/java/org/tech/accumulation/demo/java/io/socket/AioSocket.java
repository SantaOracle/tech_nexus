package org.tech.accumulation.demo.java.io.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author peiheng.jiang create on 2019/9/8
 */
@Slf4j
public class AioSocket {

    public static final int PORT = 4343;

    public static void demo() {
        Thread serverThread = new Thread(() -> {
            AsynchronousChannelGroup group = null;
            try {
                group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
                AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group)
                        .bind(new InetSocketAddress(InetAddress.getLocalHost(), PORT));
                server.accept(null, new CompletionHandler<AsynchronousSocketChannel, AsynchronousSocketChannel>() {

                    @Override
                    public void completed(AsynchronousSocketChannel result, AsynchronousSocketChannel attachment) {
                        try {
                            Future<Integer> f = result.write(Charset.defaultCharset().encode("Hello world!!!!!"));
                            f.get();
                            String nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
                            log.info("Server send time:{}", nowTime);
                        } catch (Exception e) {
                            log.error("Server side completion thread error, e:", e);
                        }
                    }

                    @Override
                    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
                        log.error("Server side send failed, e:", exc);
                    }
                });
                group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            } catch (Exception e) {
                log.error("Server exception, e:", e);
            }
        });
        serverThread.start();

        // client side
        try {
            AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
            Future<Void> future = client.connect(new InetSocketAddress(InetAddress.getLocalHost(), PORT));
            future.get();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            client.read(buffer, null, new CompletionHandler<Integer, Void>() {

                @Override
                public void completed(Integer result, Void attachment) {
                    log.info("Client print:{}", new String(buffer.array()));
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    log.info("Client error, e:", exc);
                    try {
                        client.close();
                    } catch (IOException e) {
                        log.error("Client close error, e:", e);
                    }
                }
            });
            Thread.sleep(10 * 10000);
        } catch (Exception e) {
            log.error("Client error, e:", e);
        }
    }

    public static void main(String[] args) {
        demo();
    }
}
