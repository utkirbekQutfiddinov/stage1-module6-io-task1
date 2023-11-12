package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile=new Profile();
        try (FileInputStream in=new FileInputStream(file)){

            int a;
            String text="";

            boolean name=false;
            boolean age=false;
            boolean email=false;
            boolean phone=false;

            while((a=in.read())!=-1){



                if (a==32&&text.equals("Name:")){
                    name=true;
                    text="";
                }
                if (a==32&&text.equals("Age:")){
                    age=true;
                    text="";
                }
                if (a==32&&text.equals("Email:")){
                    email=true;
                    text="";
                }
                if (a==32&&text.equals("Phone:")){
                    phone=true;
                    text="";
                }


                if(name&&a==13){
                    profile.setName(text);
                    text="";
                    name=false;
                }
                if(age&&a==13){
                    profile.setAge(Integer.parseInt(text));
                    text="";
                    age=false;
                }
                if(email&&a==13){
                    profile.setEmail(text);
                    text="";
                    email=false;
                }
                if(phone&&a==13){
                    profile.setPhone(Long.parseLong(text));
                    text="";
                    phone=false;
                }
            if(a!=13&&(char)a!=' '&&a!=10){
                text+=(char)a;
            }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }

    public static void main(String[] args) {
        FileReader f=new FileReader();

        Profile p=f.getDataFromFile(new File("D:\\01. Projects\\EPAM\\MJC\\Stage-1\\stage1-module6-io-task1\\src\\main\\resources\\Profile.txt"));
        System.out.println(p);
    }
}
