package TicketPrice;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MovieAddForm extends JFrame implements ActionListener{
	
	DbConn db = new DbConn();
	MovieDBModel dbModel = new MovieDBModel();
	JLabel lblMovieList, lblTheaterList, lblDateList, lblTimeList;
	JLabel lblMovieTitle;
	JLabel infoTitle, infoRatedPg, infoTheater, infoDate, infoRoomNum, infoPersons, infoSeatNum, infoPrice;
	JLabel info1, info2, info3, info4, info5, info6;
	ImageIcon imageMovieList, imageTheaterList, imageDateList, imageTimeList;
	ImageDraw draw = new ImageDraw();
	ImageIcon imageSeat;
	ImageIcon imagePayment;
	JButton btnSeat, btnPayment;
	String posterName;
	JList listMovie, listTheater, listDate, listSchedule;
	JPanel info;
	Vector<MovieDTO> movieList;
	Vector<TheaterDTO> thList;
	Vector<SeatDTO> seatList;
	Vector<ScheduleDTO> scList;
	Vector<TicketDTO> ticketList = new Vector<TicketDTO>();
	int selMovie, selDate, selSchdule;
	int cnt = 1045;
	
	
	public MovieAddForm() {
		super("¿µÈ­ ¿¹¸Å");
		this.setBounds(1000, 100, 1012, 800);
		this.initDesign();
		
		this.getContentPane().setBackground(Color.WHITE);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void initDesign() {
		this.setLayout(null);
		String imagepath = "C:\\sist0730\\image_Movie\\";
		
		imageMovieList = new ImageIcon(imagepath+"lblMovie.png");
		lblMovieList = new JLabel(imageMovieList);
		lblMovieList.setBounds(2, 10, 284, 33);
		this.add(lblMovieList);
		
		imageTheaterList = new ImageIcon(imagepath+"lblTheater.png");
		lblTheaterList = new JLabel(imageTheaterList);
		lblTheaterList.setBounds(288, 10, 265, 33);
		this.add(lblTheaterList);
		
		imageDateList = new ImageIcon(imagepath+"lblDate.png");
		lblDateList = new JLabel(imageDateList);
		lblDateList.setBounds(555, 10, 91, 33);
		this.add(lblDateList);
		
		imageTimeList = new ImageIcon(imagepath+"lblTime.png");
		lblTimeList = new JLabel(imageTimeList);
		lblTimeList.setBounds(648, 10, 346, 33);
		this.add(lblTimeList);
		
		info = new JPanel();
		
		info.setBackground(new Color(64, 64, 64));
		info.setBounds(0, 500, 1012, 260);
		info.isOpaque();
		//this.add(info);
		
		infoTitle = new JLabel();
		infoTitle.setBounds(160, 575, 200, 20);
		infoTitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		this.add(infoTitle);
		
		infoRatedPg = new JLabel();
		infoRatedPg.setBounds(160, 600, 200, 20);
		infoRatedPg.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		this.add(infoRatedPg);
		
		infoTheater = new JLabel();
		infoTheater.setBounds(350, 575, 100, 20);
		infoTheater.setFont((new Font("¸¼Àº °íµñ", Font.BOLD, 13)));
		this.add(infoTheater);
		
		infoDate = new JLabel();
		infoDate.setBounds(350, 600, 200, 20);
		infoDate.setFont((new Font("¸¼Àº °íµñ", Font.BOLD, 13)));
		this.add(infoDate);
		
		infoRoomNum = new JLabel();
		infoRoomNum.setBounds(350, 625, 100, 20);
		infoRoomNum.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		this.add(infoRoomNum);
		
		infoPersons = new JLabel();
		infoPersons.setBounds(350, 650, 100, 20);
		infoPersons.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		this.add(infoPersons);
		
		infoSeatNum = new JLabel();
		infoSeatNum.setBounds(350, 675, 100, 20);
		infoSeatNum.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		this.add(infoSeatNum);
		
		infoPrice = new JLabel();
		infoPrice.setBounds(350, 700, 100, 20);
		infoPrice.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		this.add(infoPrice);
		
		info1 = new JLabel();
		info1.setBounds(300, 575, 100, 20);
		info1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		this.add(info1);
		
		info2 = new JLabel();
		info2.setBounds(300, 600, 100, 20);
		info2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		this.add(info2);
		
		info3 = new JLabel();
		info3.setBounds(300, 625, 100, 20);
		info3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		this.add(info3);
		
		info4 = new JLabel();
		info4.setBounds(300, 650, 100, 20);
		info4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		this.add(info4);
		
		info5 = new JLabel();
		info5.setBounds(300, 675, 100, 20);
		info5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		this.add(info5);
		
		info6 = new JLabel();
		info6.setBounds(300, 700, 100, 20);
		info6.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		this.add(info6);
		
		
		imageSeat = new ImageIcon(imagepath+"CheckSeat.jpg");
		btnSeat = new JButton(imageSeat);
		btnSeat.setBounds(700, 640, 106, 108);
		btnSeat.addActionListener(this);
		this.add(btnSeat);
		
		imagePayment = new ImageIcon(imagepath+"CheckPayment.jpg");
		btnPayment = new JButton(imagePayment);
		btnPayment.setBounds(850, 640, 106, 108);
		btnPayment.addActionListener(this);
		this.add(btnPayment);
		
		movieList = dbModel.getMovieData();
		String[] movies = new String[movieList.size()];
		for(int i=0; i<movieList.size(); i++)
			movies[i] = movieList.get(i).getTitle();
		listMovie = new JList(movies);
		listMovie.setBounds(2, 50, 284, 500);
		listMovie.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		this.add(listMovie);
		
		thList = dbModel.getTheaterData();
		String[] theaters = new String[thList.size()];
		for(int i=0; i<thList.size(); i++)
			theaters[i] = thList.get(i).getName();
		listTheater = new JList(theaters);
		listTheater.setBounds(288, 50, 265, 500);
		listTheater.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		this.add(listTheater);
		
		LocalDate now;
		String[] date1 = new String[20];
		String[] date2 = new String[20];
		for(int i=0; i<20; i++) {
			now = LocalDate.now().plusDays(i);
			DayOfWeek dayOfWeek = now.getDayOfWeek();
			int dayOfMonth = now.getDayOfMonth();
			System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)+ " " + dayOfMonth);	
			date1[i] = String.valueOf(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)+"  "+ dayOfMonth);
			date2[i] = String.valueOf(now);
		}
		now = LocalDate.now();
		
		
		listDate = new JList(date1);
		listDate.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		JScrollPane js = new JScrollPane(listDate);
		js.setBounds(555, 50, 91, 500);
		this.add(js);
		
		
		seatList = dbModel.getSeatData();
		
		listMovie.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				selMovie = listMovie.getSelectedIndex();
				posterName = movieList.get(selMovie).getPoster();
				
				draw.repaint();
				infoTitle.setText(movieList.get(selMovie).getTitle());
				infoRatedPg.setText(movieList.get(selMovie).getRatedpg());
				
				info1.setText("±ØÀå");
				info2.setText("ÀÏ½Ã");
				info3.setText("»ó¿µ°ü");
				info4.setText("ÀÎ¿ø");
				info5.setText("ÁÂ¼®");
				info6.setText("°¡°Ý");
			}
		});
		
		listTheater.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				int selTheater = listTheater.getSelectedIndex();
				infoTheater.setText(thList.get(selTheater).getName());
			}
		});
		
		listDate.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				selDate = listDate.getSelectedIndex();
				LocalDate now = LocalDate.now();
				//infoDate.setText(date2[sel]);
				infoDate.setText(String.valueOf(now.plusDays(selDate)));
				System.out.println(now.plusDays(selDate));
			}
		});
		
		scList = dbModel.getScheduleData();
		String[] time = new String[scList.size()];
		
		for(int i=0; i<scList.size(); i++) 
			time[i] = scList.get(i).getNum() +" "+ scList.get(i).getScreeningTime();
		
		listSchedule = new JList(time);
		listSchedule.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		listSchedule.setBounds(648, 50, 346, 500);
		this.add(listSchedule);
		System.out.println(time[0]);
		
		listSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				selSchdule = listSchedule.getSelectedIndex();
				infoDate.setText(infoDate.getText() + " " + scList.get(selSchdule).getScreeningTime());
				infoRoomNum.setText(scList.get(selSchdule).getNum());
			}
		});
		
		draw.setBounds(2, 500, 150, 257);
		this.add(draw);
	}
	
	
	
	class ImageDraw extends Canvas {
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			
			if(posterName!=null)
			{
				Image image=new ImageIcon(posterName).getImage();
				g.drawImage(image, 0, 0, 150, 257, this);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		TicketDTO dto = new TicketDTO();
		if(ob==btnSeat) {
			MovieSeatForm seatForm = new MovieSeatForm(this);
			seatForm.setVisible(true);
		}
		else if(ob==btnPayment) {
			dto.setMovieNum(movieList.get(selMovie).getNum());
			dto.setScreeningDate(infoDate.getText());
			dto.setPersons(Integer.parseInt(infoPersons.getText()));
			dto.setSeatNum(infoSeatNum.getText());
			dto.setPrice(Integer.parseInt(infoPrice.getText()));
			dto.setTheater(infoTheater.getText());
			dto.setRoomNum(infoRoomNum.getText());
			
			dbModel.insertTicket(dto);
			ticketList = dbModel.getAllTicketData();
			int num = ticketList.size()-1;
			JOptionPane.showMessageDialog(btnPayment, "¿¹¸Å¹øÈ£:"+ticketList.get(num).getNum());
			this.setVisible(false);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MovieAddForm();
	}

}
