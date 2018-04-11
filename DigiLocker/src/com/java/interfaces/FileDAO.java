package com.java.interfaces;

import com.java.model.FileModel;

public interface FileDAO {

	public boolean uploadFile(String fileLocation,FileModel f1);
	public String downloadFile(FileModel f1);
	public FileModel viewFile(FileModel file);
	public void deleteFile(FileModel f1);
}
