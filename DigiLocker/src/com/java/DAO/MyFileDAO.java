package com.java.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.java.Helper.Encryption;
import com.java.dbconnection.DBUtil;
import com.java.interfaces.FileDAO;
import com.java.model.FileModel;
import com.java.resource.property.PropertyLoader;

public class MyFileDAO implements FileDAO {

	// This class is used to interact with the database.
	private Connection con = null;
	private Statement stmt = null;
	private String file_path = PropertyLoader
			.getPropertyValue("dcryptedfilepath");
	private String file_path2 = PropertyLoader
			.getPropertyValue("encryptedfilepath");
	int flag = 0;

	public String downloadFile(FileModel f1) {
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		try {
			stmt = con.createStatement();

			String sql = "SELECT * FROM FILE_DETAILS WHERE FILE_ID=(SELECT FILE_ID FROM FILE_INFO WHERE FILENAME="
					+ "'"
					+ f1.getFileName()
					+ "'"
					+ " AND USERID="
					+ "'"
					+ f1.getUserID() + "'" + ")";

			ResultSet rs;

			rs = stmt.executeQuery(sql);

			// Extract data from result set
			if (rs.next()) {

				Blob b = rs.getBlob("FILE_DATA");
				byte barr[] = b.getBytes(1, (int) b.length());// 1 means first
				// image

				if (f1.getSecure().equals("yes")) {
					flag = 1;

					FileOutputStream fout = new FileOutputStream(file_path2
							+ f1.getFileName() + "." + f1.getFileType());
					fout.write(barr);

					fout.close();
					// call dycrypt
					Encryption enc = new Encryption();
					file_path = enc.dcryptFile(f1.getFileName() + "."
							+ f1.getFileType());

				} else {
					FileOutputStream fout = new FileOutputStream(file_path
							+ f1.getFileName() + "." + f1.getFileType());
					fout.write(barr);

					fout.close();

					file_path = file_path + f1.getFileName() + "."
							+ f1.getFileType();
				}

			}
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return file_path;

	}

	public boolean uploadFile(String fileLocation, FileModel f1) {
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into FILE_DETAILS values(?,?,?)");

			String uniqueID = UUID.randomUUID().toString();

			// SequenceGen sq=new SequenceGen();
			// int id=sq.nextNum();
			ps.setString(1, uniqueID);
			ps.setString(2, "");

			FileInputStream fin;

			fin = new FileInputStream(fileLocation);

			ps.setBinaryStream(3, fin, fin.available());

			int i;

			i = ps.executeUpdate();

			System.out.println(i + " records affected");

			// checking filename.
			f1 = filenameExists(f1);

			// getting connection.
			con = d.getConnection();

			ps = con
					.prepareStatement("insert into FILE_INFO values(?,?,?,?,?,?,?,?)");

			ps.setString(1, f1.getUserID());
			ps.setString(2, f1.getFileName());
			ps.setInt(3, f1.getCategory_ID());
			ps.setString(4, f1.getDescription());
			ps.setString(5, f1.getFileType());
			ps.setString(6, uniqueID);
			ps.setString(7, f1.getSecure());
			ps.setInt(8, 1);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;

	}

	private FileModel filenameExists(FileModel f1) {

		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		try {
			stmt = con.createStatement();

			String sql = "SELECT * FROM FILE_INFO WHERE FILENAME='"
					+ f1.getFileName() + "'" + " AND CATEGORY_ID='"
					+ f1.getCategory_ID() + "'" + " AND USERID='"
					+ f1.getUserID() + "'";

			ResultSet rs;

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				System.out.println("available filename");
				f1.setFileName(newFileName(f1.getFileName()));
				filenameExists(f1);
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
		return f1;
	}

	private String newFileName(String fileName) {

		fileName = fileName + "(1)";

		return fileName;
	}

	public FileModel viewFile(FileModel f1) {
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		try {
			stmt = con.createStatement();

			String sql = "SELECT * FROM FILE_INFO WHERE FILENAME=" + "'"
					+ f1.getFileName() + "'" + " AND USERID='" + f1.getUserID()
					+ "' AND FLAG=" + 1;

			ResultSet rs;

			rs = stmt.executeQuery(sql);

			// Extract data from result set
			if (rs.next()) {

				f1.setUserID(rs.getString("USERID"));// not required currently
				f1.setFileName(rs.getString("FILENAME"));// not required
				// currently
				f1.setCategory_ID(rs.getInt("CATEGORY_ID"));
				f1.setDescription(rs.getString("DESCRIPTION"));
				f1.setFileType(rs.getString("FILETYPE"));
				f1.setSecure(rs.getString("SECURE"));
			}
			stmt.close();

		} catch (SQLException e) {
			System.out.println("MY EXCEPTION!");
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return f1;

	}

	public void deleteFile(FileModel f1) {
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		PreparedStatement ps;
		try {

			ps = con
					.prepareStatement("DELETE FROM FILE_DETAILS WHERE FILE_ID=(SELECT FILE_ID FROM FILE_INFO WHERE FILENAME='"
							+ f1.getFileName()
							+ "' AND CATEGORY_ID='"
							+ f1.getCategory_ID()
							+ "' AND USERID='"
							+ f1.getUserID() + "'" + ")");
			ps.executeUpdate();

			ps = con.prepareStatement("DELETE FROM FILE_INFO WHERE FILENAME='"
					+ f1.getFileName() + "'" + " AND CATEGORY_ID='"
					+ f1.getCategory_ID() + "'" + " AND USERID='"
					+ f1.getUserID() + "'");
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

	public List<FileModel> getfiledetails(String userid) {
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		try {
			stmt = con.createStatement();

			String sql = "SELECT * FROM FILE_INFO WHERE USERID=" + "'" + userid
					+ "'" + "AND FLAG=" + 1;

			ResultSet rs;

			rs = stmt.executeQuery(sql);

			List<FileModel> list = new ArrayList<FileModel>();

			// Extract data from result set
			while (rs.next()) {

				FileModel f1 = new FileModel();

				f1.setUserID(rs.getString("USERID"));// not required currently
				f1.setFileName(rs.getString("FILENAME"));// not required
				// currently
				f1.setCategory_ID(rs.getInt("CATEGORY_ID"));
				f1.setDescription(rs.getString("DESCRIPTION"));
				f1.setFileType(rs.getString("FILETYPE"));
				f1.setSecure(rs.getString("SECURE"));

				list.add(f1);
			}
			stmt.close();
			return list;
		} catch (SQLException e) {
			System.out.println("MY EXCEPTION!");
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

	public List<FileModel> getFilesbyCategory(String userid, String category) {
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		try {
			stmt = con.createStatement();

			String sql = "SELECT * FROM FILE_INFO WHERE USERID="
					+ "'"
					+ userid
					+ "'"
					+ " AND FLAG="
					+ 1
					+ " AND CATEGORY_ID=(SELECT CATEGORY_ID FROM CATEGORY WHERE CATEGORY_NAME='"
					+ category + "')";

			ResultSet rs;

			rs = stmt.executeQuery(sql);

			List<FileModel> list = new ArrayList<FileModel>();

			// Extract data from result set
			while (rs.next()) {

				FileModel f1 = new FileModel();

				f1.setUserID(rs.getString("USERID"));// not required currently
				f1.setFileName(rs.getString("FILENAME"));// not required
				// currently
				f1.setCategory_ID(rs.getInt("CATEGORY_ID"));
				f1.setDescription(rs.getString("DESCRIPTION"));
				f1.setFileType(rs.getString("FILETYPE"));
				f1.setSecure(rs.getString("SECURE"));

				list.add(f1);
			}
			stmt.close();
			return list;
		} catch (SQLException e) {
			System.out.println("MY EXCEPTION!");
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

	public int getCategoryID(String category) {

		int id = 0;
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		try {
			stmt = con.createStatement();

			String sql = "SELECT CATEGORY_ID FROM CATEGORY WHERE CATEGORY_NAME='"
					+ category + "'";
			ResultSet rs;

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				id = rs.getInt("CATEGORY_ID");
			}
			return id;
		} catch (SQLException e) {
			System.out.println("MY EXCEPTION!");
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public void hideFile(FileModel f1) {
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		PreparedStatement ps;
		try {

			ps = con.prepareStatement("UPDATE FILE_INFO SET FLAG=" + 0
					+ "WHERE FILENAME='" + f1.getFileName() + "'"
					+ " AND CATEGORY_ID='" + f1.getCategory_ID() + "'"
					+ " AND USERID='" + f1.getUserID() + "'");
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
	
	public void showFile(FileModel f1) {
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		PreparedStatement ps;
		try {

			ps = con.prepareStatement("UPDATE FILE_INFO SET FLAG=" + 1
					+ "WHERE FILENAME='" + f1.getFileName() + "'"
					+ " AND CATEGORY_ID='" + f1.getCategory_ID() + "'"
					+ " AND USERID='" + f1.getUserID() + "'");
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

	public List<FileModel> getHiddenfiles(String userid) {
		// connecting to database.
		DBUtil d = new DBUtil();
		con = d.getConnection();

		try {
			stmt = con.createStatement();

			String sql = "SELECT * FROM FILE_INFO WHERE USERID=" + "'" + userid
					+ "'" + " AND FLAG=" + 0;

			ResultSet rs;

			rs = stmt.executeQuery(sql);

			List<FileModel> list = new ArrayList<FileModel>();

			// Extract data from result set
			while (rs.next()) {

				FileModel f1 = new FileModel();

				f1.setUserID(rs.getString("USERID"));// not required currently
				f1.setFileName(rs.getString("FILENAME"));// not required
				// currently
				f1.setCategory_ID(rs.getInt("CATEGORY_ID"));
				f1.setDescription(rs.getString("DESCRIPTION"));
				f1.setFileType(rs.getString("FILETYPE"));
				f1.setSecure(rs.getString("SECURE"));

				list.add(f1);
			}
			stmt.close();
			return list;
		} catch (SQLException e) {
			System.out.println("MY EXCEPTION!");
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
}
