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
		btnAdd = new JButton("영화 예매");
		btnDel = new JButton("예매 취소");
		btnCheck = new JButton("예메 내역");
		btnUpdate = new JButton("예매 수정");
		
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
			String num = JOptionPane.showInputDialog(btnCheck, "예매 번호");	
			ReservedDetail res = new ReservedDetail(num);
			}catch(ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(btnCheck, "예매 내역을 찾을 수 없습니다.");
				return;
			}
		}
	}

	public static void main(String[] args) {
		new MovieMain();
	}

}
