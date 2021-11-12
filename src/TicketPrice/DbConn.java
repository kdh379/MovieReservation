package TicketPrice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConn {
	//로컬18c url
		static final String ORACLE_LOCAL = "jdbc:oracle:thin:@localhost:1521:xe";
		
		public Connection getLocalOracle() {
			Connection conn=null;
			try {
				conn=DriverManager.getConnection(ORACLE_LOCAL, "dohyeon", "a1234");
				System.out.println("로컬 오라클 연결 성공");
			} catch (SQLException e) {
				System.out.println("로컬 오라클 연결 실패"+e.getMessage());
			}
			return conn;
		}
		
		//close 메소드
		public void dbClose(ResultSet rs, Statement stmt, Connection conn) {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void dbClose(Statement stmt, Connection conn) {
			try {
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void dbClose(PreparedStatement pstmt, Connection conn) {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
