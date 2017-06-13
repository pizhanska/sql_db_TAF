package com.mentorship.db.utilities;

import com.mentorship.db.model.ActorObj;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uliana Pizhanska on 04/06/2017.
 */
public class ReadWriteObjFile {
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private Object obj;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    public Object readObj(String filepath){
        try {
            fileInputStream = new FileInputStream(filepath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
            return obj;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public  void writeObjectToFile(String filepath,Object serObj) {
        try {
            fileOutputStream = new FileOutputStream(filepath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(serObj);
            objectOutputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
