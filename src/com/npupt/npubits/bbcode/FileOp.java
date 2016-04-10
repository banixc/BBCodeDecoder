package com.npupt.npubits.bbcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileOp{
    public static final String encoding = "GBK";
    public static final String head = "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + encoding + "\" /><body>";
    public static final String foot = "</body>";

    public static String readFile(String filePath){
        try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ 
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String allTxt = "";
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        allTxt+=lineTxt+"\n";
                    }
                    read.close();
                    return allTxt;
                }
        } catch (Exception e) {
            System.out.println("Read error!");
            e.printStackTrace();
        }
        return null;
     
    }

    public static boolean writeFile(String fileName,String content) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName),encoding);
            out.write(content);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
     
    public static void main(String[] args){
        if(args.length>0) {
            for(int i=0;i<args.length;i++) {
                String fileName = args[i];
                String content = readFile(fileName);
                if(content!=null) {
                    BCItem item = new BCDecode(content).getItem();
                    boolean flags = writeFile(fileName + ".html",head + item.toHtml() + foot);
                    System.out.println(fileName + " Convert " + (flags?"success!":"failed!"));
                } else {
                    System.out.println(fileName + " Not find!");
                }
            }
        } else {
            System.out.println("Usage: FileName1 FileName2 FileName3");
        }
    }
}