/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Saurabh
 */
public class PassEncrypt {

    public static String passEncrypt(String password) {
        String pass = "";
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            MD5.update(password.getBytes(), 0, password.getBytes().length);
            byte[] hashvalue = MD5.digest();
            pass = new BASE64Encoder().encode(hashvalue);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return pass;
    }
}
