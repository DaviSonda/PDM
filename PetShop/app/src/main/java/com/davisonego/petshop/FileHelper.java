package com.davisonego.petshop;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileHelper {
    File path;
    File file;
    FileOutputStream stream;

    //EXAMPLES ON HOW TO USE
    //new FileHelper().WriteFile(this, "login.txt", "User");
    //System.out.println(new FileHelper().ReadFile(this, "login.txt"));
    //new FileHelper().DeleteFile(this, "login.txt");
    //System.out.println(new FileHelper().ReadFile(this, "login.txt"));
    //new FileHelper().WriteFile(this, "login.txt", "Admin");
    //System.out.println(new FileHelper().ReadFile(this, "login.txt"));

    public void WriteFile(Context context, String fileName, String fileData) {
        try {
            path = context.getExternalFilesDir(null);
            file = new File(path, fileName);
            stream = new FileOutputStream(file);
            try {
                stream.write(fileData.getBytes());
            } finally {
                stream.close();
            }
        } catch (Exception e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
    }

    public String ReadFile(Context context, String fileName) {
        String contents = "";
        try {
            path = context.getExternalFilesDir(null);
            file = new File(path, fileName);
            int length = (int) file.length();

            byte[] bytes = new byte[length];

            FileInputStream in = new FileInputStream(file);
            try {
                in.read(bytes);
            } finally {
                in.close();
            }

            contents = new String(bytes);
        } catch (Exception e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
        return contents;
    }

    public void DeleteFile(Context context, String fileName) {
        path = context.getExternalFilesDir(null);
        file = new File(path, fileName);
        file.delete();
    }
}
