package com.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.java.dbconnection.DBUtil;
import com.java.interfaces.UserDAO;
import com.java.model.User;

public class MyUserDAO implements UserDAO {

	private Statement stmt = null;

	public boolean createNewUser(User u) {

		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		// get all user details from user model and set them in database.

		PreparedStatement ps;
		try {
			ps = con
					.prepareStatement("insert into USER_INFO values(?,?,?,?,?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getMobile());
			ps.setString(5, u.getName());
			ps.setString(6, u.getCountry());
			ps.setString(7, u.getStatus());

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

	public boolean deleteUser(User user) {
		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		String username = user.getUsername();
		PreparedStatement ps;
		try {
			ps = con
					.prepareStatement("UPDATE USER_INFO set status='inactive' where userid="
							+ "'" + username + "'");
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

	public boolean UpdateUserInfo(User user) {
		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		String username = user.getUsername();
		String name = user.getName();
		String email = user.getEmail();
		String country = user.getCountry();
		String mobile = user.getMobile();

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("UPDATE USER_INFO SET NAME='" + name
					+ "'" + " , " + "EMAIL='" + email + "'" + " , "
					+ "COUNTRY='" + country + "'" + " , " + "PHONE='" + mobile
					+ " ' " + " WHERE USERID='" + username + "'");
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

	public User getUserbyUsername(String username) {

		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		// this method will get user details from database.
		User user = new User();

		ResultSet rs;
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM USER_INFO WHERE USERID=" + "'"
					+ username + "'";

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				user.setName(rs.getString("NAME"));
				user.setUsername(rs.getString("USERID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setMobile(rs.getString("PHONE"));
				user.setCountry(rs.getString("COUNTRY"));
				user.setStatus(rs.getString("STATUS"));

				return user;
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

		return null;
	}

	public User getUserbyEmail(String email) {

		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		// this method will get user details from database.
		User user = new User();

		ResultSet rs;
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM USER_INFO WHERE EMAIL=" + "'" + email
					+ "'";

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				user.setName(rs.getString("NAME"));
				user.setUsername(rs.getString("USERID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setMobile(rs.getString("PHONE"));
				user.setCountry(rs.getString("COUNTRY"));
				user.setStatus(rs.getString("STATUS"));

				return user;
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

		return null;
	}

	public void tokenStore(String username, int token) {
		// this method stores user token in database.

		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into TOKEN values(?,?)");
			ps.setString(1, username);
			ps.setInt(2, token);

			ps.executeUpdate();

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

	}

	public String verifyToken(String id) {

		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		ResultSet rs;
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM TOKEN WHERE ID=" + "'" + id + "'";

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("USERID");
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
		return null;

	}

	public boolean setStatus(String username) {
		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		PreparedStatement ps;
		try {
			ps = con
					.prepareStatement("UPDATE USER_INFO set status='active' where userid="
							+ "'" + username + "'");
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

	public void deleteToken(String id) {
		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM TOKEN WHERE TOKENID=" + "'"
					+ id + "'");
			ps.executeUpdate();

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

	}

	public boolean changePassword(String username, String password) {
		// getting connection.
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("UPDATE USER_INFO set PASSWORD=" + "'"
					+ password + "'" + " where userid=" + "'" + username + "'");
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
}
