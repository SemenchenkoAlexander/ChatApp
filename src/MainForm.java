import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;

import java.awt.GridBagLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.sql.Date;
import java.awt.GridBagConstraints;
import java.awt.Color;

import javax.swing.text.BadLocationException;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class MainForm implements Observer {

    private JFrame frame;
    private JTextField localLog;
    private JTextField remoteLog;
    private JTextField remoteAddr;
    private JTextField msg;
    private CallListenerThread callListenerThread;
    public static MainForm window;
    private DefaultListModel<Object> dlm;
    private JList<Object> list;
    private Connection connection;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    window = new MainForm();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainForm() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setMinimumSize(new Dimension(454, 432));
        frame.setMinimumSize(new Dimension(470, 470));
        frame.setBounds(100, 100, 470, 470);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setName("panel");
        panel.setBackground(new Color(255, 255, 255));
        panel.setOpaque(false);
        panel.setBounds(25, 25, 404, 382);
        frame.getContentPane().add(panel);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
        panel.add(panel_1, BorderLayout.NORTH);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 75, 75, 13, 80, 75, 75, 0 };
        gbl_panel_1.rowHeights = new int[] { 20, 20, 0 };
        gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
                0.0, Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
        panel_1.setLayout(gbl_panel_1);

        JLabel lblNewLabel = new JLabel("local login");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel_1.add(lblNewLabel, gbc_lblNewLabel);

        localLog = new JTextField();
        GridBagConstraints gbc_localLog = new GridBagConstraints();
        gbc_localLog.fill = GridBagConstraints.BOTH;
        gbc_localLog.insets = new Insets(0, 0, 5, 5);
        gbc_localLog.gridx = 1;
        gbc_localLog.gridy = 0;
        panel_1.add(localLog, gbc_localLog);
        localLog.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("remote login");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 3;
        gbc_lblNewLabel_1.gridy = 0;
        panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

        remoteLog = new JTextField();
        GridBagConstraints gbc_remoteLog = new GridBagConstraints();
        gbc_remoteLog.fill = GridBagConstraints.BOTH;
        gbc_remoteLog.insets = new Insets(0, 0, 5, 5);
        gbc_remoteLog.gridx = 4;
        gbc_remoteLog.gridy = 0;
        panel_1.add(remoteLog, gbc_remoteLog);
        remoteLog.setColumns(10);

        JButton disconnectBut = new JButton("Disconnect");
        GridBagConstraints gbc_DisconBut = new GridBagConstraints();
        gbc_DisconBut.fill = GridBagConstraints.BOTH;
        gbc_DisconBut.insets = new Insets(0, 0, 5, 0);
        gbc_DisconBut.gridx = 5;
        gbc_DisconBut.gridy = 0;
        panel_1.add(disconnectBut, gbc_DisconBut);

        JButton applyBut = new JButton("Apply");
        GridBagConstraints gbc_applyBut = new GridBagConstraints();
        gbc_applyBut.fill = GridBagConstraints.BOTH;
        gbc_applyBut.insets = new Insets(0, 0, 0, 5);
        gbc_applyBut.gridx = 0;
        gbc_applyBut.gridy = 1;
        panel_1.add(applyBut, gbc_applyBut);

        JLabel lblNewLabel_2 = new JLabel("remote addr");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 1;
        panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

        remoteAddr = new JTextField();
        GridBagConstraints gbc_remoteAddr = new GridBagConstraints();
        gbc_remoteAddr.insets = new Insets(0, 0, 0, 5);
        gbc_remoteAddr.fill = GridBagConstraints.BOTH;
        gbc_remoteAddr.gridx = 4;
        gbc_remoteAddr.gridy = 1;
        panel_1.add(remoteAddr, gbc_remoteAddr);
        remoteAddr.setColumns(10);

        JButton connectBut = new JButton("Connect");
        GridBagConstraints gbc_connectBut = new GridBagConstraints();
        gbc_connectBut.fill = GridBagConstraints.BOTH;
        gbc_connectBut.gridx = 5;
        gbc_connectBut.gridy = 1;
        panel_1.add(connectBut, gbc_connectBut);

        JPanel panel_2 = new JPanel();
        panel_2.setOpaque(false);
        panel.add(panel_2, BorderLayout.SOUTH);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[] { 331, 75, 0 };
        gbl_panel_2.rowHeights = new int[] { 23, 0 };
        gbl_panel_2.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
        gbl_panel_2.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel_2.setLayout(gbl_panel_2);

        msg = new JTextField();
        GridBagConstraints gbc_msg = new GridBagConstraints();
        gbc_msg.fill = GridBagConstraints.BOTH;
        gbc_msg.insets = new Insets(0, 0, 0, 5);
        gbc_msg.gridx = 0;
        gbc_msg.gridy = 0;
        panel_2.add(msg, gbc_msg);
        msg.setColumns(10);

        final JButton sendBut = new JButton("Send");
        GridBagConstraints gbc_sendBut = new GridBagConstraints();
        gbc_sendBut.fill = GridBagConstraints.BOTH;
        gbc_sendBut.gridx = 1;
        gbc_sendBut.gridy = 0;
        panel_2.add(sendBut, gbc_sendBut);

        JPanel panel_3 = new JPanel();
        panel_3.setOpaque(false);
        panel_3.setBorder(new EmptyBorder(5, 0, 5, 0));
        panel.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel_3.add(scrollPane, BorderLayout.CENTER);

        list = new JList<Object>();
        scrollPane.setViewportView(list);

        frame.getContentPane().addComponentListener(new ComponentListener() {

            public void componentResized(ComponentEvent e) {
                panel.setSize(frame.getContentPane().getWidth() - 50, frame
                        .getContentPane().getHeight() - 50);

            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentShown(ComponentEvent e) {

            }

            public void componentHidden(ComponentEvent e) {

            }
        });

        frame.addWindowStateListener(new WindowStateListener() {

            public void windowStateChanged(WindowEvent e) {
                panel.setSize(frame.getWidth() - 66, frame.getHeight() - 88);
            }

        });

        dlm = new DefaultListModel<Object>();
        sendBut.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                if ((localLog.getText().equals(""))
                        || (remoteLog.getText().equals(""))
                        || (remoteAddr.getText().equals(""))) {
                    JOptionPane.showMessageDialog(frame,
                            "Not enough data for sending the message");
                } else {
                    String name = new String();
                    if (localLog.getText().length() > 10) {
                        try {
                            name = localLog.getText(0, 10);
                        } catch (BadLocationException ignore) {
                        }
                        name = name + "...";
                    } else
                        name = localLog.getText();
                    long date = System.currentTimeMillis();
                    dlm.addElement("<html>" + name + " "
                            + new Date(date).toLocaleString() + ":<br>"
                            + msg.getText() + " </span></html>");
                    list.setModel(dlm);

                    try {
                        connection.sendMessage(msg.getText());
                        System.out.println("Sended");
                    } catch (IOException ex) {
                        System.out.println("No internet connection");
                    }

                }
                msg.setText("");
                msg.requestFocus();
            }
        });

        msg.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendBut.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {

            }

            @Override
            public void keyTyped(KeyEvent arg0) {

            }
        });

        applyBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (callListenerThread == null) {
                    System.out.println("Added obs");
                    callListenerThread = new CallListenerThread(
                            new CallListener(localLog.getText()));
                    callListenerThread.addObserver(window);
                } else {
                    callListenerThread.setLocalNick(localLog.getText());
                }
            }
        });

        connectBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Caller caller = new Caller(localLog.getText(), remoteAddr
                        .getText());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connection = caller.call();

                            if (caller.getCallStatus().toString().equals("OK"))
                                remoteLog.setText(caller.getRemoteNick());
                            else if (caller.getCallStatus().toString()
                                    .equals("BUSY")) {
                                JOptionPane.showMessageDialog(frame, "User "
                                        + caller.getRemoteNick() + " is busy");
                            } else {
                                JOptionPane.showMessageDialog(frame, "User "
                                        + caller.getRemoteNick()
                                        + " has declined your call.");
                                connection = null;
                            }

                        } catch (Exception ex) { // Show message that remote
                            // user is offline or wrong
                            // ip
                            JOptionPane
                                    .showMessageDialog(
                                            frame,
                                            "Connection error. User with ip does not exist or there is no Internet connection");
                            connection = null;
                        }
                    }
                }).start();
            }

        });

        disconnectBut.addActionListener(e -> {
            if (connection != null)
                try {
                    remoteLog.setText("");
                    remoteAddr.setText("");
                    connection.disconnect();
                    if (callListenerThread != null)
                        callListenerThread.setBusy(false);

                } catch (IOException ignored) {
                }
        });
    }

    public boolean question(String nick, String remoteAddress) {
        Object[] options = { "Receive", "Reject" };
        int dialogResult = JOptionPane.showOptionDialog(frame, "User " + nick
                + " with ip " + remoteAddress
                + " is trying to connect with you", "Recieve connection",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.out.println("Receive");
            remoteLog.setText(nick);
            remoteAddr.setText(remoteAddress);
            return true; // Receive
        }
        System.out.println("Rejected");
        return false; // Reject

    }

    @SuppressWarnings("deprecation")
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof CallListener) {
            CallListener c = (CallListener) arg;
            callListenerThread.suspend();
            callListenerThread.setReceive(question(c.getRemoteNick(),
                    c.getRemoteAddress()));
            callListenerThread.resume();
        } else if (arg instanceof Connection) {
            connection = (Connection) arg;
            System.out.println("Output connection created");
        } else {
            System.out.println("Receive message");
            System.out.println(arg.toString());
            Command command = (Command) arg;
            System.out.println(command.toString());

            if (command instanceof MessageCommand) {
                dlm.addElement("<html>" + remoteLog.getText() + " "
                        + new Date(System.currentTimeMillis()).toLocaleString()
                        + ":<br>" + arg.toString() + " </span></html>");
                list.setModel(dlm);
            }
            else
            if (command.toString().toLowerCase().equals("disconnect")) {
                remoteLog.setText("");
                remoteAddr.setText("");
                if (callListenerThread != null)
                    callListenerThread.setBusy(false);
            }
        }
    }
}