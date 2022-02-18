import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame implements ActionListener {

    //TODO: ADD CIPHER FUNCTIONALITY

    private TextField fileTextField;
    private TextField cipherTextField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JButton fileOpen;
    private JFileChooser jFileChooser;

    private EncryptionTool et;

    public GUI(EncryptionTool et) {
        this.et = et;
        init();
    }

    public void init() {
        this.setTitle("LagCrypt");
        this.setSize(400,450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        addComponents();
        this.setLayout(null);
        this.setVisible(true);
    }

    public void addComponents() {
        JLabel fileLabel = new JLabel();
        fileLabel.setText("File/Files in path: ");
        fileLabel.setBounds(0, 0, 150, 30);
        this.add(fileLabel);

        JLabel cipherLabel = new JLabel();
        cipherLabel.setText("Cipher: ");
        cipherLabel.setBounds(0, 50, 100, 30);
        this.add(cipherLabel);

        fileTextField = new TextField();
        fileTextField.setBounds(150, 0, 150, 30);
        this.add(fileTextField);

        cipherTextField = new TextField();
        cipherTextField.setBounds(100, 50, 150, 30);
        this.add(cipherTextField);

        encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(150, 150, 90, 30);
        encryptButton.addActionListener(this);
        this.add(encryptButton);

        decryptButton = new JButton("Decrypt");
        decryptButton.setBounds(150, 250, 90, 30);
        decryptButton.addActionListener(this);
        this.add(decryptButton);

        fileOpen = new JButton("Open");
        fileOpen.setBounds(300, 50, 90, 30);
        fileOpen.addActionListener(this);
        this.add(fileOpen);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonEvents(e);
    }

    public void buttonEvents(ActionEvent e) {
        if (e.getSource() == encryptButton) {
            File file = jFileChooser.getSelectedFile();
            System.out.println(file.getPath());
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File f : listFiles) {
                    et.encrypt(f, Integer.parseInt(cipherTextField.getText()));
                }
            }
        } else if (e.getSource() == decryptButton) {
            File file = jFileChooser.getSelectedFile();
            System.out.println(file.getPath());
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File f : listFiles) {
                    et.decrypt(f, Integer.parseInt(cipherTextField.getText()));
                }
            }
        } else if (e.getSource() == fileOpen) {
            jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new java.io.File("."));
            jFileChooser.setDialogTitle("Select file/folder");
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.showOpenDialog(this);
            fileTextField.setText(jFileChooser.getSelectedFile().toString());
        }
    }
}
