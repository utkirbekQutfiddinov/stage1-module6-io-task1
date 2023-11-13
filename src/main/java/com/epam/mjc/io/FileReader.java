package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile=new Profile();
        try (FileInputStream in=new FileInputStream(file)){

            int a;
            StringBuilder text= new StringBuilder();

            while((a=in.read())!=-1){
                if(a!=13){
                    text.append((char) a);
                }else {
                    if(text.toString().contains("Name: ")){
                        profile.setName(text.substring(text.indexOf(" ")+1));
                    }else if(text.toString().contains("Age: ")){
                        profile.setAge(Integer.parseInt(text.substring(text.indexOf(" ")+1)));
                    }else if(text.toString().contains("Email: ")){
                        profile.setEmail(text.substring(text.indexOf(" ")+1));
                    } else {
                        profile.setPhone(Long.parseLong(text.substring(text.indexOf(" ")+1)));
                    }
                    text=new StringBuilder();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }
}
