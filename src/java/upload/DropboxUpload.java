/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saurabh
 */
public class DropboxUpload {

    final String APP_KEY = "wqk24sgzfbug7zp";
    final String APP_SECRET = "85g1jgm3wrmsfnk";
    final String accessToken = "GnsSF65v7bAAAAAAAAAAF16t5Zy2p_HxPbWmR20AG3ySBkhT77pnChDfVmCCqyQA";
    DbxAppInfo appInfo;
    DbxRequestConfig config;
    DbxClient client;

    public DropboxUpload() {
        appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
        config = new DbxRequestConfig("myPHRApp/1.0", Locale.getDefault().toString());
        client = new DbxClient(config, accessToken);
    }

    public String getName() {
        try {
            return client.getAccountInfo().displayName;
        } catch (Exception e) {
            return "Error : " + e;
        }
    }

    public void uploadFile(File inputFolder, String fileName, String outputFolder) {
        try {
            File inputFile = new File(inputFolder + File.separator + fileName);
            String outFile = outputFolder + "/" + fileName;
            FileInputStream inputStream = new FileInputStream(inputFile);
            DbxEntry.File uploadedFile = client.uploadFile(outFile,
                    DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded File : " + uploadedFile.toString());
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public void getFileList(String folder) {
        try {
            DbxEntry.WithChildren listing = client.getMetadataWithChildren(folder);
            System.out.println("Files in the Folder :");
            for (DbxEntry child : listing.children) {
                System.out.println(" " + child.name + " : " + child.toString());
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public void downloadFile(String fileName, String inputFolder, File downloadFolder) {
        if (!downloadFolder.exists()) {
            downloadFolder.mkdir();
        }
        String inputFile = inputFolder + "/" + fileName;
        File outputFile = new File(downloadFolder + File.separator + fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            DbxEntry.File downloadedFile = client.getFile(inputFile, null, outputStream);
            System.out.println("Downloaded File: " + downloadedFile.toString());
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public void removeFile(String inputFolder, String fileName) {
        try {
            client.delete(inputFolder + "/" + fileName);
        } catch (DbxException ex) {
            System.out.println("Error : " + ex);
        }
    }

//    public static void main(String sa[]) {
//        DropboxUpload obj = new DropboxUpload();
//        File inputFolder = new File("C:\\Users\\Saurabh\\Desktop\\Saumya");
//        File downloadFolder = new File("C:\\Users\\Saurabh\\Desktop");
//        String outputFolder = "/Saumyaa";
//        String file = "BHU.pdf";
//        obj.uploadFile(inputFolder, file, outputFolder);
//        obj.getFileList(outputFolder);
//        obj.downloadFile(file, outputFolder, downloadFolder);
//        obj.removeFile("/PHR/PAT00001", "");
//    }
}
