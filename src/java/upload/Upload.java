/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload;

/**
 *
 * @author Saurabh
 */
import com.dropbox.core.*;
import java.io.*;
import java.util.Locale;

public class Upload {

    String APP_KEY = "";
    String APP_SECRET = "";
    DbxAppInfo appInfo;
    DbxRequestConfig config;
    DbxWebAuthNoRedirect webAuth;
    String authorizeUrl = "";
    DbxAuthFinish authFinish;
    String accessToken;
    DbxClient client;

    public Upload() {
        APP_KEY = "wqk24sgzfbug7zp";
        APP_SECRET = "85g1jgm3wrmsfnk";

        appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        config = new DbxRequestConfig("myPHRApp/1.0", Locale.getDefault().toString());
        webAuth = new DbxWebAuthNoRedirect(config, appInfo);
        authorizeUrl = webAuth.start();
    }

    public String getUplink() {
        return authorizeUrl;
    }

    public void setAuthFinish(String code) {
        try {
            authFinish = webAuth.finish(code);
            accessToken = authFinish.accessToken;
            client = new DbxClient(config, accessToken);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public String getName() {
        try {
            return client.getAccountInfo().displayName;
        } catch (Exception e) {
            return "Error : " + e;
        }
    }

    public void uploadFile(File inputFile, String outputFile) {
        try {
            FileInputStream inputStream = new FileInputStream(inputFile);
            DbxEntry.File uploadedFile = client.uploadFile(outputFile,
                    DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
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

    public void downloadFile(File fileName, File downloadFolder, String inputFile) {
        if (!downloadFolder.exists()) {
            downloadFolder.mkdir();
        }
        File outputFile = new File(downloadFolder + File.separator + fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            DbxEntry.File downloadedFile = client.getFile(inputFile, null,outputStream);
            System.out.println("Metadata: " + downloadedFile.toString());
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
    
}
