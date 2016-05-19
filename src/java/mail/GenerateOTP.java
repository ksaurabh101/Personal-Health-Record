/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Saurabh
 */
public class GenerateOTP {

    private static final Random RANDOM = new SecureRandom();
    public static final int PASSWORD_LENGTH = 4;

    public String generateOTP() {
        String letters = "0123456789";

        String pw = "";
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        return pw;
    }
}
