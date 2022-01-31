package com.example.demo;


import com.example.demo.hibernate.entities.Street;
import org.joda.time.LocalDate;

import javax.mail.search.SearchTerm;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Formatter {
    public static String getFileName(String street,String home, String flat) {
        return getFullLocation(street,home,flat);
    }

    public static String getDirectoryName(LocalDate date, Street street){
        StringBuilder sb = new StringBuilder();
        sb.append(date.toString());
        sb.append("\\");
        sb.append(street.getName());
        sb.append("\\");
        return sb.toString();
    }

    public static String getFullLocation(String street,String home, String flat) {
        return street + ", д. " + home + ", " + flat;
    }

    public static String ISOtoUTF(String str){
        return new String(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
    public static List<File> getFilesFromParts(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<File> files = new ArrayList<>();
        try {
            request.getParts().forEach(System.out::println);
            List<Part> fileParts = request.getParts().stream().filter((part) -> "files".equals(part.getName())).toList();
            System.out.println("Found " + fileParts.size() + " files:");
            for (Part filePart : fileParts){
                String imgName = Formatter.ISOtoUTF(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
                InputStream fileContent = filePart.getInputStream();
                File targetFile = new File("D:\\TEST\\" + filePart.getSubmittedFileName());
                System.out.println("\t" + imgName);
                Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                files.add(targetFile);
            }

        } catch (IOException | ServletException | NullPointerException e) {
            System.out.println(e);
        }

        return files;
    }



    static public void Zip(List<File> files, String zip_file) throws Exception
    {
        // Cоздание объекта ZipOutputStream из FileOutputStream
        FileOutputStream fout = new FileOutputStream(zip_file);
        ZipOutputStream zout = new ZipOutputStream(fout);
        // Определение кодировки

        for(File file : files) {
            System.out.println("Добавление файла <" + file.getName() + ">");
            if (!file.exists()){
                System.out.println("Пропуск, такого файла не существует");
                continue;
            }
            FileInputStream fis = new FileInputStream(file);

            zout.putNextEntry(new ZipEntry(file.getPath()));

            byte[] buffer = new byte[4048];
            int length;
            while((length = fis.read(buffer)) > 0)
                zout.write(buffer, 0, length);
            zout.closeEntry();
            fis.close();
        }

        zout.close();

        System.out.println("Zip файл создан!");
    }

    static public void ZipAllFiles(String source_dir, String zip_file) throws Exception
    {
        // Cоздание объекта ZipOutputStream из FileOutputStream
        FileOutputStream fout = new FileOutputStream(zip_file);
        ZipOutputStream zout = new ZipOutputStream(fout);
        // Определение кодировки


        // Создание объекта File object архивируемой директории
        File fileSource = new File(source_dir);

        addDirectory(zout, fileSource);

        // Закрываем ZipOutputStream
        zout.close();

        System.out.println("ZipAllFiles файл создан!");
    }

    static private void addDirectory(ZipOutputStream zout, File fileSource)
            throws Exception
    {
        File[] files = fileSource.listFiles();
        System.out.println("Добавление директории <" + fileSource.getName() + ">");
        for(int i = 0; i < files.length; i++) {
            // Если file является директорией, то рекурсивно вызываем
            // метод addDirectory
            if(files[i].isDirectory()) {
                addDirectory(zout, files[i]);
                continue;
            }
            System.out.println("Добавление файла <" + files[i].getName() + ">");

            FileInputStream fis = new FileInputStream(files[i]);

            zout.putNextEntry(new ZipEntry(files[i].getPath()));

            byte[] buffer = new byte[4048];
            int length;
            while((length = fis.read(buffer)) > 0)
                zout.write(buffer, 0, length);
            // Закрываем ZipOutputStream и InputStream
            zout.closeEntry();
            fis.close();
        }
    }

}
