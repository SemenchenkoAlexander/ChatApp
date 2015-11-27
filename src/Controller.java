import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexander on 27.11.15.
 */
public class Controller {

    private MainForm frame;

    public Controller(MainForm frame){

        this.frame = frame;

        this.frame.addSendListener(new SendListener());
        this.frame.addConListener(new ConListener());
        this.frame.addDisconListener(new DisconListener());
        this.frame.addApplyListener(new ApplyListener());
    }

    private class SendListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            frame.appendText(frame.getMessage());
        }
    }

    private class ConListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO
        }
    }

    private class DisconListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO
        }
    }

    private class ApplyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO
        }
    }
}
