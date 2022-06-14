package util;

import entity.Driver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class FileUtil {
    public static <T> void writeDataToFile(LinkedList<T> data, String fileName) {
        if (FileUtil.isNullOrEmpty(fileName) || FileUtil.isNullOrEmpty(data)) {
            return;
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(data);
            objectOutputStream.writeObject("\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null;
    }

    public static boolean isNullOrEmptyArray(Object[] obj) {
        return obj == null && obj.length == 0;
    }
}
