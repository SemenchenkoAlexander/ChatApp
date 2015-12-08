import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexander on 27.11.15.
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.UnexpectedException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.portable.UnknownException;

import com.sun.glass.events.MouseEvent;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

import java.awt.Color;

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




    private CallListener callListener;
    private Caller caller;
    private Connection connection;
    private CallListenerThread callLT;
    private CommandListenerThread commandLT;
    private boolean forAccept;
    private ServerConnection server;

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
        this.add(textName);

        apply.setText("Apply");
        apply.setBounds(180, 400, 100, 30);
        apply.setFont(text.getFont().deriveFont(15f));
        apply.setBorder(BorderFactory.createEmptyBorder(2, 2,0, 2 ));

        apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login;
                if (textName.getText().equals("")) {
                    login = "unnamed";
                } else
                    login = textName.getText();
                boolean isCorrectLogin = false;
                for (int i = 0; i < login.toCharArray().length; i++)
                    if (login.toCharArray()[i] != ' ') {
                        isCorrectLogin = true;
                        break;
                    }
                if (!isCorrectLogin) {
                    login = "unnamed";
                }
                while (login.charAt(0) == ' ')
                    login = login.substring(1);
                textName.setText(login);
                textName.setEnabled(false);
                server = new ServerConnection(login);
                server.connect();
                server.goOnline();
                callLT = new CallListenerThread();
                callLT.start();
                commandLT = new CommandListenerThread();
                apply.setEnabled(false);
                connectButton.setEnabled(true);

                callLT.setNick(login);
        }
        });
    
        this.add(apply);

        connectButton.setText("Connect");
        connectButton.setBounds(475, 400, 100, 30);
        connectButton.setFont(text.getFont().deriveFont(15f));
        connectButton.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 2));

        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (remoteAddr.getText() != "") {
                    String login;
                    login = textName.getText();
                    caller = new Caller(login, remoteAddr.getText());
                    try {
                        connection = caller.call();
                        if (connection != null) {
                            commandLT.setConnection(connection);
                            commandLT.start();
                            connection.sendNick(textName.getText());
                        } else{
                            JOptionPane.showMessageDialog(null,
                                    "Couldn't connect this ip ");
                        }
                    } catch (InterruptedException e1) {

                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
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

        }


    public void addConnectListener(ActionListener act){
        this.connectButton.addActionListener(act);
    }



}
