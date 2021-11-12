package TicketPrice;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MovieSeatForm extends JFrame implements ItemListener, ActionListener{

	ImageIcon screenImage;
	JLabel lblscreen;
	JCheckBox[][] seat = new JCheckBox[8][12];
	JLabel[] seatNumRow;
	JLabel[] seatNumCol;
	JLabel infoPersons, infoPrice, infoSeatNum, lblPersons, lblPrice, lblSeatNum;
	JButton btnCheck;
	String result,check;
	int cnt = 0;
	int total = 0;
	MovieAddForm add;
	
	public MovieSeatForm(MovieAddForm addForm) {
		super("ÀÎ¿ø ¹× ÁÂ¼® ¼±ÅÃ");
		add = addForm;
		this.setBounds(1000, 100, 700, 400);
		this.initDesign();
		
		this.getContentPane().setBackground(Color.WHITE);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setVisible(true);
		this.setResizable(false);
	}
	
	public void initDesign() {
		this.setLayout(null);
		String imagepath = "C:\\sist0730\\image_Movie\\";
		
		screenImage = new ImageIcon(imagepath+"Screen.jpg");
		lblscreen = new JLabel(screenImage);
		lblscreen.setBounds(5, 0, 653, 25);
		this.add(lblscreen);
		
		int xpos=105;
		int ypos=40;
		
		seatNumCol = new JLabel[8];
		for(int i=0; i<seatNumCol.length; i++) {
			seatNumCol[i] = new JLabel();
			seatNumCol[i].setText(Character.toString(65+i));
			seatNumCol[i].setBounds(80, ypos, 20, 20);
			ypos+=25;
			this.add(seatNumCol[i]);
		}
		
		seatNumRow = new JLabel[12];
		for(int i=0; i<seatNumRow.length; i++) {
			seatNumRow[i] = new JLabel();
			seatNumRow[i].setText(String.valueOf(i+1));
			seatNumRow[i].setBounds(xpos, 240, 20, 20);
			xpos += 40;
			this.add(seatNumRow[i]);
		}
		
		infoSeatNum = new JLabel("ÁÂ¼®¹øÈ£");
		infoSeatNum.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		infoSeatNum.setBounds(50, 275, 100, 20);
		this.add(infoSeatNum);
		
		lblSeatNum = new JLabel();
		lblSeatNum.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		lblSeatNum.setBounds(110, 275, 200, 20);
		this.add(lblSeatNum);
		
		infoPersons = new JLabel("ÀÎ¿ø¼ö");
		infoPersons.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		infoPersons.setBounds(50, 300, 100, 20);
		this.add(infoPersons);
		
		lblPersons = new JLabel();
		lblPersons.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		lblPersons.setBounds(110, 300, 200, 20);
		this.add(lblPersons);
		
		infoPrice = new JLabel("°¡°Ý");
		infoPrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		infoPrice.setBounds(50, 325, 100, 20);
		this.add(infoPrice);
		
		lblPrice = new JLabel();
		lblPrice.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		lblPrice.setBounds(110, 325, 100, 20);
		this.add(lblPrice);
		
		
		btnCheck = new JButton("È®ÀÎ");
		btnCheck.setBounds(500, 275, 80, 80);
		btnCheck.addActionListener(this);
		this.add(btnCheck);
		
		xpos = 100;
		ypos = 40;
		for(int i=0; i<8; i++) {
			for(int j=0; j<12; j++) {
				seat[i][j] = new JCheckBox();
				seat[i][j].setBounds(xpos, ypos, 20, 20);
				seat[i][j].addItemListener(this);
				xpos += 40;
				this.add(seat[i][j]);
			}
			xpos=100;
			ypos+=25;
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		
		for(int i=0; i<seat.length; i++) {
			for(int j=0; j<seat[i].length; j++) {
				if(ob==seat[i][j]) {
					check = Character.toString((char)65+i)+(j+1);
					if(seat[i][j].isSelected()==true) {
						cnt++;
						result = check + "," + lblSeatNum.getText();
						result = result.replaceAll(",$", "");
						lblSeatNum.setText(result);
						lblPersons.setText(String.valueOf(cnt));
					}
					else if(seat[i][j].isSelected()==false) {
						result = result.replaceAll(check, "");
						result = result.replaceAll(",$", "");
						lblSeatNum.setText(result);
						cnt--;
						lblPersons.setText(String.valueOf(cnt));
						lblPrice.setText(String.valueOf(total)+"¿ø");
					}
					total = 12000*cnt;
					lblPrice.setText(String.valueOf(total)+"¿ø");
				}
			}	
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		SeatDTO dto = new SeatDTO();
		//add = new MovieAddForm();
		if(ob==btnCheck) {
			add.infoPersons.setText(String.valueOf(cnt));
			add.infoSeatNum.setText(result);
			add.infoPrice.setText(String.valueOf(total));
			
			this.setVisible(false);
		}
	}
	
//	public static void main(String[] args) {
//		new MovieSeatForm();
//	}

}
