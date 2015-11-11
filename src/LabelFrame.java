import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class LabelFrame extends JFrame  {
	
	JPanel panel = new JPanel();	
	JButton button1;
	JButton button2;
	JLabel lable1;
	JLabel lable2;
	JTextField textfieldlogin;
	JPasswordField textfieldpassword;
	JTextField textfield3; 
	JTextArea textarea;
	String name = "Artur";
	String password = "123456789";
	String name1;
	String password1;
	
	LabelFrame(){
		
		Font font = new Font("Verdana", Font.BOLD, 13);
		
		Color col = new Color(90, 75, 80);
		
		String[] elements = new String[] {"Саша", "Леша", "Макс"};
				
		lable1 = new JLabel("Логин");
		lable1.setFont(font);
		lable1.setForeground(Color.blue);
		lable1.setBounds(45, 40, 60, 30);
				
		lable2 = new JLabel(name1);
		lable2.setFont(font);
		lable2.setForeground(Color.red);
		lable2.setBounds(45, 90, 115, 30);
		
		textfieldlogin = new JTextField();
		textfieldlogin.setBounds(125, 40, 115, 30);
		
		textfieldpassword = new JPasswordField();
		textfieldpassword.setBounds(125, 90, 115, 30);
			
		
		JComboBox combo  = new JComboBox(elements);
		combo.setBounds(350, 40, 140, 30);
		
		button2 = new JButton("Войти");
		butto
		button2.setFont(font);
		button2.addActionListener(new ActionListener( ) {
        	public void actionPerformed(ActionEvent ae) {
        		name1 = lable1.getText();
              }});

		
		JTextField textField = new JTextField();
		
		textField.setCaretColor(Color.RED);
		textField.setHorizontalAlignment(JTextField.LEFT);
		
		JTextArea textArea = new JTextArea(5, 20);
		textArea.setLineWrap(true);//пособность переносить на другие линии!
		textArea.setWrapStyleWord(true);//способность переносить слова на соседнюю строку целиком
		//textArea.setFont(font);
		//Управление вводимого текста 
		textField.setBounds(45, 370, 500, 75);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		button1 = new JButton("Отправить");
		button1.setLocation(560, 414);
		button1.setSize(100, 30);
		button1.setBackground(Color.green);
				
		panel.add(lable1);
		panel.add(lable2);
		panel.add(textfieldlogin);
		panel.add(textfieldpassword);
		panel.add(button2);
		panel.add(combo);
		panel.add(textField);
		panel.add(textArea);
		panel.add(button1);
		this.add(panel);
		
	}
}