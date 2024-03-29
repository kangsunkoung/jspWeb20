package customer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import customer.db.DBCon;
import customer.vo.Notice;

public class NoticeDao {
	public void write(Notice n) throws Exception {
		// db
		Connection con = DBCon.getConnection();
		String sql = "insert into notices values" 
				+ "((select max(to_number(seq))+1 from notices),"
				+ "?,'cj',?,systimestamp,0)";

		// 실행
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, n.getTitle());
		pstmt.setString(2, n.getContent());
		pstmt.executeUpdate();

		pstmt.close();
		con.close();

	}

	public void update(Notice n) throws Exception {
		Connection con = DBCon.getConnection();
		String sql = "update notices " 
					+ "set title=?, content=? where seq=?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, n.getTitle());
		pstmt.setString(2, n.getContent());
		pstmt.setString(3, n.getSeq());

		pstmt.executeUpdate();

		pstmt.close();
		con.close();

	}

	public int delete(String seq) throws Exception {
		String sql = "delete from notices where seq="+seq;
		Connection con = DBCon.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		int delcnt=pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
		return delcnt;
		
	}
	public Notice getNotice(String seq) throws Exception {
		Connection con=DBCon.getConnection();
		String sql="select seq,title,writer,content,regdate,hit from notices where seq="+seq;

		//실행
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);

		rs.next();
		
		Notice n=new Notice();
		n.setSeq(rs.getString("seq"));
		n.setWriter(rs.getString("writer"));
		n.setTitle(rs.getString("title"));
		n.setContent(rs.getString("content"));
		n.setRegdate(rs.getDate("regdate"));
		n.setHit(rs.getInt("hit"));
		
		rs.close();
		stmt.close();
		con.close();
		
		return n;
	}
}
