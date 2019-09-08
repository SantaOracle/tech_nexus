package org.tech.accumulation.demo.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @author peiheng.jiang create on 2019/9/8
 */
public class NioDemo {

    /**
     * 基于NIO，拷贝文件
     * @param fromPath
     * @param toPath
     * @throws IOException
     */
    public static void copyFile(String fromPath, String toPath) throws IOException {
        FileInputStream fis = new FileInputStream(fromPath);
        FileOutputStream fos = new FileOutputStream(toPath);

        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear();
            if (readChannel.read(buffer) == -1) {
                break;
            }
            buffer.flip();
            writeChannel.write(buffer);
        }

        fis.close();
        fos.close();
    }

    /**
     * 基于NIO的TCP服务器实现
     * @param listenPort
     */
    public static void nioTcpServer(int listenPort) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel listenChannel = ServerSocketChannel.open();
        listenChannel.socket().bind(new InetSocketAddress(listenPort));
        listenChannel.configureBlocking(false);
        listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(3000) == 0) {
                continue;
            }
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                if (key.isAcceptable() || key.isReadable()) {
                    // TODO: handleAccept(key)
                }
                if (key.isValid() && key.isWritable()) {
                    // TODO: handleWrite(key)
                }
                keyIter.remove();
            }
        }
    }
}
