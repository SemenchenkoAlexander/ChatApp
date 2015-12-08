/**
 * Created by Alexander on 27.11.15.
 */
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Connection  {
    private Socket socket;
    private PrintWriter printer;
    private PrintStream outStream;
    private Scanner inStream;
    private String nickname;

    public Connection(Socket socet) {
        this.socket = socet;
    }

    public Connection(Socket socet, String nickname) throws IOException {
        this.socket = socet;
        outStream = new PrintStream(socet.getOutputStream(),true, Const.ENCODING);
        inStream = new Scanner(socet.getInputStream());
        this.nickname = nickname;

    }

    public void sendNick(String nick) throws UnsupportedEncodingException, IOException {
        outStream.println("ChatApp 2015 user " + nick);
    }
    public void sendMessage(final String message) throws UnsupportedEncodingException, IOException {
        outStream.println("Message");
        outStream.println(message);
    }

    public void accept() throws IOException {
        outStream.println("Accepted");
    }

    public void reject() throws IOException {
        outStream.println("Rejected");
        outStream.close();
    }


    public void disconnect() throws IOException {
        outStream.println("Disconnect");
        outStream.close();
        socket.close();
    }

    public Command receive() throws IOException {
        String str;
        str=inStream.nextLine();
        if (str.toUpperCase().startsWith("CHATAPP 2015 USER")) {
            Scanner in = new Scanner(str);
            in.next();

        } else if (str.toUpperCase().equals("MESSAGE")) {
            str=inStream.nextLine();

        } else {
            str = str.toUpperCase().replaceAll("[\r\n]","");
            for (Command.CommandType cc : Command.CommandType.values())
                if (cc.toString().equals(str))
                    return new Command(Command.CommandType.valueOf(str.replaceAll("ED", "")));
        }
        return new Command(Command.CommandType.NULL);
    }

}