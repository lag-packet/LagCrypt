import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionTool {

    //TODO
    // SEE IF OVERFLOW EFFECTS ENCRYPTION FOR BINARIES.

    public EncryptionTool() {

    }

    public void encrypt(File fileToEncrypt, int cipherOffset) {
        try {
            System.out.println("Encrypting");
            FileInputStream fInputStream = new FileInputStream(fileToEncrypt);

            int byteRead = -1;
            int[] fileContents = new int[(int) fileToEncrypt.length()];
            int i = 0;
            System.out.println(fileContents.length);
            while ((byteRead = fInputStream.read()) != -1) {
                fileContents[i] = byteRead;
                System.out.println("read:" + fileContents[i]);
                i++;
            }
            fInputStream.close();

            FileOutputStream fOutputStream = new FileOutputStream(fileToEncrypt);
            for (int j = 0; j < fileContents.length; j++) {
                fileContents[j] = fileContents[j] + cipherOffset;
                fOutputStream.write(fileContents[j]);
                System.out.println("writing:" + fileContents[j]);
            }
            fOutputStream.flush();
            fOutputStream.close();

            System.out.println("Encrypting done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decrypt(File file, int cipherOffset) {
        encrypt(file, -cipherOffset);
    }

    public String getMD5(File file) {
        //code used from https://howtodoinjava.com/java/java-security/sha-md5-file-checksum-hash/

        MessageDigest md5Digest = null;
        StringBuilder sb = new StringBuilder();
        FileInputStream fis = null;

        try {
            md5Digest = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);

            //Create byte array to read data in chunks
            byte[] byteArray = new byte[1024];
            int bytesCount = 0;

            //Read file data and update in message digest
            while ((bytesCount = fis.read(byteArray)) != -1) {
                md5Digest.update(byteArray, 0, bytesCount);
            }

            fis.close();

            //Get the hash's bytes
            byte[] bytes = md5Digest.digest();

            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
