import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexander on 27.11.15.
 */
public class MainForm extends JFrame {

    private JTextArea text = new JTextArea();
    private JTextArea textMessage = new JTextArea();
    private JTextArea textName = new JTextArea();
    private JTextArea remoteAddr = new JTextArea();
    private JTextArea remoteNick = new JTextArea();

    private JButton send = new JButton();
    private JButton apply = new JButton();
    private JButton connectButton = new JButton();
    private JButton disconnectButton = new JButton();

    public MainForm(){

        this.setBounds(250, 100, Const.WIDTH, Const.HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        text.setBounds(30, 30, 840,300);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFont(text.getFont().deriveFont(15f));
       // text.setBorder(BorderFactory.createEtchedBorder(0));
        text.setEditable(false);
        this.add(text);


        textMessage.setBounds(30, 350, 740, 30);
        textMessage.setFont(text.getFont().deriveFont(20f));
        textMessage.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 2));
        textMessage.setEnabled(false);
        this.add(textMessage);

        send.setText("SEND");
        send.setBounds(770, 350, 100, 30);
        send.setFont(text.getFont().deriveFont(15f));
        send.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 2));
        send.setEnabled(false);
        this.add(send);

        textName.setBounds(30, 400, 150, 30);
        textName.setFont(text.getFont().deriveFont(20f));
        textName.setBorder(BorderFactory.createEmptyBorder(2,2,0,2));
        textName.setEnabled(false);
        this.add(textName);

        apply.setText("Apply");
        apply.setBounds(180, 400, 100, 30);
        apply.setFont(text.getFont().deriveFont(15f));
        apply.setBorder(BorderFactory.createEmptyBorder(2, 2,0, 2 ));
        
        this.add(apply);

        connectButton.setText("Connect");
        connectButton.setBounds(475, 400, 100, 30);
        connectButton.setFont(text.getFont().deriveFont(15f));
        connectButton.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 2));
        this.add(connectButton);

        disconnectButton.setText("Disconnect");
        disconnectButton.setBounds(770, 400, 100, 30);
        disconnectButton.setFont(text.getFont().deriveFont(15f));
        disconnectButton.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 2));
        this.add(disconnectButton);

        remoteAddr.setBounds(325 , 400, 150, 30);
        remoteAddr.setFont(text.getFont().deriveFont(20f));
        remoteAddr.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 2));
        this.add(remoteAddr);

        remoteNick.setBounds(620, 400, 150, 30);
        remoteNick.setFont(text.getFont().deriveFont(20f));
        remoteNick.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 2));

        this.add(remoteNick);

        this.setVisible(true);

    }

    // GETTERS

    // LISTENERS

    public void addApplyListener(ActionListener act){
        //mvc
    }
}
