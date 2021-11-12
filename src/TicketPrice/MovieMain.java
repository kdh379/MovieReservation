package TicketPrice;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MovieMain extends JFrame implements ActionListener{
	
	JButton btnAdd, btnDel, btnCheck, btnUpdate;
	
	public MovieMain() {
		super("Movie");
		this.setBounds(800, 100, 250, 300);
		this.initDesign();
		
		this.getContentPane().setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void initDesign() {
		this.setLayout(new GridLayout(4, 1));
		btnAdd = new JButton("��ȭ ����");
		btnDel = new JButton("���� ���");
		btnCheck = new JButton("���� ����");
		btnUpdate = new JButton("���� ����");
		
		this.add(btnAdd);
		this.add(btnDel);
		this.add(btnCheck);
		this.add(btnUpdate);
		
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnCheck.addActionListener(this);
		btnUpdate.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		
		
		if(ob==btnAdd) {
			MovieAddForm add = new MovieAddForm();
			//add.setVisible(true);
		}
		else if(ob==btnCheck) {
			try {
			String num = JOptionPane.showInputDialog(btnCheck, "���� ��ȣ");	
			ReservedDetail res = new ReservedDetail(num);
			}catch(ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(btnCheck, "���� ������ ã�� �� �����ϴ�.");
				return;
			}
		}
	}

	public static void main(String[] args) {
		new MovieMain();
	}

}
