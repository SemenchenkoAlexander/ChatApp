/**
 * Created by Alexander on 27.11.15.
 */
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Connection  {
    private Socket socket;

    public Connection(Socket socet) {
        this.socket = socet;
    }

}