import javax.swing.*;

/**
 * Created by Alexander on 27.11.15.
 */
public class Application {

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controller control = new Controller(new MainForm());
            }
        });

    }
}
