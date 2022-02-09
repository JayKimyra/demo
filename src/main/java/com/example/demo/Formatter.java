package com.example.demo;


import com.example.demo.hibernate.entities.Street;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Formatter {
    public static String getFileName(String street,String home, String flat) {
        return getFullLocation(street,home,flat);
    }

    public static String getDirectoryName(Date date, Street street){
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

    public static Timestamp StringToTimestamp(String str){
        if (str == null || str.equals("")) return new Timestamp(System.currentTimeMillis());
        try {
            if (StringUtils.countMatches(str, ":") == 1) {
                str += ":00";
            }
            return Timestamp.valueOf(str.replace("T"," "));
        }
        catch (Exception e){
            return new Timestamp(System.currentTimeMillis());
        }
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
            System.out.println(getZipEntryName(file,2,new StringBuilder()));
            zout.putNextEntry(new ZipEntry(getZipEntryName(file,2,new StringBuilder())));

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

    public static String getZipEntryName(File file,int depth,StringBuilder sb) {
        if (file.isDirectory()) sb.insert(0,"\\");
        sb.insert(0,file.getName());
        if (depth == 0){
            return sb.toString();
        }
        return getZipEntryName(file.getParentFile(),depth - 1, sb);
    }

    public static void main(String[] args) throws Exception {
        /*List<File> files = new ArrayList<>();
        files.add(new File("D:\\TEST\\2022-02-01\\Абитуриентский переулок\\Абитуриентский переулок, д. были времена, отлично было.pdf"));
        Zip(files,"zipped_file");*/
        ZipAllFiles("D:\\TEST\\","D:\\name.zip");

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
        for(int i = 0; i < Objects.requireNonNull(files).length; i++) {
            // Если file является директорией, то рекурсивно вызываем
            // метод addDirectory
            if(files[i].isDirectory()) {
                addDirectory(zout, files[i]);
                continue;
            }
            System.out.println("Добавление файла <" + files[i].getName() + ">");

            FileInputStream fis = new FileInputStream(files[i]);

            zout.putNextEntry(new ZipEntry(getZipEntryName(files[i],2,new StringBuilder())));

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
