//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PdfManager {
    static final int MAX_WIDTH = 612;
    static final int MAX_HEIGHT = 792;
    static final String savePath = "D:\\TEST\\";

    public PdfManager() {
    }

    public static boolean createPdf(List<File> files, String folder, String fileName) {

        PDDocument document = new PDDocument();
        for (File file : files){
            if (file.isFile()) {
                PDPage blankPage = new PDPage();
                document.addPage(blankPage);
                try (PDPageContentStream contents = new PDPageContentStream(document, blankPage)) {
                    try {
                        PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(file, document);
                        contents.drawImage(pdImage, 0, 0, MAX_WIDTH, MAX_HEIGHT);
                    } catch (IOException e) {
                        System.out.println("PM1");
                        System.out.println(e);
                    }
                } catch (IOException e) {
                    System.out.println("PM2");
                    e.printStackTrace();
                }
            }
        }

        try {
            File directory = new File(savePath + folder);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String absolutePath = directory.getAbsolutePath();
            if (new File(absolutePath + "\\" + fileName + ".pdf").exists()) return false;
            document.save(absolutePath + "\\" + fileName + ".pdf");
        } catch (IOException e) {
            System.out.println("PM3");
            e.printStackTrace();
        }
        finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
