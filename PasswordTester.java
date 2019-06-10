package Model;

import java.security.NoSuchAlgorithmException;

public class PasswordTester {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String password = "sample";
        byte[] salt = PasswordGenerator.getSalt();

        for (int i = 0; i<=10; i++)
        {
            //System.out.printf("salt %d: %s%n", i, Arrays.toString(PasswordGenerator.getSalt()));
            //   System.out.printf("password %d: %s/n", i,
            //          PasswordGenerator.getSHA512Password(password, PasswordGenerator.getSalt()));
            System.out.printf("password %s\n", PasswordGenerator.getSHA512Password(password, salt));
            //System.out.printf("password %d: %s/n", PasswordGenerator.getSHA512Password(password, salt));

        }
    }
}

