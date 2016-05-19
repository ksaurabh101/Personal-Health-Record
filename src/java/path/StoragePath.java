/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package path;

/**
 *
 * @author Saurabh
 */
public class StoragePath {

    static String path = "C:\\Users\\Saurabh\\Desktop\\Project";
    static String downLoad = "C:\\Users\\Saurabh\\Desktop\\Download";
    static String dropboxDir = "/PHR/";

    public static String getDropboxDir() {
        return dropboxDir;
    }

    public static String getPath() {
        return path;
    }

    public static String getDownLoadFolder() {
        return downLoad;
    }
}
