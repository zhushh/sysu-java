import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener {
	private JFrame frame;
	private JTextField[] fields;
	private JButton[] buttons;

	public Calculator() {
		int width = 80;
		int firstLine_y = 0;
		int secondLine_y = 80;
		frame = new JFrame("Easy Calculator");
		fields = new JTextField[5];
		buttons = new JButton[5];

		String[] btNames = {"+", "-", "*", "/", "OK"};
		for (int i = 0; i < 5; i++) {
			fields[i] = new JTextField();
			fields[i].setBounds(i*width, firstLine_y, 80, 80);
			fields[i].setHorizontalAlignment(JTextField.CENTER);
			buttons[i] = new JButton(btNames[i]);
			buttons[i].setBounds(i*width, secondLine_y, 80, 80);
		}
		fields[3].setText("=");

		init();
		setFontAndColor();
		addEventHandler();
	}

	public void init() {
		frame.setLayout(new GridLayout(2, 5));
		for (int i = 0; i < 5; i++) {
			frame.add(fields[i]);
		}
		for (int i = 0; i < 5; i++) {
			frame.add(buttons[i]);
		}
	}

	public void setFontAndColor() {
		fields[1].setFont(new Font("宋体", Font.BOLD, 20));
		fields[3].setFont(new Font("宋体", Font.BOLD, 20));
		fields[4].setFont(new Font("宋体", Font.BOLD, 20));

		fields[1].setBackground(Color.BLACK);
		fields[3].setBackground(Color.BLACK);
		fields[4].setBackground(Color.BLACK);

		fields[1].setBackground(Color.GRAY);
		fields[3].setBackground(Color.GRAY);
		fields[4].setBackground(Color.GRAY);
	}

	public void addEventHandler() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(this);
		}
	}

	private String m = null;
	private String n = null;
	private String op = null;

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("OK")) {
			m = fields[0].getText();
			n = fields[2].getText();

			char ss = op.charAt(0);
			switch (ss) {
				case '+': fields[4].setText(Double.parseDouble(m) + Double.parseDouble(n) + ""); break;
				case '-': fields[4].setText(Double.parseDouble(m) - Double.parseDouble(n) + ""); break;
				case '*': fields[4].setText(Double.parseDouble(m) * Double.parseDouble(n) + ""); break;
				case '/': fields[4].setText(Double.parseDouble(m) / Double.parseDouble(n) + ""); break;
				default: break;
			}
		} else {
			op = str;
			fields[1].setText(op);
		}
	}

	public void showMe() {
		frame.pack();
		// 自定义窗体大小
		frame.setSize(400, 160);
		// 设置窗体的启动位置
		frame.setLocation(500, 400);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
	}

	public static void main(String[] args) {
		new Calculator().showMe();
	}
}
