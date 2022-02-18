import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GUI extends JFrame implements ActionListener {

    private TextField fileTextField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JButton fileOpen;

    public GUI() {
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


        fileTextField = new TextField();
        fileTextField.setBounds(150, 0, 150, 30);
        this.add(fileTextField);

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
        if (e.getSource() == encryptButton) {
            System.out.println("G");
        } else if (e.getSource() == decryptButton) {
            System.out.println("H");
        } else if (e.getSource() == fileOpen) {
            JFileChooser fc=new JFileChooser();
            int i=fc.showOpenDialog(this);
            if(i==JFileChooser.APPROVE_OPTION){
                File f=fc.getSelectedFile();
                String filepath=f.getPath();
                try{
                    BufferedReader br=new BufferedReader(new FileReader(filepath));
                    String s1="",s2="";
                    while((s1=br.readLine())!=null){
                        s2+=s1+"\n";
                    }
                    fileTextField.setText(s2);
                    br.close();
                }catch (Exception ex) {ex.printStackTrace();  }
            }
        }
    }
}
