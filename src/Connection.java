/**
 * Created by Alexander on 27.11.15.
 */
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Connection  {
    private Socket socket;
    private PrintWriter printer;

    public Connection(Socket socet) {
        this.socket = socet;
    }

    public void sendNick(String ver,String nick, boolean busy){
        StringBuilder line = new StringBuilder();
        line.append(ver + " user " + nick);
        if (busy)
            line.append(" busy");
        this.printer.print(line + "\n");
        this.printer.flush();
    }

}