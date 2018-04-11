package com.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.java.dbconnection.DBUtil;
import com.java.interfaces.SessionDAO;

public class MySessionDAO implements SessionDAO {

	public boolean createActiveSession(String sessionId, String username) {
		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into ACTIVE_SESSION values(?,?)");
			ps.setString(1, username);
			ps.setString(2, sessionId);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public boolean deleteActiveSession(String session_id) {
		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		PreparedStatement ps;
		try {
			ps = con
					.prepareStatement("DELETE FROM ACTIVE_SESSION WHERE SESSIONID="
							+ "'" + session_id + "'");
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean sessionExists(String sessionId) {
		Statement stmt = null;

		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		try {
			stmt = con.createStatement();

			String sql = "SELECT * FROM ACTIVE_SESSION WHERE SESSIONID=" + "'"
					+ sessionId + "'";

			ResultSet rs;

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
