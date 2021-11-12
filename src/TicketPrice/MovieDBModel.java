package TicketPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MovieDBModel {
	
	DbConn db = new DbConn();
	
	public Vector<MovieDTO> getMovieData() {
		Vector<MovieDTO>list = new Vector<MovieDTO>();
		
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from movie";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MovieDTO dto = new MovieDTO();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setGenre(rs.getString("genre"));
				dto.setDirector(rs.getString("director"));
				dto.setRatedpg(rs.getString("ratedpg"));
				dto.setRunningtime(rs.getString("runningtime"));
				dto.setReleasedate(rs.getDate("releasedate"));
				dto.setPoster(rs.getString("poster"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public Vector<TheaterDTO> getTheaterData() {
		Vector<TheaterDTO> thlist = new Vector<TheaterDTO>();
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from theater";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TheaterDTO dto = new TheaterDTO();
				dto.setName(rs.getString("name"));
				
				thlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
		return thlist;
	}
	
	public Vector<SeatDTO> getSeatData(){
		Vector<SeatDTO> seatlist = new Vector<SeatDTO>();
		
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from seat";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SeatDTO dto = new SeatDTO();
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getString("num"));
				dto.setSeatNum(rs.getString("seatnum"));
				dto.setScreeningTime(rs.getString("screeningtime"));
				dto.setReserved(rs.getInt("reserved"));
				seatlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
		
		
		return seatlist;
	}
	
	public Vector<ScheduleDTO> getScheduleData(){
		Vector<ScheduleDTO> sclist = new Vector<ScheduleDTO>();
		
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from schedule";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ScheduleDTO dto = new ScheduleDTO();
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getString("num"));
				dto.setScreeningDate(rs.getString("screeningDate"));
				dto.setScreeningTime(rs.getString("screeningtime"));
				dto.setMovieNum(rs.getInt("movienum"));
				sclist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return sclist;
	}
	
	public void insertTicket(TicketDTO dto) {
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		
		String sql = "insert into ticket values(seq_ticket.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getMovieNum());
			pstmt.setString(2, dto.getScreeningDate());
			pstmt.setInt(3, dto.getPersons());
			pstmt.setString(4, dto.getSeatNum());
			pstmt.setInt(5, dto.getPrice());
			pstmt.setString(6, dto.getTheater());
			pstmt.setString(7, dto.getRoomNum());
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
	
	public Vector<TicketDTO> getTicketData(String num) {
		Vector<TicketDTO> ticketlist = new Vector<TicketDTO>();
		
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from ticket where num = ?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TicketDTO dto = new TicketDTO();
				dto.setNum(rs.getInt("num"));
				dto.setMovieNum(rs.getInt("movienum"));
				dto.setScreeningDate(rs.getString("screeningdate"));
				dto.setPersons(rs.getInt("persons"));
				dto.setSeatNum(rs.getString("seatnum"));
				dto.setPrice(rs.getInt("price"));
				dto.setOrderPlaced(rs.getDate("orderplaced"));
				dto.setTheater(rs.getString("theater"));
				dto.setRoomNum(rs.getString("roomnum"));
				
				ticketlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return ticketlist;
	}
	
	public Vector<TicketDTO> getAllTicketData() {
		Vector<TicketDTO> ticketlist = new Vector<TicketDTO>();
		
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from ticket order by num asc";
		
		
		try {
			pstmt = conn.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TicketDTO dto = new TicketDTO();
				dto.setNum(rs.getInt("num"));
				dto.setMovieNum(rs.getInt("movienum"));
				dto.setScreeningDate(rs.getString("screeningdate"));
				dto.setPersons(rs.getInt("persons"));
				dto.setSeatNum(rs.getString("seatnum"));
				dto.setPrice(rs.getInt("price"));
				dto.setOrderPlaced(rs.getDate("orderplaced"));
				dto.setTheater(rs.getString("theater"));
				dto.setRoomNum(rs.getString("roomnum"));
				
				ticketlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return ticketlist;
	}
	
	public String getPosterName(String num) {
		String posterName = "";
		
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select poster from movie m, ticket t where m.num = (select movienum from "
				+ "ticket where num = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next())
				posterName = rs.getString("poster");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return posterName;
	}
	
	public String getMovieName(String num) {
		String movieTitle = "";
		
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select title from movie m, ticket t where m.num = (select movienum from "
				+ "ticket where num = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next())
				movieTitle = rs.getString("title");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieTitle;
	}
	
	public void deleteTicket(int num) {
		Connection conn = db.getLocalOracle();
		PreparedStatement pstmt = null;
		
		String sql = "delete ticket where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
}
