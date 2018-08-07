package com.example.ryan.todomanager;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoadData {

    static public void save(String filename, Object objectToSave,Context context){
        File file = new File(context.getFilesDir(), filename);
        FileOutputStream fos;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(objectToSave);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static public Object load(String filename,Context context){
        File file = new File(context.getFilesDir(), filename);
        FileInputStream fis = null;
        ObjectInputStream in = null;
        Object object = null;
        if(file.exists()){
            try {
                fis = new FileInputStream(file);
                in = new ObjectInputStream(fis);
                object = in.readObject();
                in.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            object = new TaskList(new Task[0]);
        }

        return object ;
    }
}
