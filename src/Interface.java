import javax.swing.*;
import java.awt.*;

public class Interface {
	
	private static final Integer vusota = 500;
	private static final Integer shurina = 700;
	
	public static void main(String[] args){
		
		LabelFrame frame = new LabelFrame();	
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(shurina, vusota);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("ChatAp");
		frame.setBackground(Color.white);
		frame.setVisible(true);
	}
}
