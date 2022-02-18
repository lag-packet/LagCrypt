import java.io.*;

public class LagCrypt {

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir") + "/files/myFile.txt");
        EncryptionTool et = new EncryptionTool();

        GUI gui = new GUI();
        //et.encrypt(file,209);
        //et.decrypt(file, 209);
        //System.out.println(et.getMD5(file));
    }
}
