package ex1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  

public class View {  
	public static void main(String[] args) {  
		JFrame f=new JFrame();//creating instance of JFrame  
		int width = 800, height = 800;

		
		          
		JButton b=new JButton("Calcula fatorial");//creating instance of JButton  
		b.setBounds(width/2 - 100, height/2 - 20, 200, 40);//x axis, y axis, width, height  
		
		JLabel label = new JLabel("Input: ");
		label.setBounds(width/2 - 200, height/2 - 50, 100, 40);
		
		final JTextField textField = new JTextField("");
		textField.setBounds(width/2 - 200, height/2 - 20, 100, 40);
		
		
		JLabel label2 = new JLabel();
		label2.setBounds(width/2 + 100, height/2 - 50, 200, 40);
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText();
				int resultado;
				try {
					resultado = Controller.calculaFatorial(Integer.parseInt(input));
					label2.setText("Output: "+ resultado);
				} catch (NumberFormatException e2) {
					label2.setText("Input inválido");
				} catch (RuntimeException e1) {
					label2.setText("Erro: número negativo");
				}
			}
        });
		
		
		f.add(b);//adding button in JFrame  
		f.add(label);
		f.add(textField);
		f.add(label2);
		          
		
		f.setSize(width, height);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
		
	}  
} 