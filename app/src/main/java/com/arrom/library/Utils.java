package com.arrom.library;

/**
 * Created by Administrator on 2017/2/26.
 */

public class Utils {

    public static boolean isExist(String className,ClassLoader loader){
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
