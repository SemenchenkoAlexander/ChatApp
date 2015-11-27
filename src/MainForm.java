import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Alexander on 27.11.15.
 */
public class MainForm extends JFrame {

    private JTextArea text = new JTextArea();
    private JTextArea textMessage = new JTextArea();
    private JTextArea textName = new JTextArea();

    private JButton send = new JButton();
    private JButton apply = new JButton();
    private JButton connectButton = new JButton();
    private JButton disconnectButton = new JButton();

    public MainForm(){

        this.setBounds(250, 100, Const.WIDTH, Const.HEIGHT);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        text.setBounds(30, 30, 840,300);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(BorderFactory.createEtchedBorder(0));
        text.setEditable(false);
        this.add(text);


        textMessage.setBounds(30, 330, 740, 30);
        Border outside = BorderFactory.createEtchedBorder(0);
        Border inside = BorderFactory.createMatteBorder(0, 4, 0, 4, Color.WHITE);
        textMessage.setBorder(BorderFactory.createCompoundBorder(outside,inside));
        textMessage.setFont(textMessage.getFont().deriveFont(18f));
        this.add(textMessage);

        textName.setBounds(550, 440, 150, 30);
        textName.setBorder(BorderFactory.createEtchedBorder(0));
        this.add(textName);

        send.setText("SEND");
        send.setBounds(770, 330, 100, 30);
        this.add(send);

        apply.setText("Apply");
        apply.setBounds(700, 440, 70, 30);
        apply.setBackground(new Color(249,6,0));
        apply.setForeground(new Color(45,0,0));
        this.add(apply);

        connectButton.setText("Connect");
        connectButton.setBounds(80, 430, 100, 50);
        this.add(connectButton);

        disconnectButton.setText("Disconnect");
        disconnectButton.setBounds(200, 430, 100, 50);
        this.add(disconnectButton);

        this.setVisible(true);

    }

    public String getMessage(){
        return textMessage.getText();
    }

    public void appendText(String txt){
        this.text.append(txt + "\n");
    }

    public void addSendListener(ActionListener act){
        send.addActionListener(act);
    }

    public void addConListener(ActionListener act){
        connectButton.addActionListener(act);
    }

    public void addDisconListener(ActionListener act){
        disconnectButton.addActionListener(act);
    }

    public void addApplyListener(ActionListener act){
        apply.addActionListener(act);
    }
}
