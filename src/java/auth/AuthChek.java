/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import path.StoragePath;
import upload.DropboxUpload;

/**
 *
 * @author Saurabh
 */
public class AuthChek {

    String path = "",
            patientID = "",
            fileName = "";

    public AuthChek(String path, String patientID, String fileName) {
        this.path = path;
        this.patientID = patientID;
        this.fileName = fileName;
    }

    public boolean emergencyCheck() {
        File patientFolder = new File(path + File.separator + "Encrypt" + File.separator + patientID);
        if (!patientFolder.exists()) {
            patientFolder.mkdir();
        }
        String policyFileName = "policy" + fileName + ".txt";
        //Download file from cloud
        DropboxUpload download = new DropboxUpload();
        download.downloadFile(policyFileName, StoragePath.getDropboxDir() + patientID, patientFolder);

        File policyFile = new File(patientFolder.getAbsolutePath() + File.separator + policyFileName);
        try {
            FileInputStream fis = new FileInputStream(policyFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<String> profession = new ArrayList<>();
            ArrayList<String> speciality = new ArrayList<>();
            ArrayList<String> organization = new ArrayList<>();
            profession = (ArrayList<String>) ois.readObject();
            speciality = (ArrayList<String>) ois.readObject();
            organization = (ArrayList<String>) ois.readObject();
            for (String s : organization) {
                if (s.equals("Emergency")) {
                    policyFile.delete();
                    return true;
                }
            }
        } catch (Exception e) {
            policyFile.delete();
            System.out.println("Error : " + e);
        }
        policyFile.delete();
        return false;
    }
}
