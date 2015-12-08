/**
 * Created by Alexander on 03.12.15.
 */
import java.io.IOException;
import java.util.Observable;
/*
CLASS THAT IS LOOKING FORWARD FOR AN INCOMING CONNECTION
 */
public class CallListenerThread extends Observable implements Runnable {
    private CallListener callListener;

    public CallListenerThread(CallListener callListener){
        this.callListener = callListener;
    }

    public CallListenerThread() {

    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()){
            try{
                Connection connection = callListener.getConnection();
                if (callListener.isBusy()){
                    connection.sendNick(Const.DEFAULT_VER);
                }
                else {
                    connection.sendNick(Const.DEFAULT_VER);
                    setChanged();
                    notifyObservers();
                    clearChanged();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void setBusy(boolean busy){
        this.callListener.setBusy(busy);
    }
    public Connection getLastCon(){
        return this.callListener.getLastCon();
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    };
    public void setNick(String localNick) {
        callListener.setNick(localNick);
    }
}