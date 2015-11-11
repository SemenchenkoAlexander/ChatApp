import java.io.IOException;
import java.net.Socket;

public class Connection {
   private Socket s;

   public Connection(Socket sockt){
        this.s=sockt;
    }

   public void sendNickHello(String nick) throws IOException {
       s.getOutputStream().write(("ChatApp 2015 user "+nick+"\n").getBytes());
   }
   public void sendNickBusy(String nick) throws IOException {
       s.getOutputStream().write(("ChatApp 2015 user " + nick + "busy\n").getBytes());
   }
   public void accept() throws IOException {
       s.getOutputStream().write(("Accepted\n").getBytes());
   }
    public void reject() throws IOException {
        s.getOutputStream().write(("Rejected\n").getBytes());
    }
    public void disconnect() throws IOException {
        s.getOutputStream().write(("Disconnected\n").getBytes());
    }
}
