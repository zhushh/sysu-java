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

public class SimpleCalculator implements ActionListener 
{
	private JFrame frame;
	private JTextField field;
	private JButton[] allButtons;
	private JButton clearButton;

	public SimpleCalculator() {
		frame = new JFrame("SymbolCalculator v1.1");
		field = new JTextField(25);
		allButtons = new JButton[16];
		String str = "123+456-789*0.=/";
		for (int i = 0; i < 16; i++) {
			allButtons[i] = new JButton(str.substring(i,i+1));
		}
		clearButton = new JButton("CLEAR");

		// call init
		init();
		// set textfield font and color
		setFontAndColor();
		addEventHandler();
	}

	public void addEventHandler() {
		for (int i = 0; i < allButtons.length; i++) {
			allButtons[i].addActionListener(this);
			clearButton.addActionListener(this);
		}
	}

	private String op = null;
	private String m = null;
	private String n = null;
	private boolean cls = false;

	private void setResult() {
		if (field.getText().equals("")) {
			cls = true;
			m = null;
			n = null;
		} else if (m != null) {
			n = field.getText();

			char ss = op.charAt(0);
			switch (ss) {
				case '+': field.setText((Double.parseDouble(m) + Double.parseDouble(n)) + ""); break;
				case '-': field.setText((Double.parseDouble(m) - Double.parseDouble(n)) + ""); break;
				case '*': field.setText((Double.parseDouble(m) * Double.parseDouble(n)) + ""); break;
				case '/': field.setText((Double.parseDouble(m) / Double.parseDouble(n)) + ""); break;
				default: break;
			}

			cls = true;
			m = null;
			n = null;
		}
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();	// get the clicked button
		if ("0123456789".indexOf(str) != -1) {
			if (cls) {
				field.setText("");
				cls = false;
			}
			field.setText(field.getText() + str);
		} else if ("+-*/".indexOf(str) != -1) {
			if (field.getText().equals("")) {
				op = str;
			} else if (m == null) {
				m = field.getText();
				op = str;
				field.setText("");
			} else if (m != null) {
				setResult();
				m = field.getText();
				op = str;
			}
		} else if (str.equals("=")) {
			setResult();
		} else if (str.equals("CLEAR")) {
			field.setText("");
			m = null;
			n = null;
		}
	}

	public void init() {
		frame.setLayout(new BorderLayout());
		JPanel northPannel = new JPanel();
		JPanel centerPannel = new JPanel();
		JPanel southPannel = new JPanel();

		northPannel.setLayout(new FlowLayout());
		centerPannel.setLayout(new GridLayout(4,4));
		southPannel.setLayout(new FlowLayout());

		northPannel.add(field);
		southPannel.add(clearButton);
		for (int i = 0; i < allButtons.length; i++) {
			centerPannel.add(allButtons[i]);
		}

		frame.add(northPannel, BorderLayout.NORTH);
		frame.add(southPannel, BorderLayout.SOUTH);
		frame.add(centerPannel, BorderLayout.CENTER);
	}

	public void setFontAndColor() {
		field.setFont(new Font("宋体", Font.BOLD, 16));
		field.setForeground(Color.PINK);
		field.setBackground(new Color(0x2a, 0x3b, 0x4f));
	}

	public void showMe() {
		frame.pack();
		// 自定义窗体大小
		frame.setSize(400, 300);
		// 设置窗体的启动位置
		frame.setLocation(500, 400);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
	}

	public static void main(String[] args) {
		new SimpleCalculator().showMe();
	}
}