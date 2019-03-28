package org.tech.accumulation.util;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author yuanwei on 2018/12/26
 **/
@Slf4j
public final class FileUtil {

    private static final int BYTE_COUNT_IN_1KB = 1024;
    private static final int EFFECTIVE_BYTES_OF_LONG = 63;
    private static final int DECIMAL = 10;

    private FileUtil() {
    }

    /**
     * 将字节数转换为适合阅读的形式，如 MB、GB 等
     *
     * @param byteCount 字节个数
     * @return 新格式
     */
    @NonNull
    public static String humanReadableSize(long byteCount) {
        if (byteCount < BYTE_COUNT_IN_1KB) {
            return byteCount + " B";
        }
        int z = (EFFECTIVE_BYTES_OF_LONG - Long.numberOfLeadingZeros(byteCount)) / DECIMAL;
        return String.format("%.4f %sB", (double) byteCount / (1L << (z * DECIMAL)), " KMGTPE".charAt(z));
    }

    @NonNull
    public static String humanReadableSize(@Nullable File file) {
        long byteCount = Optional.ofNullable(file).map(File::length).orElse(0L);
        return humanReadableSize(byteCount);
    }

    /**
     * 递归删除文件，可以根据指定条件删除，当传入条件为null时，表明全部删除
     *
     * @param file    待删除的文件（夹）
     * @param matcher 匹配器
     * @throws IOException 删除文件失败时会抛异常
     */
    public static void recursiveDelete(@NonNull File file, @Nullable Predicate<File> matcher) throws IOException {
        if (!file.exists()) {
            return;
        }

        boolean matched = Optional.ofNullable(matcher).map(predicate -> predicate.test(file)).orElse(true);

        if (file.isFile() && matched) {
            Files.delete(file.toPath());
        }

        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                recursiveDelete(f, matcher);
            } else {
                if (matched) {
                    Files.delete(file.toPath());
                }
            }
        }
    }
}
