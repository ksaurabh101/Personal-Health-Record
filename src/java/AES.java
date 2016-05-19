/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saurabh
 */
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static String encrypt(String seed, String cleartext) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] result = encrypt(rawKey, cleartext.getBytes());
        return toHex(result);
    }

    public static String decrypt(String seed, String encrypted) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }


    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }
    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length()/2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2*buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }
    private final static String HEX = "0123456789ABCDEF";
    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
    }

    public static File encriptFile(File f)
    {
    	File out = new File(f.getName());
    	String s = "";
    	try{
    	    	
    		DataInputStream dis = new DataInputStream(new FileInputStream(f));
    		String  t = dis.readLine();
    		while( t != null)
    		{
    			s += t+'\n';
    			t = dis.readLine(); //dis.read(f.1)
    		}
    		String tenc = AES.encrypt("kumarsaurabh", s);
    			
    	  FileOutputStream fos = new FileOutputStream(out);
    	  fos.write(tenc.getBytes());
    	  
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return out;
    }
    
    
    public static File decriptFile(File f)
    {
    	File out = new File(f.getName());
    	String s = "";
    	try{
    	    	
    		DataInputStream dis = new DataInputStream(new FileInputStream(f));
    		String  t = dis.readLine();
    		while( t != null)
    		{
    			s += t+'\n';
    			t = dis.readLine(); //dis.read(f.1)
    		}
    		
    		String tenc = AES.decrypt("kumarsaurabh", s);    			
    		FileOutputStream fos = new FileOutputStream(out);
    		fos.write(tenc.getBytes());
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return out;
    }
    /*
     public static void main(String s[]) throws Exception
     {    	 
    	 	String crypto = AES.encrypt("12341561fdskhk", "This is any encryption algorithm");

    		String cleartext = AES.decrypt("12341561fdskhk", crypto);
    		
    		System.out.println("Crip :"+crypto);
    		
    		System.out.println("Dec :"+cleartext);
    		
    		File f = new File("C:\\Users\\Saurabh\\Desktop\\PHR\\New folder\\pills.jpg");
    		File ff = AES.encriptFile(f);
    		File ou = AES.decriptFile(ff);

    		String s1="";
    		DataInputStream dis = new DataInputStream(new FileInputStream(ou));
    		String  t = dis.readLine();
    		while( t != null)
    		{
    			s1 += t+'\n';
    			t = dis.readLine(); //dis.read(f.1)
    		}
    		System.out.println("fname : "+ou.getName());
    		System.out.println(s1);    		
     }
    */
}


