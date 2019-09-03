package main;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    private static final String SECRET_KEY = "JBSWY3DPEHPK3PXP";
    private static final int PERIOD = 30;
    private static final int DIGITS = 6;

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Your code: ");
            String receivedCode = sc.nextLine();
            String code = Authenticator.getOneTimePassword(SECRET_KEY, PERIOD, DIGITS);
            if (code.equals(receivedCode)) {
                System.out.println("Successful!");
            } else {
                System.out.println("Failed authentication!");
                System.out.println("EXPECTING " + code + " BUT " + receivedCode + " WAS FOUND");
            }
        }
    }
}
