package org.tech.accumulation.demo.java.io;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author peiheng.jiang create on 2019/9/8
 */
@Slf4j
public class SimpleIoDemo {

    /**
     * JDK7之前，使用字符流，读取文件内容
     * @param path
     * @throws IOException
     */
    public static void readBefore7(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder builder = new StringBuilder();
        String line;
        while (!Strings.isNullOrEmpty(line = br.readLine())) {
            builder.append(line).append(System.lineSeparator());
        }
        br.close();
        fr.close();

        log.info("File info:{}", builder.toString());
    }

    /**
     * JDK7之前，使用字节流，写入文件内容
     * @param path
     */
    public static void writeBefore7(String path) throws IOException {
        String content = "Hello world!";

        FileWriter fw = new FileWriter(path, false);
        fw.write(content);
        fw.close();
    }

    /**
     * JDK7之后，读取文件
     * @param path
     */
    public static void readAfter7(String path) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(path));
        log.info("File info:{}", new String(data, StandardCharsets.UTF_8));
    }

    /**
     * JDK7之后，写入文件
     * @param path
     */
    public static void writeAfter7(String path) throws IOException {
        String content = "Hello world!";
        Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
    }
}
