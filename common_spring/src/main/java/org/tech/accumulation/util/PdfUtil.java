package org.tech.accumulation.util;

import com.google.code.appengine.awt.Color;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.itext.extension.font.ITextFontRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangpeiheng create on 2019/12/15
 */
@Slf4j
public class PdfUtil {

    /**
     * docx文档转化为pdf文件
     *
     * @param is word文件输入流
     * @param os pdf文件输入流
     */
    public static void docx2Pdf(InputStream is, OutputStream os) {
        long start = System.currentTimeMillis();
        try {
            XWPFDocument document = new XWPFDocument(is);
            PdfOptions options = PdfOptions.create().fontEncoding(StandardCharsets.UTF_8.toString());
            options.fontProvider(new ITextFontRegistry() {
                @Override
                public Font getFont(String familyName, String encoding, float size, int style, Color color) {
                    return super.getFont(familyName, encoding, size, style, color);
                }
            });
            PdfConverter.getInstance().convert(document, os, options);
        } catch (Exception e) {
            log.error("Word to pdf error, e:", e);
        } finally {
            log.info("Word to pdf cost:{}", System.currentTimeMillis() - start);
        }
    }

    public static void main(String[] args) {
        String dirPath = "/Users/jiangpeiheng/myhome/work_stuff/esign/";
        String filename = "CONTRACT AND BILL OF SALE FOR AUTOMOBILE";

        try (
                InputStream is = new FileInputStream(dirPath + filename + ".docx");
                OutputStream os = new FileOutputStream(dirPath + filename + ".pdf")
        ) {
            docx2Pdf(is, os);
        } catch (Exception e) {
            log.error("PdfUtil exception, e:", e);
        }

    }
}
