
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItemStream;
import path.StoragePath;
import upload.DropboxUpload;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Saurabh
 */
public class uploadProcess {

    //Encrypt File
    public static boolean encrypt(String path, FileItemStream item, String patientID) {
        try {
            File mainFolder = new File(path + File.separator + "Encrypt");
            if (!mainFolder.exists()) {
                mainFolder.mkdir();
            }
            File patientFolder = new File(mainFolder + File.separator + patientID);
            if (!patientFolder.exists()) {
                patientFolder.mkdir();
            }
            String ukey = GetKey.getPatientKey(patientID);
            InputStream fis = item.openStream();

            FileOutputStream fos = new FileOutputStream(patientFolder.getAbsolutePath() + File.separator + item.getName());
            byte[] k = ukey.getBytes();
            SecretKeySpec key = new SecretKeySpec(k, "AES");
            System.out.println(key);
            Cipher enc = Cipher.getInstance("AES");
            enc.init(Cipher.ENCRYPT_MODE, key);
            CipherOutputStream cos = new CipherOutputStream(fos, enc);
            byte[] buf = new byte[1024];
            int read;
            while ((read = fis.read(buf)) != -1) {
                cos.write(buf, 0, read);
            }
            fis.close();
            fos.flush();
            cos.close();

            //Upload File to cloud
            DropboxUpload upload = new DropboxUpload();
            upload.uploadFile(patientFolder, item.getName(), StoragePath.getDropboxDir() + patientID);
            DeleteDirectory.delete(patientFolder);

            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    //Encrypt Data textFileUpload
    public static boolean encrypt(File inputFolder, File outputFolder, String fileName, String patientID) {
        try {

            String ukey = GetKey.getPatientKey(patientID);
            File filePath = new File(inputFolder.getAbsolutePath() + File.separator + fileName);
            FileInputStream fis = new FileInputStream(filePath);
            File outputFile = new File(outputFolder.getAbsolutePath() + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(outputFile);
            byte[] k = ukey.getBytes();
            SecretKeySpec key = new SecretKeySpec(k, "AES");
//            System.out.println(key);
            Cipher enc = Cipher.getInstance("AES");
            enc.init(Cipher.ENCRYPT_MODE, key);
            CipherOutputStream cos = new CipherOutputStream(fos, enc);
            byte[] buf = new byte[1024];
            int read;
            while ((read = fis.read(buf)) != -1) {
                cos.write(buf, 0, read);
            }
            fis.close();
            fos.flush();
            cos.close();

            //Upload File to cloud
            DropboxUpload upload = new DropboxUpload();
            upload.uploadFile(outputFolder, fileName, StoragePath.getDropboxDir() + patientID);
            DeleteDirectory.delete(outputFolder);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    //Decrypt File
    public static boolean decrypt(File inputFolder, String fileName, String patientID, String viewKey) {
        try {

            String ukey = GetKey.getPatientKey(patientID);
            if (viewKey.equals(ukey)) {
                //Download File from Cloud..
                DropboxUpload download = new DropboxUpload();
                download.downloadFile(fileName, StoragePath.getDropboxDir() + patientID, inputFolder);

                File inputFile = new File(inputFolder.getAbsolutePath() + File.separator + fileName);
                FileInputStream fis = new FileInputStream(inputFile);
                File outputFolder = new File(inputFolder.getAbsolutePath() + File.separator + "temp");
                if (!outputFolder.exists()) {
                    outputFolder.mkdir();
                }
                FileOutputStream fos = new FileOutputStream(outputFolder.getAbsolutePath() + File.separator + fileName);
                byte[] k = ukey.getBytes();
                SecretKeySpec key = new SecretKeySpec(k, "AES");
                Cipher enc = Cipher.getInstance("AES");
                enc.init(Cipher.DECRYPT_MODE, key);
                CipherOutputStream cos = new CipherOutputStream(fos, enc);
                byte[] buf = new byte[1024];
                int read;
                while ((read = fis.read(buf)) != -1) {
                    cos.write(buf, 0, read);
                }
                fis.close();
                fos.flush();
                cos.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
//FileAccess.java ...Used for User Read Action
    public static boolean decrypt(File inputFolder, String fileName, String patientID) {
        try {
            //Download File from Cloud..
            DropboxUpload download = new DropboxUpload();
            download.downloadFile(fileName, StoragePath.getDropboxDir() + patientID, inputFolder);

            String ukey = GetKey.getPatientKey(patientID);
            File inputFile = new File(inputFolder.getAbsolutePath() + File.separator + fileName);
            FileInputStream fis = new FileInputStream(inputFile);
            File outputFolder = new File(inputFolder.getAbsolutePath() + File.separator + "temp");
            if (!outputFolder.exists()) {
                outputFolder.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(outputFolder.getAbsolutePath() + File.separator + fileName);
            byte[] k = ukey.getBytes();
            SecretKeySpec key = new SecretKeySpec(k, "AES");
            Cipher enc = Cipher.getInstance("AES");
            enc.init(Cipher.DECRYPT_MODE, key);
            CipherOutputStream cos = new CipherOutputStream(fos, enc);
            byte[] buf = new byte[1024];
            int read;
            while ((read = fis.read(buf)) != -1) {
                cos.write(buf, 0, read);
            }
            fis.close();
            fos.flush();
            cos.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
}
