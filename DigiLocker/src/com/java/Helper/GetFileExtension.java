package com.java.Helper;

import java.io.File;

public class GetFileExtension {
	public static String getFileExtension(String fileLocation) {
		File file = new File(fileLocation);
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}
}
