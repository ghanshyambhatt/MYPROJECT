package com.java.model;

public class FileModel {

	private String UserID = "";
	private String FileName = "";
	private int Category_ID;
	private String Description = "";
	private String FileType = "";
	private String Secure = "";

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public int getCategory_ID() {
		return Category_ID;
	}

	public void setCategory_ID(int categoryID) {
		Category_ID = categoryID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getFileType() {
		return FileType;
	}

	public void setFileType(String fileType) {
		FileType = fileType;
	}

	public String getSecure() {
		return Secure;
	}

	public void setSecure(String secure) {
		Secure = secure;
	}

}
