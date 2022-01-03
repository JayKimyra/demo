//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
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

    static void createPdf(List<File> files, String folder, String fileName) {
        PDDocument document = new PDDocument();
        for (File file : files){
            if (file.isFile()) {
                try {
                    PDPage blankPage = new PDPage();
                    document.addPage(blankPage);
                    PDPageContentStream contents = new PDPageContentStream(document, blankPage);

                    try {
                        PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(file, document);
                        contents.drawImage(pdImage, 0, 0, 612, 792);
                    } catch (Throwable e) {
                        System.out.println(e);
                    }

                    contents.close();
                } catch (NullPointerException | IOException var11) {
                    var11.printStackTrace();
                }
            }
        }

        try {
            File directory = new File("D:\\TEST\\" + folder);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String var10001 = directory.getAbsolutePath();
            document.save(var10001 + "\\" + fileName + ".pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
