package TicketPrice;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReservedDetail extends JFrame implements ActionListener{
	
	ImageDraw draw = new ImageDraw();
	int movieNum;
	String posterName;
	JLabel lblReservedNum, lblMovieTitle, lblScreeningdate, lblPersons, lblSeatnum, lblPrice;
	JLabel lblTheater, lblRoomNum, lblOrderplaced;
	JLabel info1, info2, info3, info4, info5, info6, info7;
	ImageIcon imageCheckCancel;
	Vector<TicketDTO> ticketList = new Vector<TicketDTO>();
	Vector<MovieDTO> movieList = new Vector<MovieDTO>();
	MovieDBModel dbModel = new MovieDBModel();
	JButton btnCancel;

	public ReservedDetail(String num) {
		super("¿¹¸Å ³»¿ª");
		this.setBounds(1000, 100, 700, 400);
		this.initDesign(num);
		this.getContentPane().setBackground(Color.WHITE);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void initDesign(String num) {
		this.setLayout(null);
		System.out.println("¿¹¾à¹øÈ£: " + num);
		String imagepath = "C:\\sist0730\\image_Movie\\";
		ticketList = dbModel.getTicketData(num);
		movieList = dbModel.getMovieData();
		movieNum = ticketList.get(0).getMovieNum();
		posterName = dbModel.getPosterName(num);
		
		draw.setBounds(10, 10, 200, 360);
		this.add(draw);
		
		info1 = new JLabel("¿¹¸Å¹øÈ£ :");
		info1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		info1.setBounds(500, 10, 100, 25);
		this.add(info1);
		
		lblReservedNum = new JLabel(String.valueOf(ticketList.get(0).getNum()));
		lblReservedNum.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lblReservedNum.setBounds(600, 10, 200, 25);
		this.add(lblReservedNum);
		
		lblMovieTitle = new JLabel(dbModel.getMovieName(num));
		lblMovieTitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lblMovieTitle.setBounds(230, 10, 400, 25);
		this.add(lblMovieTitle);
		
		info2 = new JLabel("°ü¶÷ ÀÏ½Ã");
		info2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		info2.setBounds(230, 70, 100, 20);
		this.add(info2);
		
		lblScreeningdate = new JLabel(ticketList.get(0).getScreeningDate());
		lblScreeningdate.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		lblScreeningdate.setBounds(300, 70, 300, 20);
		this.add(lblScreeningdate);
		
		info3 = new JLabel("°ü¶÷ ±ØÀå");
		info3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		info3.setBounds(230, 100, 100, 20);
		this.add(info3);
		
		lblTheater = new JLabel(ticketList.get(0).getTheater());
		lblTheater.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		lblTheater.setBounds(300, 100, 300, 20);
		this.add(lblTheater);
		
		info4 = new JLabel("»ó¿µ°ü");
		info4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		info4.setBounds(500, 70, 100, 20);
		this.add(info4);
		
		lblRoomNum = new JLabel(ticketList.get(0).getRoomNum());
		lblRoomNum.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		lblRoomNum.setBounds(580, 70, 100, 20);
		this.add(lblRoomNum);
		
		info5 = new JLabel("°ü¶÷ ÁÂ¼®");
		info5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		info5.setBounds(500, 100, 100, 20);
		this.add(info5);
		
		lblSeatnum = new JLabel(ticketList.get(0).getSeatNum());
		lblSeatnum.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		lblSeatnum.setBounds(580, 100, 100, 20);
		this.add(lblSeatnum);
		
		info6 = new JLabel("°áÁ¦ ³¯Â¥");
		info6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		info6.setBounds(230, 150, 100, 20);
		this.add(info6);
		
		lblOrderplaced = new JLabel(String.valueOf(ticketList.get(0).getOrderPlaced()));
		lblOrderplaced.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		lblOrderplaced.setBounds(300, 150, 200, 20);
		this.add(lblOrderplaced);
		
		imageCheckCancel = new ImageIcon(imagepath+"CheckCancel.jpg");
		btnCancel = new JButton(imageCheckCancel);
		btnCancel.setBounds(590, 325, 61, 24);
		btnCancel.addActionListener(this);
		this.add(btnCancel);
	}
	
	class ImageDraw extends Canvas {
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			
			if(posterName!=null)
			{
				Image image=new ImageIcon(posterName).getImage();
				g.drawImage(image, 0, 0, 200, 340, this);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		
		if(ob==btnCancel) {
			dbModel.deleteTicket(ticketList.get(0).getNum());
			this.setVisible(false);
		}
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new ReservedDetail(num);
//	}

}
