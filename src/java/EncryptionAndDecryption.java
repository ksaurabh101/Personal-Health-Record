
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saurabh
 */
public class EncryptionAndDecryption {
    
    public static void encrypt(File file,String ukey)throws Exception{
        //File file=new File("C:\\Users\\Saurabh\\Desktop\\PHR\\New folder\\pills.jpg");
        FileInputStream fis=new FileInputStream(file);
        
        FileOutputStream fos=new FileOutputStream(file);
        byte[] k=ukey.getBytes();
        SecretKeySpec key=new SecretKeySpec(k, "AES");
        System.out.println(key);
        Cipher enc=Cipher.getInstance("AES");
        enc.init(Cipher.ENCRYPT_MODE, key);
        CipherOutputStream cos=new CipherOutputStream(fos, enc);
        byte[] buf=new byte[1024];
        int read;
        while((read=fis.read(buf))!=-1)
        {
            cos.write(buf,0,read);
        }
        fis.close();
        fos.flush();
        cos.close();
    }
    public static void decrypt(File file,String ukey)throws Exception{
        //File file=new File("C:\\Users\\Saurabh\\Desktop\\PHR\\New folder\\encrypt1.jpg");
        FileInputStream fis=new FileInputStream(file);
        
        FileOutputStream fos=new FileOutputStream(file);
        byte[] k=ukey.getBytes();
        SecretKeySpec key=new SecretKeySpec(k, "AES");
        Cipher enc=Cipher.getInstance("AES");
        enc.init(Cipher.DECRYPT_MODE, key);
        CipherOutputStream cos=new CipherOutputStream(fos, enc);
        byte[] buf=new byte[1024];
        int read;
        while((read=fis.read(buf))!=-1)
        {
            cos.write(buf,0,read);
        }
        fis.close();
        fos.flush();
        cos.close();
        Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+file);
    }
}
